package com.cs.consumer;

import com.cs.config.TopicExchangeConfig;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author qx
 * @Date 2021/1/17 16:31
 * @Description
 */
@Component
@RabbitListener(queuesToDeclare = @Queue(TopicExchangeConfig.TOPIC_EXCHANGE_QUEUE_A))
public class ConsumerA {

    @RabbitHandler
    public void process(Map<String, Object> map) {
        System.out.println("队列[" + TopicExchangeConfig.TOPIC_EXCHANGE_QUEUE_A + "]收到消息：" + map.toString());
    }

}
