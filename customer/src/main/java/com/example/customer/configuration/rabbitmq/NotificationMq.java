package com.example.customer.configuration.rabbitmq;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;

@Component
@Getter
public class NotificationMq {

    @Value("${rabbitmq.exchanges.internal}")
    private String internalExchange;

    @Value("${rabbitmq.routing-keys.internal-notification}")
    private String internalNotificationRoutingKey;
}
