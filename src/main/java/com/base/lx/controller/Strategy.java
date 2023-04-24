package com.base.lx.controller;

import com.base.lx.service.DataService;
import lombok.Data;

import java.util.List;

/**
 * @author FuYuChuang
 * @version 1.0
 * @date 2023/4/14 10:04
 * @desc
 */
@Data
public class Strategy {

    private DataService dataService;


    public void setDataService(){
        this.dataService = dataService;
    }

    /**
     * 有参构造函数调用排序数据
     * @param students
     */
    public List<StudentVO> Strategy(List<StudentVO> students){
      return this.dataService.sort(students);
    }

}
