package com.base.lx.consumer;

import com.alibaba.fastjson.JSONObject;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.MessageExt;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Consumer {

    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(
                "rmq-group");

        consumer.setNamesrvAddr("127.0.0.1:9876");
        consumer.setInstanceName("consumer");
        //订阅某个主题，然后使用tag过滤消息，不过滤可以用*代表
        consumer.subscribe("Topic-test", "testTag");

        //注册监听回调实现类来处理broker推送过来的消息,MessageListenerConcurrently是并发消费
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> messages, ConsumeConcurrentlyContext context) {
                for (MessageExt msg : messages) {
                    String a = new String(msg.getBody());
                    JSONObject map = JSONObject.parseObject(a);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    Date newDate1 = new Date();
                    String nowDate = simpleDateFormat.format(newDate1);
                    String date = simpleDateFormat.format(map.get("date"));
                    System.out.println("当前时间："+nowDate);
                    System.out.println("mq时间："+date);
                    System.out.println("名称："+map.get("name"));
                    System.out.println("队列次数："+map.get("section"));
                    System.out.println("第几次执行："+map.get("number"));
                    System.out.println(new Date() + "----" + msg.getMsgId() + " ===== " + map);
                    String[] cc = (String[]) map.get("section");
                    if(cc !=null && cc.length > 1){
                        for (int i = 1; i < cc.length; i++) {
                            //创建一个消息生产者，传入的是消息组名称
                            DefaultMQProducer producer = new DefaultMQProducer("rmq-group");
                            //输入nameserver服务的地址
                            producer.setNamesrvAddr("127.0.0.1:9876");
                            producer.setInstanceName("producer");
                            try {
                                HashMap mapParam = new HashMap();
                                mapParam.put("date", date);
                                mapParam.put("name", map.get("name"));
                                Thread.sleep(1000);  //每秒发送一次MQ
                                map.put("number", i++);
                                msg.setDelayTimeLevel(Integer.parseInt(cc[1]));
                                msg.setTopic("Topic-test");
                                msg.setTags("testTag");
                                msg.setBody(JSONObject.toJSONString(map).getBytes(StandardCharsets.UTF_8));
                                System.out.println("发送队列数据："+JSONObject.toJSONString(msg));
                                //发送，返回结果对象
                                SendResult sendResult = producer.send(msg);
                                System.out.println(sendResult.getMsgId()); //消息id
                                System.out.println(sendResult.getMessageQueue()); //队列信息
                                System.out.println(sendResult.getSendStatus());  //发送结果
                                System.out.println(sendResult.getOffsetMsgId()); //下一个要消费的消息的偏移量
                                System.out.println(sendResult.getQueueOffset());  //队列消息偏移量
                                System.out.println();
                                System.out.println("================================================");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            producer.shutdown();
                        }
                    }

                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();//消费者启动完成
        System.out.println("Consumer Started.");
    }

}