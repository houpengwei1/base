package com.base.lx.enumstrategy;

import com.base.lx.controller.StudentVO;
import com.base.lx.service.DataService;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author FuYuChuang
 * @version 1.0
 * @date 2023/4/17 16:56
 * @desc
 */
@Service
public class RankServiceImpl {


    @Resource
    private DataService dataService;



    public List<StudentVO> getRank(List<StudentVO> list, String type){

        type = "HighPrice";
        TypeEnum typeEnum = TypeEnum.valueOf(type);

       return typeEnum.sort(list);
    }


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

        String type = "HighPrice";
        TypeEnum typeEnum = TypeEnum.valueOf(type);


        System.out.println(typeEnum.sort(objects));


    }

}
