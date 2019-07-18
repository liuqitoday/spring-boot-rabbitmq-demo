package com.liuqitech.mqdemo.service;

import com.liuqitech.mqdemo.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class Runner implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;

    public Runner(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_DIRECT, "test message");
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_FANOUT, "", "fanout message");

        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_TOPIC, "com.liuqitech.info", "[com.liuqitech.info]topic message");

        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_TOPIC, "com.liuqitech.error", "[com.liuqitech.error]topic message");
    }

}