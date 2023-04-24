package com.base.lx.service;

import com.base.lx.controller.StudentVO;

import java.util.List;

/**
 * @author FuYuChuang
 * @version 1.0
 * @date 2023/4/14 9:59
 * @desc
 */
public interface DataService {

    List<StudentVO>  sort(List<StudentVO> list);

}
