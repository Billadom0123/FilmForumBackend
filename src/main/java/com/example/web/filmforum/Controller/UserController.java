package com.example.web.filmforum.Controller;

import com.alibaba.fastjson2.JSONObject;
import com.example.web.filmforum.Payload.DataResponse;
import com.example.web.filmforum.Service.Login.UserService;
import com.example.web.filmforum.Service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FollowService followService;

    @PostMapping("/register")
    public DataResponse registerUser(@RequestBody JSONObject payload) {
        return userService.registerUser(payload);
    }

    @PostMapping("/registerAdmin")
    @Secured("ROLE_ADMIN")
    public DataResponse registerAdmin(@RequestBody JSONObject payload) {
        return userService.registerAdmin(payload);
    }

    // 关注用户（仅POST）
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping("/{id}/follow")
    public DataResponse follow(@PathVariable("id") Long targetUserId) {
        return followService.followUser(targetUserId);
    }

    // 取消关注（仅POST，满足只用GET/POST的约束）
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping("/{id}/unfollow")
    public DataResponse unfollow(@PathVariable("id") Long targetUserId) {
        return followService.unfollowUser(targetUserId);
    }

    // 新增：更新当前登录用户头像（仅POST）
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping("/avatar")
    public DataResponse updateAvatar(@RequestBody JSONObject payload) {
        return userService.updateAvatar(payload);
    }
}
