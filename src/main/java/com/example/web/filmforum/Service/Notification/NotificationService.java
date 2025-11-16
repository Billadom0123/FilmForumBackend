package com.example.web.filmforum.Service.Notification;

import com.alibaba.fastjson2.JSONArray;
import com.example.web.filmforum.Model.Notification.NotificationPO;
import com.example.web.filmforum.Model.User.UserPO;
import com.example.web.filmforum.Payload.DataResponse;
import com.example.web.filmforum.Payload.Enums.CommonErr;
import com.example.web.filmforum.Repository.NotificationRepository;
import com.example.web.filmforum.Repository.UserRepository;
import com.example.web.filmforum.Util.H;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;
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

    public DataResponse list(int page, int size) {
        UserPO me = currentUser();
        if (me == null) return DataResponse.failure(CommonErr.NO_AUTHENTICATION);
        Pageable pr = PageRequest.of(Math.max(page - 1, 0), Math.max(size, 1));
        Page<NotificationPO> p = notificationRepository.findByToUser_IdOrderByCreateTimeDesc(me.getId(), pr);
        JSONArray arr = new JSONArray();
        for (NotificationPO n : p.getContent()) {
            arr.add(H.build()
                    .put("id", n.getId())
                    .put("type", n.getType())
                    .put("content", n.getContent())
                    .put("fromUser", n.getFromUser() == null ? null : H.build()
                            .put("id", n.getFromUser().getId())
                            .put("nickname", n.getFromUser().getNickname())
                            .put("username", n.getFromUser().getUsername())
                            .put("avatar", n.getFromUser().getAvatar())
                            .toJson())
                    .put("postId", n.getPostId())
                    .put("commentId", n.getCommentId())
                    .put("parentCommentId", n.getParentCommentId())
                    .put("read", n.getReadFlag())
                    .put("createTime", n.getCreateTime())
                    .toJson());
        }
        long unread = notificationRepository.countByToUser_IdAndReadFlagFalse(me.getId());
        return DataResponse.success(H.build()
                .put("notifications", arr)
                .put("total", p.getTotalElements())
                .put("page", pr.getPageNumber() + 1)
                .put("size", pr.getPageSize())
                .put("unread", unread)
                .toJson());
    }

    public DataResponse unreadCount() {
        UserPO me = currentUser();
        if (me == null) return DataResponse.failure(CommonErr.NO_AUTHENTICATION);
        long unread = notificationRepository.countByToUser_IdAndReadFlagFalse(me.getId());
        return DataResponse.success(H.build().put("unread", unread).toJson());
    }

    public DataResponse markRead(Long id) {
        UserPO me = currentUser();
        if (me == null) return DataResponse.failure(CommonErr.NO_AUTHENTICATION);
        NotificationPO n = notificationRepository.findById(id).orElse(null);
        if (n == null) return DataResponse.failure(CommonErr.RESOURCE_NOT_FOUND);
        if (n.getToUser() == null || !n.getToUser().getId().equals(me.getId())) return DataResponse.failure(CommonErr.NO_AUTHORITY);
        if (!Boolean.TRUE.equals(n.getReadFlag())) {
            n.setReadFlag(true);
            notificationRepository.save(n);
        }
        return DataResponse.ok();
    }

    public DataResponse markAllRead() {
        UserPO me = currentUser();
        if (me == null) return DataResponse.failure(CommonErr.NO_AUTHENTICATION);
        Page<NotificationPO> p = notificationRepository.findByToUser_IdOrderByCreateTimeDesc(me.getId(), PageRequest.of(0, 200));
        for (NotificationPO n : p.getContent()) {
            if (!Boolean.TRUE.equals(n.getReadFlag())) {
                n.setReadFlag(true);
            }
        }
        notificationRepository.saveAll(p.getContent());
        return DataResponse.ok();
    }
}

