package com.base.lx.listener;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;
/**
 * @author fuyuchuang
 * @version 1.0
 * @date 2023/3/6 16:51
 * @description
 */
@Component
@RabbitListener(queues = "fanout.C")
public class FanoutReceiverC {

    @RabbitHandler
    public void process(Map testMessage) {
        System.out.println("FanoutReceiverC消费者收到消息  : " +testMessage.toString());
    }

}
