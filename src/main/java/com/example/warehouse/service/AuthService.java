package com.example.warehouse.service;

import com.example.warehouse.dto.AuthTree;

import java.util.List;

public interface AuthService {
    /**
     * 根据用户id查询所有权限树
     */
    List<AuthTree> selectAuthTreeByUid(Integer userId);

    /**
     * 查询所有权限树
     */
    List<AuthTree> selectAuthTree();

    /**
     * 根据roleId查询authIdList
     */
    List<Integer> selectAuthIdListByRid(Integer roleId);
}
