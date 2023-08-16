package com.base.lx.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;

@Mapper
public interface TAcquiringSplitMapper {

    HashMap<String,Object> selectByMerchantNoAndConfirmOrderNoAndConfirmTradeNo(
            @Param("merchantNo") String merchantNo,
            @Param("confirmOutTradeNo") String confirmOutTradeNo,
            @Param("confirmTradeNo") String confirmTradeNo
    );

}
