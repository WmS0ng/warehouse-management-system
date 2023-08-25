package com.example.warehouse.service;


import com.example.warehouse.entity.User;
import com.example.warehouse.page.Page;
import com.example.warehouse.result.Result;

/**
 * user_info标的service接口
 */
public interface UserService {
    /**
     * 根据账号查询用户
     */
    User selectUserByCode(String userCode);

    /**
     * 分页查询用户
     */
    Page selectUserList(Page page, User user);

    /**
     * 插入用户
     */
    Result insertUser(User user);

    /**
     * 启用或禁用用户
     */
    Result updateState(User user);
}
