package com.example.warehouse.service;

import com.example.warehouse.entity.Role;
import com.example.warehouse.page.Page;

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

    /**
     * 角色分页查询
     */
    Page selectRolePage(Page page, Role role);
}
