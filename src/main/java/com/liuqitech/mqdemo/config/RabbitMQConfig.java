package com.liuqitech.mqdemo.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_DIRECT = "directQueue";
    public static final String QUEUE_FANOUT_1 = "fanoutQueue1";
    public static final String QUEUE_FANOUT_2 = "fanoutQueue2";
    public static final String QUEUE_TOPIC = "topicQueue";
    public static final String QUEUE_TOPIC_2 = "topicQueue2";
    public static final String EXCHANGE_FANOUT = "fanoutExchange";
    public static final String EXCHANGE_TOPIC = "topicExchange";

    @Bean
    Queue directQueue() {
        return new Queue(QUEUE_DIRECT);
    }

    @Bean
    Queue fanoutQueue1() {
        return new Queue(QUEUE_FANOUT_1);
    }

    @Bean
    Queue fanoutQueue2() {
        return new Queue(QUEUE_FANOUT_2);
    }

    @Bean
    Queue topicQueue() {
        return new Queue(QUEUE_TOPIC);
    }

    @Bean
    Queue topicQueue2() {
        return new Queue(QUEUE_TOPIC_2);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(EXCHANGE_FANOUT);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(EXCHANGE_TOPIC);
    }

    @Bean
    public Binding bindFanoutExchangeWithFanoutQueue1(@Qualifier("fanoutExchange") FanoutExchange fanoutExchange,
                                                      @Qualifier("fanoutQueue1") Queue fanoutQueue1) {
        return BindingBuilder.bind(fanoutQueue1).to(fanoutExchange);
    }

    @Bean
    public Binding bindFanoutExchangeWithFanoutQueue2(@Qualifier("fanoutExchange") FanoutExchange fanoutExchange,
                                                      @Qualifier("fanoutQueue2") Queue fanoutQueue2) {
        return BindingBuilder.bind(fanoutQueue2).to(fanoutExchange);
    }

    @Bean
    public Binding bindTopicExchangeWithTopicQueue(@Qualifier("topicExchange") TopicExchange topicExchange,
                                                   @Qualifier("topicQueue") Queue topicQueue) {
        return BindingBuilder.bind(topicQueue).to(topicExchange).with("*.liuqitech.*");
    }

    @Bean
    public Binding bindTopicExchangeWithTopicQueue2(@Qualifier("topicExchange") TopicExchange topicExchange,
                                                    @Qualifier("topicQueue2") Queue topicQueue2) {
        return BindingBuilder.bind(topicQueue2).to(topicExchange).with("#.error");
    }

}
