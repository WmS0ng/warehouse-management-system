package com.example.warehouse.service;

import com.example.warehouse.dto.AssignAuthDto;
import com.example.warehouse.entity.Role;
import com.example.warehouse.page.Page;
import com.example.warehouse.result.Result;

import java.util.List;

public interface RoleService {

    /**
     * 查询所有角色的业务方法
     */
    List<Role> selectList();

    /**
     * 根据userId查询所有角色
     */
    List<Role> selectListByUserId(Integer userId);

    /**
     * 角色分页查询
     */
    Page selectPage(Page page, Role role);

    /**
     * 添加角色
     */
    Result insert(Role role);

    /**
     * 启用或禁用角色
     */
    Result updateState(Role role);

    /**
     * 根据角色id删除角色
     */
    Result deleteById(Integer roleId);

    /**
     * 给角色分配权限
     */
    void assignAuth(AssignAuthDto assignAuthDto);

    /**
     * 修改角色
     */
    Result update(Role role);
}
