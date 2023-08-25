package com.example.warehouse.service.impl;

import com.example.warehouse.entity.User;
import com.example.warehouse.mapper.UserMapper;
import com.example.warehouse.page.Page;
import com.example.warehouse.result.Result;
import com.example.warehouse.service.UserService;
import com.example.warehouse.utils.DigestUtil;
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

    @Override
    public Result insertUser(User user) {
        // 判断账号是否已存在
        User u = userMapper.selectUserByCode(user.getUserCode());
        // 账号已存在
        if (u != null) {
            return Result.err(Result.CODE_ERR_BUSINESS, "账号已经存在！");
        }

        String password = DigestUtil.hmacSign(user.getUserPwd());
        user.setUserPwd(password);

        // 执行添加
        int i = userMapper.insertUser(user);
        if (i > 0) {
            return Result.ok("用户添加成功！");
        }

        return Result.err(Result.CODE_ERR_BUSINESS, "用户添加失败！");
    }
}
