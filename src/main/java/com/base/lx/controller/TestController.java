package com.base.lx.controller;

import com.base.lx.service.DataAgeImpl;
import com.base.lx.service.DataPriceImpl;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author FuYuChuang
 * @version 1.0
 * @date 2023/4/14 10:07
 * @desc
 */
public class TestController {


    public static void main(String[] args) {


        StudentVO student1 = new StudentVO("张三",1,25);
        StudentVO student2 = new StudentVO("张三2",12,21);
        StudentVO student3 = new StudentVO("张三3",5,12);
        StudentVO student4 = new StudentVO("张三4",4,13);
        StudentVO student5 = new StudentVO("张三5",6,18);
        List<StudentVO> objects = new ArrayList<>();
        objects.add(student1);
        objects.add(student2);
        objects.add(student3);
        objects.add(student4);
        objects.add(student5);
        System.out.println(objects);

        Scanner scanner = new Scanner(System.in);
        String sc = scanner.next();
        Strategy strategy = new Strategy();
        if ("age".equals(sc)){
            System.out.println("age 排序");
             strategy.setDataService(new DataAgeImpl());
        }else{
            System.out.println("price 排序");
            strategy.setDataService(new DataPriceImpl());
        }

        List<StudentVO> strategy1 = strategy.Strategy(objects);
        System.out.println(strategy1);
    }




}
