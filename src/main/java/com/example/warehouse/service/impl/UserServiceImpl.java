package com.example.warehouse.service.impl;

import com.example.warehouse.entity.User;
import com.example.warehouse.mapper.UserMapper;
import com.example.warehouse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    // 注入UserMapper
    @Autowired
    private UserMapper userMapper;

    // 根据账号查询用户的业务方法
    @Override
    public User selectUserByCode(String userCode) {
        return userMapper.selectUserByCode(userCode);
    }
}
