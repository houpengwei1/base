package com.base.lx.controller;


import lombok.Data;

/**
 * @author FuYuChuang
 * @version 1.0
 * @date 2023/4/14 9:50
 * @desc
 */
@Data
public class StudentVO {

    private String name;

    private Integer age;

    private Integer price;


    public  StudentVO(String name, Integer age, Integer price) {
        this.name = name;
        this.age = age;
        this.price = price;
    }

}
