package com.example.warehouse.service;

import com.example.warehouse.entity.Role;
import com.example.warehouse.page.Page;
import com.example.warehouse.result.Result;

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

    /**
     * 添加角色
     */
    Result insertRole(Role role);

    /**
     * 启用或禁用角色
     */
    Result updateRoleStateByRid(Role role);

    /**
     * 根据角色id删除角色
     */
    Result deleteRoleByRid(Integer roleId);
}
