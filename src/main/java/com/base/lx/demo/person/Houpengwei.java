package com.base.lx.demo.person;

import com.base.lx.demo.car.Car;

public class Houpengwei extends PersonCar{

    public Houpengwei(Car car){
        super(car);
    }

    @Override
    public void home() {
        car.start();
        car.left();
        car.stop();
    }
}
