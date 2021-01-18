package com.cs.producer;

import com.cs.queueConfig.RabbitMqConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author qx
 * @Date 2021/1/11 13:37
 * @Description: 发送消息的Service类
 */
@Service
public class Producer1 {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 生产者Producer1发送消息
     *
     * @param msg 消息内容
     * @return 发送结果
     * @throws Exception
     */
    public String sendMsg(String msg) {
        try {
            String msgId = UUID.randomUUID().toString().replace("-", "").substring(0, 32);
            String sendTime = sdf.format(new Date());
            Map<String, Object> map = new HashMap<>();
            map.put("msgId", msgId);
            map.put("sendTime", sendTime);
            map.put("msg", msg);
            rabbitTemplate.convertAndSend(RabbitMqConfig.RABBITMQ_DEMO_DIRECT_EXCHANGE, RabbitMqConfig.RABBITMQ_DEMO_DIRECT_ROUTING, map);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "ok";
    }
}
