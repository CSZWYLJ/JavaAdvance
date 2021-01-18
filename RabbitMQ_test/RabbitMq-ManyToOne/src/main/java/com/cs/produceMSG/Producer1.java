package com.cs.produceMSG;

import com.cs.config.TopicExchangeConfig;
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
 * @Date 2021/1/17 16:20
 * @Description
 */
@Service
public class Producer1 {
    @Resource
    private RabbitTemplate rabbitTemplate;

    public String sendMsgByTopicExchange(String msg, String routingKey) {
        Map<String, Object> message = getMessage(msg);
        try {
            rabbitTemplate.convertAndSend(TopicExchangeConfig.TOPIC_EXCHANGE_DEMO_NAME, routingKey, message);
            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }

    }

    public Map<String, Object> getMessage(String msg) {
        String msgId = UUID.randomUUID().toString().replace("-", "").substring(0, 31);
        String sendTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        Map<String, Object> map = new HashMap<>();
        map.put("msgId", msgId);
        map.put("sendTime", sendTime);
        map.put("msg", msg);
        return map;
    }
}
