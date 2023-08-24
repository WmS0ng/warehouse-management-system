package com.example.warehouse.utils;

/**
 * 常量类:
 * 里面定义的全部是全局常量的接口称为常量类
 */
public interface WarehouseConstants {

    // 用户未审核
    String USER_STATE_NOT_PASS = "0";

    // 用户已审核
    String USER_STATE_PASS = "1";

    // 传递token的请求头名称
    String HEADER_TOKEN_NAME = "Token";
}
