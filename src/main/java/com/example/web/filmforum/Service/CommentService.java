package com.example.web.filmforum.Service;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.example.web.filmforum.Model.Post.CommentPO;
import com.example.web.filmforum.Model.Post.PostPO;
import com.example.web.filmforum.Model.User.UserPO;
import com.example.web.filmforum.Payload.DataResponse;
import com.example.web.filmforum.Payload.Enums.CommonErr;
import com.example.web.filmforum.Repository.CommentRepository;
import com.example.web.filmforum.Repository.PostRepository;
import com.example.web.filmforum.Repository.UserRepository;
import com.example.web.filmforum.Util.H;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    private UserPO currentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) return null;
        String name = auth.getName();
        UserPO user = userRepository.findByUsername(name);
        if (user == null) user = userRepository.findByEmail(name);
        return user;
    }

    public DataResponse list(Long postId, Pageable pageable) {
        PostPO post = postRepository.findById(postId).orElse(null);
        if (post == null) return DataResponse.failure(CommonErr.RESOURCE_NOT_FOUND);
        Page<CommentPO> page = commentRepository.findByPost_IdAndParentIsNullOrderByCreateTimeDesc(postId, pageable);
        JSONArray arr = new JSONArray();
        for (CommentPO c : page.getContent()) {
            List<CommentPO> replies = commentRepository.findByParent_IdOrderByCreateTimeAsc(c.getId());
            JSONArray repliesArr = new JSONArray();
            for (CommentPO r : replies) {
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
                                .toJson()
                );
            }
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
        if (parentId != null) {
            CommentPO parent = commentRepository.findById(parentId).orElse(null);
            if (parent != null && parent.getPost() != null && parent.getPost().getId().equals(postId)) {
                c.setParent(parent);
            }
        }
        CommentPO saved = commentRepository.save(c);
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

    public DataResponse delete(Long id) {
        UserPO me = currentUser();
        if (me == null) return DataResponse.failure(CommonErr.NO_AUTHENTICATION);
        CommentPO c = commentRepository.findById(id).orElse(null);
        if (c == null) return DataResponse.failure(CommonErr.RESOURCE_NOT_FOUND);
        if (c.getAuthor() == null || !c.getAuthor().getId().equals(me.getId())) return DataResponse.failure(CommonErr.NO_AUTHORITY);
        commentRepository.delete(c);
        return DataResponse.ok();
    }
}
