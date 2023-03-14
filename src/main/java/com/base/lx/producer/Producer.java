package com.base.lx.producer;

import com.alibaba.fastjson.JSONObject;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class Producer {

    public static String aa = "";

    public static void main(String[] args) throws MQClientException {
        //创建一个消息生产者，传入的是消息组名称
        DefaultMQProducer producer = new DefaultMQProducer("rmq-group");
        //输入nameserver服务的地址
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.setInstanceName("producer");
        //启动生产者
        producer.start();
        try {
            HashMap map = new HashMap();
            map.put("date", new Date());
            map.put("name", "测试延时队列");

            Thread.sleep(1000);  //每秒发送一次MQ
            //创建消息
            Message msg = new Message();
            //延时队列
            //不支持指定时间 只能根据等级获取对应模板时间
            //"1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h"
            Scanner in = new Scanner(System.in);
            System.out.print("请输入需要延时队列秒数：");
            int anInt = in.nextInt();
            String aa = calculateLevel(anInt);//秒数
            String[] cc = aa.split(",");
            System.out.println(aa);
            if (cc.length > 1) {
                map.put("section", cc);
            }
            map.put("number", 1);
            msg.setDelayTimeLevel(Integer.parseInt(cc[0]));
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
        } finally {
            aa = "";
        }
        producer.shutdown();
    }

    public static String calculateLevel(Integer second) {
        List<Integer> list = getList();
        //判断参数秒数小于等于1  就直接默认级别1
        if (list.get(0) >= second) {
            return "1";
        }
        //判断参数秒数大于最大值2小时  就递归处理
        if (list.get(list.size()-1) < second) {
            aa = aa + list.size() + ",";
            second = second - list.get(list.size()-1);
            calculateLevel(second);
            return aa;
        } else {
            for (int i = 1; i < list.size(); i++) {
                if (list.get(i) < second && list.get(i + 1) > second) {
                    aa = aa + (i + 1) + ",";
                   break;
                }
            }
        }
        return aa;
    }

    public static List<Integer> getList() {
        //模拟时间队列 转换成秒数
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(5);
        list.add(10);
        list.add(30);
        list.add(60);
        list.add(120);
        list.add(180);
        list.add(240);
        list.add(300);
        list.add(360);
        list.add(420);
        list.add(480);
        list.add(5400);
        list.add(6000);
        list.add(12000);
        list.add(18000);
        list.add(36000);
        list.add(72000);
        return list;
    }


}