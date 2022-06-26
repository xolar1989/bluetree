package org.carlos.bluetree.notification.rabbitmq;

import org.carlos.bluetree.amqp.RabbitMQMessageProducer;
import org.carlos.bluetree.client.notification.NotificationRequest;
import org.carlos.bluetree.notification.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationService notificationService;

    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    private final NotificationConfig config;

    private final String HEADER_RETRIES_COUNT = "HEADER_RETRIES_COUNT";

    private final Integer MAX_RETRIES_COUNT = 5;

    @RabbitListener(queues = "${rabbitmq.queues.notification}")
    public void consumer(NotificationRequest notificationRequest){
        throw new IllegalStateException("not consumed");
//        log.info("Consumed {} from queue",notificationRequest);
//        notificationService.send(notificationRequest);
    }

    @RabbitListener(queues = "${rabbitmq.queues.notification-dlx}")
    public void processFailedMessages(Message failedMessage){
        log.info("");
        Integer retriesCount = Optional.ofNullable((Integer) failedMessage.getMessageProperties()
                .getHeaders().get(HEADER_RETRIES_COUNT)).orElse(1);
        log.info("retries nr {}",retriesCount);
        if (retriesCount >= MAX_RETRIES_COUNT) {
            log.info("Discarding message");
            return;
        }
        log.info("Retrying message for the {} time", retriesCount);
        failedMessage.getMessageProperties()
                .getHeaders().put(HEADER_RETRIES_COUNT, ++retriesCount);
        log.info("Received failed message: {}", failedMessage);
        rabbitMQMessageProducer.publish(failedMessage,config.getInternalExchange());
    }

}
