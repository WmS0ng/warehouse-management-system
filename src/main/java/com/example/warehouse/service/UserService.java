package com.example.warehouse.service;


import com.example.warehouse.dto.AssignRoleDto;
import com.example.warehouse.entity.User;
import com.example.warehouse.page.Page;
import com.example.warehouse.result.Result;

import java.util.List;

/**
 * user_info标的service接口
 */
public interface UserService {
    /**
     * 根据账号查询用户
     */
    User selectByCode(String userCode);

    /**
     * 分页查询用户
     */
    Page selectPage(Page page, User user);

    /**
     * 插入用户
     */
    Result insert(User user);

    /**
     * 启用或禁用用户
     */
    Result updateState(User user);

    /**
     * 给用户分配角色的业务方法
     */
    void assignRole(AssignRoleDto assignRoleDto);

    /**
     * 批量删除用户
     */
    Result deleteByIds(List<Integer> userIdList);


    /**
     * 修改userName
     */
    Result updateName(User user);

    /**
     * 初始化userPwd
     */
    Result updatePassword(User user);
}
