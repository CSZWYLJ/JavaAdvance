package com.cs.config;

import com.rabbitmq.client.Channel;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author qx
 * @Date 2020/12/31 10:16
 * @Description
 */
@Component
public class RabbitMqManager {
    private static final Logger log = LoggerFactory.getLogger(RabbitMqManager.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void convertAndSend(String queueName, String message) {
        if (!StringUtils.isAnyBlank(queueName, message)) {
            rabbitTemplate.convertAndSend(queueName, message);
        }
    }

    public void convertAndSend(String topicExchange, String routingKey, String message) {
        if (!StringUtils.isAnyBlank(topicExchange, routingKey, message)) {
            rabbitTemplate.convertAndSend(topicExchange, routingKey, message);
        }
    }

    public void receive(String queueName) {
        if (StringUtils.isNotBlank(queueName)) {
            rabbitTemplate.receive(queueName);
        }
    }

    public Channel openNewChannel() {
        ConnectionFactory connectionFactory = rabbitTemplate.getConnectionFactory();
        Connection connection = connectionFactory.createConnection();
        return connection.createChannel(false);
    }

    /**
     * 允许仅使用队列，不使用交换机和routingKey
     *
     * @param channel
     * @param queueName
     */
    public void queueDeclare(Channel channel, String queueName) {
        this.queueDeclareBind(channel, queueName, null, null);
    }

    public void queueDeclareBind(Channel channel, String queueName, String exchangeName, String routingKey) {
        try {
            channel.queueDeclare(queueName, true, false, false, null);
            if (StringUtils.isNotEmpty(exchangeName) && StringUtils.isNotEmpty(routingKey)) {
                channel.queueBind(queueName, exchangeName, routingKey);
            }
            log.info("RabbitMQ queue declare OK [queue = {}, exchange = {}, bind = {}]", queueName, exchangeName, routingKey);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean closeChannel(Channel channel) {
        if (channel == null) {
            return false;
        }
        boolean result = false;

        try {
            com.rabbitmq.client.Connection connection = channel.getConnection();
            channel.close();
            connection.close();
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return result;
    }
}
