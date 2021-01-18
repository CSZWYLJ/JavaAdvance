package com.cs.controller;

import com.cs.produceMSG.Producer1;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author qx
 * @Date 2021/1/17 15:55
 * @Description
 */
@RestController
public class TopicController {

    @Resource
    private Producer1 producer1;

    @PostMapping("/topicMsg")
    public String topic(@RequestParam(name = "msg") String msg, @RequestParam(name = "routingKey") String routingKey) {
        return producer1.sendMsgByTopicExchange(msg, routingKey);
    }
}
