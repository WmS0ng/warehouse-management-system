package com.example.warehouse.service;


import com.example.warehouse.entity.User;

/**
 * user_info标的service接口
 */
public interface UserService {
    // 用户登陆
    User selectUserByCode(String userCode);
}
