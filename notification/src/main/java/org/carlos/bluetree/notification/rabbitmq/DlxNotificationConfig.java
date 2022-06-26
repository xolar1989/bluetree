package org.carlos.bluetree.notification.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class DlxNotificationConfig {

    @Value("${rabbitmq.exchanges.dlx}")
    private String deadLetterExchange;

    @Value("${rabbitmq.queues.notification-dlx}")
    private String notificationQueueDlx;

    @Bean
    FanoutExchange notificationDeadLetterExchange() {
        return new FanoutExchange(deadLetterExchange);
    }

    @Bean
    Queue notificationDeadLetterQueue() {
        return QueueBuilder.durable(notificationQueueDlx).build();
    }

    @Bean
    Binding notificationDeadLetterBinding() {
        return BindingBuilder.bind(notificationDeadLetterQueue())
                .to(notificationDeadLetterExchange());
    }

    public String getDeadLetterExchange() {
        return deadLetterExchange;
    }

    public String getNotificationQueueDlx() {
        return notificationQueueDlx;
    }
}
