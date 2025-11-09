package com.example.web.filmforum.Controller;

import com.alibaba.fastjson2.JSONObject;
import com.example.web.filmforum.Payload.DataResponse;
import com.example.web.filmforum.Service.Login.UserService;
import com.example.web.filmforum.Service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;

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

    @GetMapping("/me")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public DataResponse getCurrentUserInfo() {
        return userService.getCurrentUserInfo();
    }

    // 获取用户信息
    @GetMapping("/{id}/info")
    public DataResponse info(@PathVariable("id") Long id) {
        return userService.getUserInfo(id);
    }

    // 更新用户信息（本人或管理员），使用POST保持全局风格
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping("/{id}/update")
    public DataResponse update(@PathVariable("id") Long id, @RequestBody JSONObject body) {
        return userService.updateUser(id, body);
    }

    // 获取用户发布的帖子
    @GetMapping("/{id}/posts")
    public DataResponse posts(@PathVariable("id") Long id,
                              @RequestParam(defaultValue = "1") int page,
                              @RequestParam(defaultValue = "10") int size) {
        PageRequest pr = PageRequest.of(Math.max(page - 1, 0), Math.max(size, 1));
        return userService.getUserPosts(id, pr);
    }

    // 获取用户关注列表
    @GetMapping("/{id}/following")
    public DataResponse following(@PathVariable("id") Long id,
                                  @RequestParam(defaultValue = "1") int page,
                                  @RequestParam(defaultValue = "10") int size) {
        PageRequest pr = PageRequest.of(Math.max(page - 1, 0), Math.max(size, 1));
        return userService.getUserFollowing(id, pr);
    }

    // 获取用户收藏的电影/电视剧/综艺
    @GetMapping("/{id}/favorites")
    public DataResponse favorites(@PathVariable("id") Long id,
                                  @RequestParam(defaultValue = "1") int page,
                                  @RequestParam(defaultValue = "10") int size) {
        PageRequest pr = PageRequest.of(Math.max(page - 1, 0), Math.max(size, 1));
        return userService.getUserFavorites(id, pr);
    }
}
