package com.example.web.filmforum.Model.Notification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationMessage implements Serializable {
    private String type; // FOLLOW / REPLY_POST / REPLY_COMMENT
    private Long fromUserId;
    private Long toUserId;
    private String content;
    private LocalDateTime timestamp;
    // 新增字段
    private Long postId;
    private Long commentId;          // 当前产生的评论ID
    private Long parentCommentId;    // 若是子评论，指向其父评论
}
