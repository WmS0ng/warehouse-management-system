package com.example.warehouse.service;

import com.example.warehouse.entity.Auth;

import java.util.List;

public interface AuthService {
    /**
     * 根据用户id查询所有权限树
     */
    List<Auth> selectTreeListByUserId(Integer userId);

    /**
     * 查询所有权限树
     */
    List<Auth> selectTreeList();

    /**
     * 根据roleId查询authIdList
     */
    List<Integer> selectIdListByRoleId(Integer roleId);
}
