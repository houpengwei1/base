package com.base.lx.demo.person;

import com.base.lx.demo.car.Car;

public abstract class PersonCar implements Person{

    public Car car;

    public PersonCar(Car car){
        this.car = car;
    }

    public abstract void home();


}
