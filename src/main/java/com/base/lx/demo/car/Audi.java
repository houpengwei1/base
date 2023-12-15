package com.base.lx.demo.car;

public class Audi implements Car{
    @Override
    public void start(){
        System.out.println("启动");
    }

    @Override
    public void left() {
        System.out.println("左转");
    }

    @Override
    public void right() {
        System.out.println("右转");
    }

    @Override
    public void stop() {
        System.out.println("停止");
    }


}
