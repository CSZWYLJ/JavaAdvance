package com.cs.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @Author qx
 * @Date 2021/1/11 15:40
 * @Description
 */
@Configuration
public class DirectRabbitConfig implements BeanPostProcessor {
    @Resource
    private RabbitAdmin rabbitAdmin;

    @Bean
    public Queue fanoutExchangeQueueA(){
        return new Queue(RabbitMqConfig_Fanout.FANOUT_EXCHANGE_QUEUE_TOPIC_A,true,false,false);
    }

    @Bean
    public Queue fanoutExchangeQueueB(){
        return new Queue(RabbitMqConfig_Fanout.FANOUT_EXCHANGE_QUEUE_TOPIC_B,true,false,false);
    }

    @Bean
    public FanoutExchange rabbitmqDemoFanoutExchange(){
        return new FanoutExchange(RabbitMqConfig_Fanout.FANOUT_EXCHANGE_DEMO_NAME,true,false);
    }

    @Bean
    public Binding bindFanoutA(){
        return BindingBuilder.bind(fanoutExchangeQueueA()).to(rabbitmqDemoFanoutExchange());
    }

    @Bean
    public Binding bindFanoutB(){
        return BindingBuilder.bind(fanoutExchangeQueueB()).to(rabbitmqDemoFanoutExchange());
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory){
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        // 启动项目及创建交换机和队列
        rabbitAdmin.declareExchange(rabbitmqDemoFanoutExchange());
        rabbitAdmin.declareQueue(fanoutExchangeQueueA());
        rabbitAdmin.declareQueue(fanoutExchangeQueueB());
        return null;
    }
}
