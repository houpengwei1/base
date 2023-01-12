package com.base.lx.controller;

import com.base.lx.util.RedisUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/redis")
public class RedisController {

    @Resource
    private RedisUtil redisUtil;

    @GetMapping("/get")
    public void get(){
        System.out.println(redisUtil.getString("cscs"));
    }

    @GetMapping("/put")
    @ResponseBody
    public void put(){
        redisUtil.insertTime("cscs","nb",30l);
    }

}
