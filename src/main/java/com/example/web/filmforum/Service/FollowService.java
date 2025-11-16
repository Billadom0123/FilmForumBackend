package com.example.web.filmforum.Service;

import com.example.web.filmforum.Model.User.FollowPO;
import com.example.web.filmforum.Model.User.UserPO;
import com.example.web.filmforum.Payload.DataResponse;
import com.example.web.filmforum.Payload.Enums.CommonErr;
import com.example.web.filmforum.Repository.FollowRepository;
import com.example.web.filmforum.Repository.UserRepository;
import com.example.web.filmforum.Service.Notification.NotificationProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FollowService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FollowRepository followRepository;
    @Autowired
    private NotificationProducer notificationProducer;

    private UserPO getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            return null;
        }
        String name = auth.getName();
        UserPO user = userRepository.findByUsername(name);
        if (user == null) {
            user = userRepository.findByEmail(name);
        }
        return user;
    }

    public DataResponse followUser(Long targetUserId) {
        UserPO me = getCurrentUser();
        if (me == null) {
            return DataResponse.failure(CommonErr.NO_AUTHENTICATION);
        }
        if (targetUserId == null || targetUserId <= 0) {
            return DataResponse.failure(CommonErr.PARAM_WRONG);
        }
        if (me.getId().equals(targetUserId)) {
            return DataResponse.failure(CommonErr.PARAM_WRONG.getCode(), "不能关注自己");
        }
        UserPO target = userRepository.findById(targetUserId).orElse(null);
        if (target == null) {
            return DataResponse.failure(CommonErr.RESOURCE_NOT_FOUND);
        }
        boolean exists = followRepository.existsByFollower_IdAndFollowing_Id(me.getId(), targetUserId);
        if (exists) {
            return DataResponse.failure(CommonErr.OPERATE_REPEAT);
        }
        FollowPO follow = new FollowPO();
        follow.setFollower(me);
        follow.setFollowing(target);
        follow.setCreateTime(LocalDateTime.now());
        followRepository.save(follow);

        // 发送关注通知
        String nickname = me.getNickname() != null ? me.getNickname() : me.getUsername();
        notificationProducer.sendFollowNotification(me.getId(), target.getId(), nickname + " 关注了你");

        return DataResponse.ok();
    }

    public DataResponse unfollowUser(Long targetUserId) {
        UserPO me = getCurrentUser();
        if (me == null) {
            return DataResponse.failure(CommonErr.NO_AUTHENTICATION);
        }
        if (targetUserId == null || targetUserId <= 0) {
            return DataResponse.failure(CommonErr.PARAM_WRONG);
        }
        UserPO target = userRepository.findById(targetUserId).orElse(null);
        if (target == null) {
            return DataResponse.failure(CommonErr.RESOURCE_NOT_FOUND);
        }
        FollowPO follow = followRepository.findByFollower_IdAndFollowing_Id(me.getId(), targetUserId);
        if (follow == null) {
            return DataResponse.failure(CommonErr.OPERATE_REPEAT);
        }
        followRepository.delete(follow);
        return DataResponse.ok();
    }
}
