package com.cs.controller;

import com.cs.produce.Producer1;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author qx
 * @Date 2021/1/15 9:35
 * @Description
 */

@RestController
@RequestMapping("/fanout/rabbitmq")
public class FanoutController {

    @Resource
    private Producer1 producer1;

    @PostMapping("/sendmsg")
    public String publish(@RequestParam(name = "msg") String msg) throws Exception {
        return producer1.sendMsgFanoutExchange(msg);
    }
}
