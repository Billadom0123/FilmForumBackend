package com.example.web.filmforum.Model.Notification;

import com.example.web.filmforum.Model.User.UserPO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notification")
public class NotificationPO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // FOLLOW / REPLY_POST / REPLY_COMMENT / LIKE_POST / LIKE_COMMENT
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_user_id")
    private UserPO fromUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_user_id")
    private UserPO toUser;

    @Column(columnDefinition = "TEXT")
    private String content;

    private Long postId;
    private Long commentId;
    private Long parentCommentId;

    private Boolean readFlag = false;

    private LocalDateTime createTime = LocalDateTime.now();
}

