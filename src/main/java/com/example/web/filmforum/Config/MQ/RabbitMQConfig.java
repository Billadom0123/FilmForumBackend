package com.example.web.filmforum.Config.MQ;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
@ConditionalOnProperty(prefix = "app.notifications.rabbit", name = "enabled", havingValue = "true")
public class RabbitMQConfig {

    public static final String NOTIFICATION_EXCHANGE = "notification.exchange";
    public static final String FOLLOW_QUEUE = "notification.follow.queue";
    public static final String FOLLOW_ROUTING_KEY = "notification.follow";

    // 新增：回复相关
    public static final String REPLY_POST_QUEUE = "notification.reply.post.queue";
    public static final String REPLY_COMMENT_QUEUE = "notification.reply.comment.queue";
    public static final String REPLY_POST_ROUTING_KEY = "notification.reply.post";
    public static final String REPLY_COMMENT_ROUTING_KEY = "notification.reply.comment";

    // 新增：点赞相关
    public static final String LIKE_POST_QUEUE = "notification.like.post.queue";
    public static final String LIKE_COMMENT_QUEUE = "notification.like.comment.queue";
    public static final String LIKE_POST_ROUTING_KEY = "notification.like.post";
    public static final String LIKE_COMMENT_ROUTING_KEY = "notification.like.comment";

    @Bean
    public DirectExchange notificationExchange() {
        return new DirectExchange(NOTIFICATION_EXCHANGE, true, false);
    }

    @Bean
    public Queue followQueue() {
        return new Queue(FOLLOW_QUEUE, true);
    }

    @Bean
    public Binding followBinding(Queue followQueue, DirectExchange notificationExchange) {
        return BindingBuilder.bind(followQueue).to(notificationExchange).with(FOLLOW_ROUTING_KEY);
    }

    // 新增：回复队列与绑定
    @Bean
    public Queue replyPostQueue() { return new Queue(REPLY_POST_QUEUE, true); }

    @Bean
    public Queue replyCommentQueue() { return new Queue(REPLY_COMMENT_QUEUE, true); }

    @Bean
    public Binding replyPostBinding(DirectExchange notificationExchange) {
        return BindingBuilder.bind(replyPostQueue()).to(notificationExchange).with(REPLY_POST_ROUTING_KEY);
    }

    @Bean
    public Binding replyCommentBinding(DirectExchange notificationExchange) {
        return BindingBuilder.bind(replyCommentQueue()).to(notificationExchange).with(REPLY_COMMENT_ROUTING_KEY);
    }

    // 新增：点赞队列与绑定
    @Bean
    public Queue likePostQueue() { return new Queue(LIKE_POST_QUEUE, true); }

    @Bean
    public Queue likeCommentQueue() { return new Queue(LIKE_COMMENT_QUEUE, true); }

    @Bean
    public Binding likePostBinding(DirectExchange notificationExchange) {
        return BindingBuilder.bind(likePostQueue()).to(notificationExchange).with(LIKE_POST_ROUTING_KEY);
    }

    @Bean
    public Binding likeCommentBinding(DirectExchange notificationExchange) {
        return BindingBuilder.bind(likeCommentQueue()).to(notificationExchange).with(LIKE_COMMENT_ROUTING_KEY);
    }

    // JSON消息转换，避免Java反序列化白名单问题
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter);
        return template;
    }

    @Bean
    public MessageConverter jsonMessageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }
}
