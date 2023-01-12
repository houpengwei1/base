package com.base.lx.task;

import com.base.lx.constants.RedisConstant;
import com.base.lx.util.GsonUtil;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


/**
 * Spring Boot应用程序在启动后，会从容器中遍历实现了CommandLineRunner接口的实例并运行它们的run方法,
 * 所以实现CommandLineRunner的类需要添加@Component等注解。如果有多个类实现了CommandLineRunner接口的话,
 * 也可以利用@Order注解来规定所有CommandLineRunner实例的运行顺序。
 */
@Component
public class RedisQueueRunner implements CommandLineRunner {

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public void run(String... args){
        System.out.println("启动监听队列线程："+ RedisConstant.REDIS_DELAYED_QUEUE_NAME);
        try {
            while (true){
                RBlockingQueue<Object> blockingQueue = redissonClient.getBlockingQueue(RedisConstant.REDIS_DELAYED_QUEUE_NAME);
                System.out.println("获取信息："+ GsonUtil.getGsonString(blockingQueue.take()));
            }
        }catch (Exception e){
            System.out.println("获取异常："+e);
        }
    }


}
