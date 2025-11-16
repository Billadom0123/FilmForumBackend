package com.example.web.filmforum.Service.Notification;

import com.example.web.filmforum.Config.MQ.RabbitMQConfig;
import com.example.web.filmforum.Model.Notification.NotificationMessage;
import com.example.web.filmforum.Model.Notification.NotificationPO;
import com.example.web.filmforum.Model.User.UserPO;
import com.example.web.filmforum.Repository.NotificationRepository;
import com.example.web.filmforum.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;

@Component
@ConditionalOnProperty(prefix = "app.notifications.rabbit", name = "enabled", havingValue = "true")
public class NotificationListener {
    private static final Logger log = LoggerFactory.getLogger(NotificationListener.class);

    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private UserRepository userRepository;

    private void persist(NotificationMessage m) {
        try {
            UserPO from = m.getFromUserId() == null ? null : userRepository.findById(m.getFromUserId()).orElse(null);
            UserPO to = m.getToUserId() == null ? null : userRepository.findById(m.getToUserId()).orElse(null);
            if (to == null) return; // 接收方不存在不入库
            NotificationPO po = new NotificationPO();
            po.setType(m.getType());
            po.setFromUser(from);
            po.setToUser(to);
            po.setContent(m.getContent());
            po.setPostId(m.getPostId());
            po.setCommentId(m.getCommentId());
            po.setParentCommentId(m.getParentCommentId());
            notificationRepository.save(po);
        } catch (Exception e) {
            log.warn("保存通知失败: {}", e.getMessage());
        }
    }

    @RabbitListener(queues = RabbitMQConfig.FOLLOW_QUEUE)
    public void onFollow(NotificationMessage message) {
        persist(message);
        log.info("收到关注通知: type={}, fromUserId={}, toUserId={}, content={}",
                message.getType(), message.getFromUserId(), message.getToUserId(), message.getContent());
    }

    @RabbitListener(queues = RabbitMQConfig.REPLY_POST_QUEUE)
    public void onReplyPost(NotificationMessage message) {
        persist(message);
        log.info("收到帖子回复通知: postId={}, commentId={}, from={}, to={}, preview={}",
                message.getPostId(), message.getCommentId(), message.getFromUserId(), message.getToUserId(), message.getContent());
    }

    @RabbitListener(queues = RabbitMQConfig.REPLY_COMMENT_QUEUE)
    public void onReplyComment(NotificationMessage message) {
        persist(message);
        log.info("收到评论回复通知: postId={}, commentId={}, parentCommentId={}, from={}, to={}, preview={}",
                message.getPostId(), message.getCommentId(), message.getParentCommentId(), message.getFromUserId(), message.getToUserId(), message.getContent());
    }

    @RabbitListener(queues = RabbitMQConfig.LIKE_POST_QUEUE)
    public void onLikePost(NotificationMessage message) {
        persist(message);
        log.info("收到帖子点赞通知: postId={}, from={}, to={}", message.getPostId(), message.getFromUserId(), message.getToUserId());
    }

    @RabbitListener(queues = RabbitMQConfig.LIKE_COMMENT_QUEUE)
    public void onLikeComment(NotificationMessage message) {
        persist(message);
        log.info("收到评论点赞通知: postId={}, commentId={}, from={}, to={}", message.getPostId(), message.getCommentId(), message.getFromUserId(), message.getToUserId());
    }
}
