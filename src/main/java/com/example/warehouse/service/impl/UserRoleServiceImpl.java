package com.example.warehouse.service.impl;

import com.example.warehouse.mapper.UserRoleMapper;
import com.example.warehouse.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleMapper userRoleMapper;
}
