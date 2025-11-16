package com.example.web.filmforum.Service;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.example.web.filmforum.Model.Post.CommentPO;
import com.example.web.filmforum.Model.Post.PostPO;
import com.example.web.filmforum.Model.User.UserPO;
import com.example.web.filmforum.Model.Common.LikePO;
import com.example.web.filmforum.Payload.DataResponse;
import com.example.web.filmforum.Payload.Enums.CommonErr;
import com.example.web.filmforum.Repository.CommentRepository;
import com.example.web.filmforum.Repository.PostRepository;
import com.example.web.filmforum.Repository.UserRepository;
import com.example.web.filmforum.Repository.LikeRepository;
import com.example.web.filmforum.Service.Notification.NotificationProducer;
import com.example.web.filmforum.Util.H;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NotificationProducer notificationProducer;
    @Autowired
    private LikeRepository likeRepository;

    private UserPO currentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) return null;
        String name = auth.getName();
        UserPO user = userRepository.findByUsername(name);
        if (user == null) user = userRepository.findByEmail(name);
        return user;
    }

    private boolean isAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) return false;
        for (GrantedAuthority ga : auth.getAuthorities()) {
            if ("ROLE_ADMIN".equals(ga.getAuthority())) return true;
        }
        return false;
    }

    public DataResponse list(Long postId, Pageable pageable) {
        PostPO post = postRepository.findById(postId).orElse(null);
        if (post == null) return DataResponse.failure(CommonErr.RESOURCE_NOT_FOUND);
        Page<CommentPO> page = commentRepository.findByPost_IdAndParentIsNullOrderByCreateTimeDesc(postId, pageable);
        JSONArray arr = new JSONArray();
        UserPO me = currentUser();
        for (CommentPO c : page.getContent()) {
            List<CommentPO> replies = commentRepository.findByParent_IdOrderByCreateTimeAsc(c.getId());
            JSONArray repliesArr = new JSONArray();
            for (CommentPO r : replies) {
                long rLikes = likeRepository.countByTargetTypeAndTargetId("COMMENT", r.getId());
                boolean rIsLiked = me != null && likeRepository.existsByUser_IdAndTargetTypeAndTargetId(me.getId(), "COMMENT", r.getId());
                repliesArr.add(
                        H.build()
                                .put("id", r.getId())
                                .put("content", r.getContent())
                                .put("author", r.getAuthor() == null ? null : H.build()
                                        .put("id", r.getAuthor().getId())
                                        .put("username", r.getAuthor().getUsername())
                                        .put("nickname", r.getAuthor().getNickname())
                                        .put("avatar", r.getAuthor().getAvatar())
                                        .toJson())
                                .put("createTime", r.getCreateTime())
                                .put("likes", rLikes)
                                .put("isLiked", rIsLiked)
                                .toJson()
                );
            }
            long cLikes = likeRepository.countByTargetTypeAndTargetId("COMMENT", c.getId());
            boolean cIsLiked = me != null && likeRepository.existsByUser_IdAndTargetTypeAndTargetId(me.getId(), "COMMENT", c.getId());
            arr.add(
                    H.build()
                            .put("id", c.getId())
                            .put("content", c.getContent())
                            .put("author", c.getAuthor() == null ? null : H.build()
                                    .put("id", c.getAuthor().getId())
                                    .put("username", c.getAuthor().getUsername())
                                    .put("nickname", c.getAuthor().getNickname())
                                    .put("avatar", c.getAuthor().getAvatar())
                                    .toJson())
                            .put("createTime", c.getCreateTime())
                            .put("likes", cLikes)
                            .put("isLiked", cIsLiked)
                            .put("replies", repliesArr)
                            .toJson()
            );
        }
        return DataResponse.success(H.build().put("comments", arr).put("total", page.getTotalElements()).put("page", pageable.getPageNumber() + 1).put("size", pageable.getPageSize()).toJson());
    }

    public DataResponse create(JSONObject body) {
        UserPO me = currentUser();
        if (me == null) return DataResponse.failure(CommonErr.NO_AUTHENTICATION);
        if (body == null) return DataResponse.failure(CommonErr.PARAM_WRONG);
        String content = body.getString("content");
        Long postId = body.getLong("postId");
        Long parentId = body.getLong("parentId");
        if (content == null || content.isBlank() || postId == null) return DataResponse.failure(CommonErr.PARAM_WRONG);
        PostPO post = postRepository.findById(postId).orElse(null);
        if (post == null) return DataResponse.failure(CommonErr.RESOURCE_NOT_FOUND);
        CommentPO c = new CommentPO();
        c.setContent(content);
        c.setAuthor(me);
        c.setPost(post);
        CommentPO parent = null;
        if (parentId != null) {
            parent = commentRepository.findById(parentId).orElse(null);
            if (parent != null && parent.getPost() != null && parent.getPost().getId().equals(postId)) {
                c.setParent(parent);
            } else {
                parent = null; // 不合法父评论忽略
            }
        }
        CommentPO saved = commentRepository.save(c);

        // 发送通知（不阻塞主流程）
        try {
            String preview = content.length() <= 50 ? content : content.substring(0, 50);
            if (c.getParent() == null) {
                // 顶层评论 -> 通知帖子作者
                if (post.getAuthor() != null && !post.getAuthor().getId().equals(me.getId())) {
                    notificationProducer.sendReplyPostNotification(me.getId(), post.getAuthor().getId(), post.getId(), saved.getId(), preview);
                }
            } else {
                // 子评论 -> 通知父评论作者及根评论作者（若不同且不是自己）
                java.util.Set<Long> receivers = new java.util.HashSet<>();
                if (parent != null && parent.getAuthor() != null && !parent.getAuthor().getId().equals(me.getId())) {
                    receivers.add(parent.getAuthor().getId());
                }
                CommentPO root = parent;
                while (root != null && root.getParent() != null) {
                    root = root.getParent();
                }
                if (root != null && root.getAuthor() != null && !root.getAuthor().getId().equals(me.getId())) {
                    receivers.add(root.getAuthor().getId());
                }
                for (Long uid : receivers) {
                    notificationProducer.sendReplyCommentNotification(me.getId(), uid, post.getId(), saved.getId(), parent.getId(), preview);
                }
            }
        } catch (Exception ex) {
            // 记录但不回滚主事务
            org.slf4j.LoggerFactory.getLogger(CommentService.class).warn("发送回复通知失败: {}", ex.getMessage());
        }

        return DataResponse.success(H.build().put("id", saved.getId()).toJson());
    }

    public DataResponse edit(Long id, JSONObject body) {
        UserPO me = currentUser();
        if (me == null) return DataResponse.failure(CommonErr.NO_AUTHENTICATION);
        CommentPO c = commentRepository.findById(id).orElse(null);
        if (c == null) return DataResponse.failure(CommonErr.RESOURCE_NOT_FOUND);
        if (c.getAuthor() == null || !c.getAuthor().getId().equals(me.getId())) return DataResponse.failure(CommonErr.NO_AUTHORITY);
        if (body != null && body.containsKey("content")) c.setContent(body.getString("content"));
        commentRepository.save(c);
        return DataResponse.ok();
    }

    @Transactional
    public DataResponse delete(Long id) {
        UserPO me = currentUser();
        if (me == null) return DataResponse.failure(CommonErr.NO_AUTHENTICATION);
        CommentPO c = commentRepository.findById(id).orElse(null);
        if (c == null) return DataResponse.failure(CommonErr.RESOURCE_NOT_FOUND);
        if (c.getAuthor() == null || !c.getAuthor().getId().equals(me.getId())) return DataResponse.failure(CommonErr.NO_AUTHORITY);
        deleteRecursively(c);
        return DataResponse.ok();
    }

    private void deleteRecursively(CommentPO comment) {
        List<CommentPO> children = commentRepository.findByParent_IdOrderByCreateTimeAsc(comment.getId());
        for (CommentPO child : children) {
            deleteRecursively(child);
        }
        commentRepository.delete(comment);
    }

    public DataResponse permission(Long id) {
        PostPO p = postRepository.findById(id).orElse(null);
        if (p == null) return DataResponse.failure(CommonErr.RESOURCE_NOT_FOUND);
        UserPO me = currentUser();
        boolean canEdit = false;
        boolean canDelete = false;
        if (me != null) {
            canEdit = p.getAuthor() != null && p.getAuthor().getId().equals(me.getId());
            canDelete = canEdit || isAdmin();
        }
        return DataResponse.success(H.build()
                .put("canEdit", canEdit)
                .put("canDelete", canDelete)
                .toJson()
        );
    }

    public DataResponse like(Long id) {
        UserPO me = currentUser();
        if (me == null) return DataResponse.failure(CommonErr.NO_AUTHENTICATION);
        CommentPO c = commentRepository.findById(id).orElse(null);
        if (c == null) return DataResponse.failure(CommonErr.RESOURCE_NOT_FOUND);
        if (likeRepository.existsByUser_IdAndTargetTypeAndTargetId(me.getId(), "COMMENT", id)) {
            return DataResponse.failure(CommonErr.OPERATE_REPEAT);
        }
        LikePO l = new LikePO();
        l.setUser(me);
        l.setTargetType("COMMENT");
        l.setTargetId(id);
        likeRepository.save(l);
        // 点赞后发送通知；取消点赞不发；自己给自己评论点赞不发
        try {
            if (c.getAuthor() != null && !c.getAuthor().getId().equals(me.getId())) {
                Long postId = c.getPost() == null ? null : c.getPost().getId();
                String nick = me.getNickname() != null ? me.getNickname() : me.getUsername();
                notificationProducer.sendLikeCommentNotification(me.getId(), c.getAuthor().getId(), postId, c.getId(), nick + " 赞了你的评论");
            }
        } catch (Exception ex) {
            org.slf4j.LoggerFactory.getLogger(CommentService.class).warn("发送评论点赞通知失败: {}", ex.getMessage());
        }
        return DataResponse.ok();
    }

    public DataResponse unlike(Long id) {
        UserPO me = currentUser();
        if (me == null) return DataResponse.failure(CommonErr.NO_AUTHENTICATION);
        CommentPO c = commentRepository.findById(id).orElse(null);
        if (c == null) return DataResponse.failure(CommonErr.RESOURCE_NOT_FOUND);
        LikePO l = likeRepository.findByUser_IdAndTargetTypeAndTargetId(me.getId(), "COMMENT", id);
        if (l == null) return DataResponse.failure(CommonErr.OPERATE_REPEAT);
        likeRepository.delete(l);
        return DataResponse.ok();
    }
}
