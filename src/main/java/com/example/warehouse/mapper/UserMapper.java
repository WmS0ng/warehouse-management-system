package com.example.warehouse.mapper;

import com.example.warehouse.entity.User;

/**
 * user_info的mapper接口
 */
public interface UserMapper {
    // 根据账号查询用户信息的方法
    public User selectUserByCode(String userCode);
}
