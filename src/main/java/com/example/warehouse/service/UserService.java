package com.example.warehouse.service;


import com.example.warehouse.entity.User;
import com.example.warehouse.page.Page;

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
}
