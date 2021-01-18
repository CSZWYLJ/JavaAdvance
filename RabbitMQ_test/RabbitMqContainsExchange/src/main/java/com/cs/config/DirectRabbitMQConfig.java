package com.cs.config;

import com.cs.queueConfig.RabbitMqConfig;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
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
 * @Date 2021/1/11 10:10
 * @Description
 */

/**
 * 实现BeanPostProcessor类，使用Bean的生命周期函数
 */
@Configuration
public class DirectRabbitMQConfig implements BeanPostProcessor {

    /**
     * 交换器和队列使用的rabbitAdmin对象
     */
    @Resource
    private RabbitAdmin rabbitAdmin;
    @Bean
    public Queue rabbitMQDemoDirectQueue(){
        /**
         * name: 队列名称
         * durable: 是否持久化
         * exclusice: 是否独享、排外的。
         */
        return new Queue(RabbitMqConfig.RABBITMQ_DEMO_TOPIC,true,false,false);
    }


    @Bean
    public DirectExchange rabbitmqDemoDirectExchange(){
        // Direct交换机
        return new DirectExchange(RabbitMqConfig.RABBITMQ_DEMO_DIRECT_EXCHANGE,true,false);
    }

    @Bean
    public Binding bindDirect(){
        return BindingBuilder.bind(rabbitMQDemoDirectQueue()).to(rabbitmqDemoDirectExchange()).with(RabbitMqConfig.RABBITMQ_DEMO_DIRECT_ROUTING);
    }


    /**
     * 初始化rabbitAdmin对象
     * @param connectionFactory
     * @return RabbitAdmin对象
     */
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory){
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }

    /**
     * 实例化bean后，也就是Bean的后置处理器
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        rabbitAdmin.declareExchange(rabbitmqDemoDirectExchange());
        rabbitAdmin.declareQueue(rabbitMQDemoDirectQueue());
        return null;
    }
}
