package com.danny.rabbitmq.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
public class RabbitMqListener {

    Logger log = LoggerFactory.getLogger(RabbitMqListener.class);

    @RabbitListener(queues = "errorQueue")
    public void processFirstQueue(String message) {
        log.info("First listener received from Queue: {}", message);
    }

    @RabbitListener(queues = "warningAndInfoQueue")
    public void processSecondQueue2(String message) {
        log.info("Second listener received from Queue: {}", message);
    }
}
