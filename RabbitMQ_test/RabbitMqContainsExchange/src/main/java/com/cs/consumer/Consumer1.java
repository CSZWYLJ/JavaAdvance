package com.cs.consumer;


import com.cs.queueConfig.RabbitMqConfig;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author qx
 * @Date 2021/1/11 9:44
 * @Description 消费者消费队列中的消息
 */
@Component
@RabbitListener(queuesToDeclare = @Queue(RabbitMqConfig.RABBITMQ_DEMO_TOPIC))//使用queuesToDeclare属性，如果不存在则会创建队列
public class Consumer1 {

    @RabbitHandler
    public void process(Map map){
        System.out.println("Consumer1得到的消息:" + map.toString());
    }
}
