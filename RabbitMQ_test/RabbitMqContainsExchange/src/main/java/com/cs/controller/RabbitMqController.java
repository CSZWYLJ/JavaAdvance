package com.cs.controller;

import com.cs.producer.Producer1;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author qx
 * @Date 2021/1/11 13:59
 * @Description
 */
@RestController
@RequestMapping("/cs/rabbitmq")
public class RabbitMqController {
    @Resource
    private Producer1 producer1;

    @PostMapping("/sendMsg")
    public String sendMsg(@RequestParam(name = "msg") String msg) {
        return producer1.sendMsg(msg);
    }
}
