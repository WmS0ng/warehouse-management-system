package com.example.warehouse.service;


import com.example.warehouse.entity.User;

/**
 * user_info标的service接口
 */
public interface UserService {
    // 根据账号查询用户的业务方法
    public User selectUserByCode(String userCode);
}
