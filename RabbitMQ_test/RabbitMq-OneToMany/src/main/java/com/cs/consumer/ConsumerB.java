package com.cs.consumer;

import com.cs.config.RabbitMqConfig_Fanout;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author qx
 * @Date 2021/1/15 9:48
 * @Description
 */

@Component
@RabbitListener(queuesToDeclare = @Queue(RabbitMqConfig_Fanout.FANOUT_EXCHANGE_QUEUE_TOPIC_B))
public class ConsumerB {

    @RabbitHandler
    public void process(Map<String, Object> map) {
        System.out.println("queue B received message: " + map.toString());
    }
}
