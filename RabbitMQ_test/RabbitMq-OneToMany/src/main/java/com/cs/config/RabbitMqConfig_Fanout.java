package com.cs.config;

/**
 * @Author qx
 * @Date 2021/1/11 15:23
 * @Description
 */
public class RabbitMqConfig_Fanout {
    /**
     * rabbitMQ的FANOUT_EXCHANGE 交换机类型的队列A的名称
     */
    public static final String FANOUT_EXCHANGE_QUEUE_TOPIC_A = "fanout.A";

    /**
     * rabbitMQ的FANOUT_EXCHANGE 交换机类型的队列B的名称
     */
    public static final String FANOUT_EXCHANGE_QUEUE_TOPIC_B = "fanout.B";

    /**
     * rabbitMQ的FANOUT_EXCHANGE 交换机类型的名称
     */
    public static final String FANOUT_EXCHANGE_DEMO_NAME = "fanout.exchange.demo.name";

}
