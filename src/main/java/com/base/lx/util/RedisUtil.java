package com.base.lx.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 读取数据
     */
    public String getString(final String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 写入数据
     */
    public boolean setString(final String key, String value) {
        boolean result = false;
        try {
            stringRedisTemplate.opsForValue().set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 更新数据
     */
    public boolean getAndSetString(final String key, String value) {
        boolean result = false;
        try {
            stringRedisTemplate.opsForValue().getAndSet(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 删除数据
     */
    public boolean delete(final String key) {
        boolean result = false;
        try {
            stringRedisTemplate.delete(key);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void insertTime(String key,String value,long time){
        stringRedisTemplate.opsForValue().set(key,value,time, TimeUnit.SECONDS);
    }

    /**
     * 插入redis，设置有效期，单位上层传
     *
     * @param obj      保存对象
     * @param keyEnum  关键字
     * @param timeout  有效期(小于等于0时,则超时时间无效)
     */
    public void insertRedisObject(Object obj, String keyEnum, long timeout) {
        final String value = GsonUtil.getGsonString(obj);
        if (timeout < 0L) {
            stringRedisTemplate.boundValueOps(keyEnum).set(value);
        } else if (timeout != 0L) {
            //只有过期时间不为0才有效,为0直接跳过
            stringRedisTemplate.boundValueOps(keyEnum).set(value, timeout, TimeUnit.MILLISECONDS);
        }
    }


}

