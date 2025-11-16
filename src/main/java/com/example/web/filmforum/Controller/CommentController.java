package com.example.web.filmforum.Controller;

import com.alibaba.fastjson2.JSONObject;
import com.example.web.filmforum.Payload.DataResponse;
import com.example.web.filmforum.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping("/create")
    public DataResponse create(@RequestBody JSONObject body) {
        return commentService.create(body);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping("/{id}/edit")
    public DataResponse edit(@PathVariable("id") Long id, @RequestBody JSONObject body) {
        return commentService.edit(id, body);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping("/{id}/delete")
    public DataResponse delete(@PathVariable("id") Long id) {
        return commentService.delete(id);
    }

    @GetMapping("/{id}/permission")
    public DataResponse permission(@PathVariable("id") Long id) {
        return commentService.permission(id);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping("/{id}/like")
    public DataResponse like(@PathVariable("id") Long id) {
        return commentService.like(id);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping("/{id}/unlike")
    public DataResponse unlike(@PathVariable("id") Long id) {
        return commentService.unlike(id);
    }
}
