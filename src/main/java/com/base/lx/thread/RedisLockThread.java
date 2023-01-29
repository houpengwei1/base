package com.base.lx.thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;


@Component
public class RedisLockThread {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void deduct() {
        //加锁setnx
        Boolean lockFirst = this.redisTemplate.opsForValue().setIfAbsent("lock", "111");
        //重试，递归调用
        if (!lockFirst) {
            try {
                Thread.sleep(50);
                deduct();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }else {
            try {
                // 1.查询库存信息
                String stock = redisTemplate.opsForValue().get("lock").toString();
                //2.判断库存是否充足
                if (stock != null && stock.length() != 0) {
                    Integer st = Integer.valueOf(stock);
                    if (st > 0) {
                        //扣减库存
                        redisTemplate.opsForValue().set("lock", String.valueOf(--st));
                    }
                }

            } finally {
                //解锁
                this.redisTemplate.delete("lock");
            }

        }
    }
}
