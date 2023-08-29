package com.example.warehouse.service;

import com.example.warehouse.entity.Auth;

import java.util.List;

public interface AuthService {
    /**
     * 根据用户id查询所有权限树
     */
    List<Auth> selectAuthTreeByUid(Integer userId);

    /**
     * 查询所有权限树
     */
    List<Auth> selectAuthTree();

    /**
     * 根据roleId查询authIdList
     */
    List<Integer> selectAuthIdListByRid(Integer roleId);
}
