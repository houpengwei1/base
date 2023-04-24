package com.base.lx.service;

import com.base.lx.controller.StudentVO;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author FuYuChuang
 * @version 1.0
 * @date 2023/4/14 10:00
 * @desc
 */
@Service
public class DataPriceImpl implements DataService{


    @Override
    public List sort(List<StudentVO> list) {

        return list.stream().sorted(Comparator.comparingInt(StudentVO::getPrice).reversed()).collect(Collectors.toList());
    }




}
