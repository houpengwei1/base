package com.base.lx.listener;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

public class KeyExpiredListener extends KeyExpirationEventMessageListener {

    /**
     * @param listenerContainer must not be {@literal null}.
     */
    public KeyExpiredListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    /**
     * message是返回的消息，包括message.getChannel()频道和message.getBody()消息内容，pattern是渠道名称
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        String channel = new String(message.getChannel(), StandardCharsets.UTF_8);
        //过期的key
        String key = new String(message.getBody(),StandardCharsets.UTF_8);
        System.out.println("redis key 过期：pattern={}"+new String(pattern)+",channel={}"+channel+",key={}"+key);
    }

}
