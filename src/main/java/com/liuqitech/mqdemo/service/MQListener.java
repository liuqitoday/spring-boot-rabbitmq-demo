package com.liuqitech.mqdemo.service;

import com.liuqitech.mqdemo.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MQListener {

    @RabbitListener(queues = RabbitMQConfig.QUEUE_DIRECT)
    public void directQueueProcess(String msg) {
        System.out.println("[directQueueProcess]接收消息" + msg);
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_FANOUT_1)
    public void fanoutQueue1Process(String msg) {
        System.out.println("[fanoutQueue1Process]接收消息" + msg);
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_FANOUT_2)
    public void fanoutQueue2Process(String msg) {
        System.out.println("[fanoutQueue2Process]接收消息" + msg);
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_TOPIC)
    public void topicQueueProcess(String msg) {
        System.out.println("[topicQueueProcess]接收消息" + msg);
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_TOPIC_2)
    public void topicQueueProcess2(String msg) {
        System.out.println("[topicQueueProcess2]接收消息" + msg);
    }
}
