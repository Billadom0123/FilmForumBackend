package com.example.web.filmforum.Service.Notification;

import com.example.web.filmforum.Config.MQ.RabbitMQConfig;
import com.example.web.filmforum.Model.Notification.NotificationMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final Logger log = LoggerFactory.getLogger(NotificationProducer.class);

    public void sendFollowNotification(Long fromUserId, Long toUserId, String content) {
        NotificationMessage msg = new NotificationMessage(
                "FOLLOW",
                fromUserId,
                toUserId,
                content,
                java.time.LocalDateTime.now(),
                null,
                null,
                null
        );
        try {
            rabbitTemplate.convertAndSend(
                    RabbitMQConfig.NOTIFICATION_EXCHANGE,
                    RabbitMQConfig.FOLLOW_ROUTING_KEY,
                    msg
            );
        } catch (Exception e) {
            // 仅记录，不影响主业务流程
            log.warn("发送关注通知失败: {}", e.getMessage());
        }
    }

    public void sendReplyPostNotification(Long fromUserId, Long toUserId, Long postId, Long commentId, String preview) {
        NotificationMessage msg = new NotificationMessage(
                "REPLY_POST",
                fromUserId,
                toUserId,
                preview,
                java.time.LocalDateTime.now(),
                postId,
                commentId,
                null
        );
        try {
            rabbitTemplate.convertAndSend(
                    RabbitMQConfig.NOTIFICATION_EXCHANGE,
                    RabbitMQConfig.REPLY_POST_ROUTING_KEY,
                    msg
            );
        } catch (Exception e) {
            log.warn("发送帖子回复通知失败: {}", e.getMessage());
        }
    }

    public void sendReplyCommentNotification(Long fromUserId, Long toUserId, Long postId, Long commentId, Long parentCommentId, String preview) {
        NotificationMessage msg = new NotificationMessage(
                "REPLY_COMMENT",
                fromUserId,
                toUserId,
                preview,
                java.time.LocalDateTime.now(),
                postId,
                commentId,
                parentCommentId
        );
        try {
            rabbitTemplate.convertAndSend(
                    RabbitMQConfig.NOTIFICATION_EXCHANGE,
                    RabbitMQConfig.REPLY_COMMENT_ROUTING_KEY,
                    msg
            );
        } catch (Exception e) {
            log.warn("发送评论回复通知失败: {}", e.getMessage());
        }
    }

    public void sendLikePostNotification(Long fromUserId, Long toUserId, Long postId, String content) {
        NotificationMessage msg = new NotificationMessage(
                "LIKE_POST",
                fromUserId,
                toUserId,
                content,
                java.time.LocalDateTime.now(),
                postId,
                null,
                null
        );
        try {
            rabbitTemplate.convertAndSend(
                    RabbitMQConfig.NOTIFICATION_EXCHANGE,
                    RabbitMQConfig.LIKE_POST_ROUTING_KEY,
                    msg
            );
        } catch (Exception e) {
            log.warn("发送帖子点赞通知失败: {}", e.getMessage());
        }
    }

    public void sendLikeCommentNotification(Long fromUserId, Long toUserId, Long postId, Long commentId, String content) {
        NotificationMessage msg = new NotificationMessage(
                "LIKE_COMMENT",
                fromUserId,
                toUserId,
                content,
                java.time.LocalDateTime.now(),
                postId,
                commentId,
                null
        );
        try {
            rabbitTemplate.convertAndSend(
                    RabbitMQConfig.NOTIFICATION_EXCHANGE,
                    RabbitMQConfig.LIKE_COMMENT_ROUTING_KEY,
                    msg
            );
        } catch (Exception e) {
            log.warn("发送评论点赞通知失败: {}", e.getMessage());
        }
    }
}
