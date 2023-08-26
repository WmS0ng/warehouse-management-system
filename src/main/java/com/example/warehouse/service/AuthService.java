package com.example.warehouse.service;

import com.example.warehouse.entity.AuthTree;

import java.util.List;

public interface AuthService {
    // 查询用户的菜单树
    List<AuthTree> selectAuthTreeByUid(Integer userId);
}
