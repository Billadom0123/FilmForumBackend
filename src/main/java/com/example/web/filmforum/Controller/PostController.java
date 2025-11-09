package com.example.web.filmforum.Controller;

import com.alibaba.fastjson2.JSONObject;
import com.example.web.filmforum.Payload.DataResponse;
import com.example.web.filmforum.Service.CommentService;
import com.example.web.filmforum.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/list")
    public DataResponse list(@RequestParam(defaultValue = "1") int page,
                             @RequestParam(defaultValue = "10") int size,
                             @RequestParam(required = false) String category,
                             @RequestParam(required = false) String keyword) {
        PageRequest pageRequest = PageRequest.of(Math.max(page - 1, 0), Math.max(size, 1));
        return postService.list(category, keyword, pageRequest);
    }

    @GetMapping("/{id}")
    public DataResponse detail(@PathVariable Long id) {
        return postService.detail(id);
    }

    @GetMapping("/{id}/comments")
    public DataResponse comments(@PathVariable("id") Long id,
                                 @RequestParam(defaultValue = "1") int page,
                                 @RequestParam(defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(Math.max(page - 1, 0), Math.max(size, 1));
        return commentService.list(id, pageRequest);
    }

    @GetMapping("/{id}/permission")
    public DataResponse permission(@PathVariable("id") Long id) {
        return postService.permission(id);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping("/create")
    public DataResponse create(@RequestBody JSONObject body) {
        return postService.create(body);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping("/{id}/edit")
    public DataResponse edit(@PathVariable("id") Long id, @RequestBody JSONObject body) {
        return postService.edit(id, body);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping("/{id}/delete")
    public DataResponse delete(@PathVariable("id") Long id) {
        return postService.delete(id);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping("/{id}/like")
    public DataResponse like(@PathVariable("id") Long id) {
        return postService.like(id);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping("/{id}/unlike")
    public DataResponse unlike(@PathVariable("id") Long id) {
        return postService.unlike(id);
    }
}
