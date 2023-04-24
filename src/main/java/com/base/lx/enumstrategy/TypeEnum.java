package com.base.lx.enumstrategy;

import com.base.lx.controller.StudentVO;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author FuYuChuang
 * @version 1.0
 * @date 2023/4/14 14:18
 * @desc
 */
public enum TypeEnum {

    // 策略实例
    HighPrice {
        public List<StudentVO> sort(List<StudentVO> source) {
            return source.stream()
                    .sorted(Comparator.comparing(StudentVO::getPrice).reversed())
                    .collect(Collectors.toList());
        }
    },
    LowPrice {
        public List<StudentVO> sort(List<StudentVO> source) {
            return source.stream()
                    .sorted(Comparator.comparing(StudentVO::getPrice))
                    .collect(Collectors.toList());
        }
    };

    public abstract List<StudentVO> sort(List<StudentVO> list);
}
