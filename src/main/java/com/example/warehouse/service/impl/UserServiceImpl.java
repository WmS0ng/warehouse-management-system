package com.example.warehouse.service.impl;

import com.example.warehouse.entity.User;
import com.example.warehouse.mapper.UserMapper;
import com.example.warehouse.page.Page;
import com.example.warehouse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    // 注入UserMapper
    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectUserByCode(String userCode) {
        return userMapper.selectUserByCode(userCode);
    }

    @Override
    public Page selectUserList(Page page, User user) {
        // 查询总用户数量
        Integer count = userMapper.selectUserRowCount(user);
        // 分页查询
        List<User> userList = userMapper.selectUserList(page, user);

        page.setTotalNum(count);
        page.setResultList(userList);

        return page;
    }
}
