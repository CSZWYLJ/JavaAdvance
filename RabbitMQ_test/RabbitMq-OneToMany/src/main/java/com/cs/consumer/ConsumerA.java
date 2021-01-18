package com.cs.consumer;

import com.cs.config.RabbitMqConfig_Fanout;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author qx
 * @Date 2021/1/15 9:45
 * @Description
 */
@Component
@RabbitListener(queuesToDeclare = @Queue(RabbitMqConfig_Fanout.FANOUT_EXCHANGE_QUEUE_TOPIC_A))
public class ConsumerA {

    @RabbitHandler
    public void process(Map<String, Object> map) {
        System.out.println("队列A收到消息: " + map.toString());
    }
}
