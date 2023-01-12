package com.base.lx.service;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TestMap {

    //代替mq做消息队列
    private volatile static Map<String, List<Long>> mapList = new ConcurrentHashMap<>();

    private TestMap() {}

    public static void main(String[] args){
        int i= 0;
        while (true) {
            try {
                System.out.println(LocalTime.now().toString() + TestMap.test("csMap", 2, 5000L));//依次代表 队列id、通过次数、时间
                Thread.sleep(2000);
            }catch (Exception e){

            }
        }
    }


    /**
     * @param csMap     队列id
     * @param count      限制次数
     * @param timeWindow 时间窗口大小
     * @return 是否允许通过
     */
    public static synchronized boolean test(String csMap, int count, long timeWindow) {
        // 获取当前时间
        long nowTime = System.currentTimeMillis();
        // 根据队列id，取出对应的限流队列，若没有则创建
        List<Long> list = mapList.computeIfAbsent(csMap, k -> new LinkedList<>());
        // 如果队列还没满，则允许通过，并添加当前时间戳到队列开始位置
        if (list.size() < count) {
            list.add(0, nowTime);
            return true;
        }
        // 队列已满（达到限制次数），则获取队列中最早添加的时间戳
        Long farTime = list.get(count - 1);
        // 用当前时间戳 减去 最早添加的时间戳
        if (nowTime - farTime <= timeWindow) {
            // 若结果小于等于timeWindow，则说明在timeWindow内，通过的次数大于count
            // 不允许通过
            return false;
        } else {
            // 若结果大于timeWindow，则说明在timeWindow内，通过的次数小于等于count
            // 允许通过，并删除最早添加的时间戳，将当前时间添加到队列开始位置
            list.remove(count - 1);
            list.add(0, nowTime);
            return true;
        }
    }


}
