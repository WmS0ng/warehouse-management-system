package com.example.warehouse.service;

import com.example.warehouse.entity.dto.AuthDto;

import java.util.List;

public interface AuthService {
    // 查询用户的菜单树
    List<AuthDto> selectAuthTreeByUid(Integer userId);
}
