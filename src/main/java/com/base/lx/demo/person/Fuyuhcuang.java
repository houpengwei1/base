package com.base.lx.demo.person;

import com.base.lx.demo.car.Car;

public class Fuyuhcuang extends PersonCar{

    public Fuyuhcuang(Car car){
        super(car);
    }

    @Override
    public void home() {
        car.start();
        car.right();
        car.stop();
    }
}
