package com.base.lx;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class controller {

    @Scheduled(cron = "fixedRate = 5000")
    public void test(){
        System.out.println("定时任务启动");
    }

}
