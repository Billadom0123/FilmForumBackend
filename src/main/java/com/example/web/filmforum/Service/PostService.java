package com.example.web.filmforum.Service;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.example.web.filmforum.Model.Common.LikePO;
import com.example.web.filmforum.Model.Post.PostPO;
import com.example.web.filmforum.Model.User.UserPO;
import com.example.web.filmforum.Payload.DataResponse;
import com.example.web.filmforum.Payload.Enums.CommonErr;
import com.example.web.filmforum.Payload.Pagination;
import com.example.web.filmforum.Repository.CommentRepository;
import com.example.web.filmforum.Repository.LikeRepository;
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
import java.util.ArrayList;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private LikeRepository likeRepository;
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

    public DataResponse list(String category, String keyword, Pageable pageable) {
        Page<PostPO> page = postRepository.search(category, keyword, pageable);
        JSONArray arr = new JSONArray();
        UserPO me = currentUser();
        for (PostPO p : page.getContent()) {
            long likes = likeRepository.countByTargetTypeAndTargetId("POST", p.getId());
            boolean isLiked = me != null && likeRepository.existsByUser_IdAndTargetTypeAndTargetId(me.getId(), "POST", p.getId());
            long comments = commentRepository.countByPost_Id(p.getId());
            String snippet = p.getContent() == null ? null : (p.getContent().length() > 120 ? p.getContent().substring(0, 120) : p.getContent());
            arr.add(
                    H.build()
                            .put("id", p.getId())
                            .put("title", p.getTitle())
                            .put("content", snippet)
                            .put("author", p.getAuthor() == null ? null : H.build()
                                    .put("id", p.getAuthor().getId())
                                    .put("username", p.getAuthor().getUsername())
                                    .put("nickname", p.getAuthor().getNickname())
                                    .put("avatar", p.getAuthor().getAvatar())
                                    .toJson())
                            .put("category", p.getCategory())
                            .put("createTime", p.getCreateTime())
                            .put("views", p.getViews())
                            .put("likes", likes)
                            .put("commentsCount", comments)
                            .put("isLiked", isLiked)
                            .toJson()
            );
        }
        Pagination pag = new Pagination(page.getTotalElements(), pageable.getPageNumber() + 1, pageable.getPageSize(), page.hasNext());
        return DataResponse.success(H.build().put("posts", arr).put("pagination", pag.toJSON()).toJson());
    }

    public DataResponse detail(Long id) {
        PostPO p = postRepository.findById(id).orElse(null);
        if (p == null) return DataResponse.failure(CommonErr.RESOURCE_NOT_FOUND);
        p.setViews(p.getViews() + 1);
        postRepository.save(p);
        UserPO me = currentUser();
        long likes = likeRepository.countByTargetTypeAndTargetId("POST", p.getId());
        boolean isLiked = me != null && likeRepository.existsByUser_IdAndTargetTypeAndTargetId(me.getId(), "POST", p.getId());
        long comments = commentRepository.countByPost_Id(p.getId());
        return DataResponse.success(
                H.build()
                        .put("id", p.getId())
                        .put("title", p.getTitle())
                        .put("content", p.getContent())
                        .put("author", p.getAuthor() == null ? null : H.build()
                                .put("id", p.getAuthor().getId())
                                .put("username", p.getAuthor().getUsername())
                                .put("nickname", p.getAuthor().getNickname())
                                .put("avatar", p.getAuthor().getAvatar())
                                .toJson())
                        .put("category", p.getCategory())
                        .put("createTime", p.getCreateTime())
                        .put("updateTime", p.getUpdateTime())
                        .put("views", p.getViews())
                        .put("likes", likes)
                        .put("commentsCount", comments)
                        .put("isLiked", isLiked)
                        .put("tags", p.getTags())
                        .toJson()
        );
    }

    public DataResponse create(JSONObject body) {
        UserPO me = currentUser();
        if (me == null) return DataResponse.failure(CommonErr.NO_AUTHENTICATION);
        if (body == null) return DataResponse.failure(CommonErr.PARAM_WRONG);
        String title = body.getString("title");
        String content = body.getString("content");
        if (title == null || title.isBlank()
                || content == null || content.isBlank()) return DataResponse.failure(CommonErr.PARAM_WRONG);
        PostPO p = new PostPO();
        p.setTitle(title);
        p.setContent(content);
        p.setCategory(body.getString("category"));
        JSONArray tagArr = body.getJSONArray("tags");
        if (tagArr != null) {
            List<String> tags = new ArrayList<>();
            for (int i = 0; i < tagArr.size(); i++) {
                Object o = tagArr.get(i);
                tags.add(o == null ? null : o.toString());
            }
            p.setTags(tags);
        }
        p.setAuthor(me);
        PostPO saved = postRepository.save(p);
        return DataResponse.success(H.build().put("id", saved.getId()).toJson());
    }

    public DataResponse edit(Long id, JSONObject body) {
        UserPO me = currentUser();
        if (me == null) return DataResponse.failure(CommonErr.NO_AUTHENTICATION);
        PostPO p = postRepository.findById(id).orElse(null);
        if (p == null) return DataResponse.failure(CommonErr.RESOURCE_NOT_FOUND);
        if (p.getAuthor() == null || !p.getAuthor().getId().equals(me.getId())) {
            return DataResponse.failure(CommonErr.NO_AUTHORITY);
        }
        if (body == null) return DataResponse.failure(CommonErr.PARAM_WRONG);
        if (body.containsKey("title")) p.setTitle(body.getString("title"));
        if (body.containsKey("content")) p.setContent(body.getString("content"));
        if (body.containsKey("category")) p.setCategory(body.getString("category"));
        if (body.containsKey("tags")) {
            JSONArray tagArr = body.getJSONArray("tags");
            if (tagArr == null) {
                p.setTags(null);
            } else {
                List<String> tags = new ArrayList<>();
                for (int i = 0; i < tagArr.size(); i++) {
                    Object o = tagArr.get(i);
                    tags.add(o == null ? null : o.toString());
                }
                p.setTags(tags);
            }
        }
        PostPO saved = postRepository.save(p);
        return DataResponse.success(H.build().put("id", saved.getId()).put("updated", true).toJson());
    }

    public DataResponse delete(Long id) {
        UserPO me = currentUser();
        if (me == null) return DataResponse.failure(CommonErr.NO_AUTHENTICATION);
        PostPO p = postRepository.findById(id).orElse(null);
        if (p == null) return DataResponse.failure(CommonErr.RESOURCE_NOT_FOUND);
        if (p.getAuthor() == null || !p.getAuthor().getId().equals(me.getId())) {
            return DataResponse.failure(CommonErr.NO_AUTHORITY);
        }
        postRepository.delete(p);
        return DataResponse.ok();
    }

    public DataResponse like(Long id) {
        UserPO me = currentUser();
        if (me == null) return DataResponse.failure(CommonErr.NO_AUTHENTICATION);
        PostPO p = postRepository.findById(id).orElse(null);
        if (p == null) return DataResponse.failure(CommonErr.RESOURCE_NOT_FOUND);
        if (likeRepository.existsByUser_IdAndTargetTypeAndTargetId(me.getId(), "POST", id)) {
            return DataResponse.failure(CommonErr.OPERATE_REPEAT);
        }
        LikePO l = new LikePO();
        l.setUser(me);
        l.setTargetType("POST");
        l.setTargetId(id);
        likeRepository.save(l);
        return DataResponse.ok();
    }

    public DataResponse unlike(Long id) {
        UserPO me = currentUser();
        if (me == null) return DataResponse.failure(CommonErr.NO_AUTHENTICATION);
        PostPO p = postRepository.findById(id).orElse(null);
        if (p == null) return DataResponse.failure(CommonErr.RESOURCE_NOT_FOUND);
        LikePO l = likeRepository.findByUser_IdAndTargetTypeAndTargetId(me.getId(), "POST", id);
        if (l == null) return DataResponse.failure(CommonErr.OPERATE_REPEAT);
        likeRepository.delete(l);
        return DataResponse.ok();
    }
}
