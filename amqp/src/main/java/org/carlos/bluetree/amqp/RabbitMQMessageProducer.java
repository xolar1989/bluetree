package org.carlos.bluetree.amqp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class RabbitMQMessageProducer {

    private final AmqpTemplate amqpTemplate;

    public void publish(Object payload, String exchange, String routingKey) {
        log.info("Publishing to {} using routing Key {}. Payload: {}", exchange, routingKey, payload);
        amqpTemplate.convertAndSend(exchange, routingKey, payload);
        log.info("Published to {} using routing Key {}. Payload: {}", exchange, routingKey, payload);
    }

    public void publish(Message message, String exchange) {
        log.info("Publishing to {} using routing Key {}. Message: {}", exchange, message.getMessageProperties().getReceivedRoutingKey(), message);
        amqpTemplate.send(exchange, message.getMessageProperties().getReceivedRoutingKey(), message);
        log.info("Published to {} using routing Key {}. Message: {}", exchange, message.getMessageProperties().getReceivedRoutingKey(), message);
    }

}
