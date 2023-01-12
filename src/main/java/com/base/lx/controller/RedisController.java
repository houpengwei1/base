package com.base.lx.controller;

import com.base.lx.constants.RedisConstant;
import com.base.lx.util.RedisUtil;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/redis")
public class RedisController {

    @Resource
    private RedisUtil redisUtil;
    @Autowired
    private RedissonClient redissonClient;

    @GetMapping("/get")
    public void get() {
        System.out.println(redisUtil.getString("cscs"));
    }

    @GetMapping("/put")
    @ResponseBody
    public void put() {
        redisUtil.insertTime("cscs", "nb", 3000l);
    }


    @GetMapping("/putRedissonQueue")
    public void putRedissonQueue() {
        for (int i = 1; i <= 30; i++) {
            RBlockingQueue<HashMap<String, Object>> blockingQueue = redissonClient.getBlockingQueue(RedisConstant.REDIS_DELAYED_QUEUE_NAME);
            RDelayedQueue<HashMap<String, Object>> delayedQueue = redissonClient.getDelayedQueue(blockingQueue);
            HashMap<String, Object> map = new HashMap<>();
            map.put("cs", "nb");
            map.put("cs" + i, "nb" + i);
            delayedQueue.offer(map, 3000 * i, TimeUnit.MILLISECONDS);
        }
    }

}
