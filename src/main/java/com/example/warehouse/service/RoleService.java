package com.example.warehouse.service;

import com.example.warehouse.entity.Role;

import java.util.List;

public interface RoleService {

    /**
     * 查询所有角色的业务方法
     */
    List<Role> selectAllRole();

    /**
     * 根据userId查询所有角色
     */
    List<Role> selectRoleListByUid(Integer userId);
}
