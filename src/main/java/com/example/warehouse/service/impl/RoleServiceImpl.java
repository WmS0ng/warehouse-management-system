package com.example.warehouse.service.impl;

import com.example.warehouse.entity.Role;
import com.example.warehouse.mapper.RoleMapper;
import com.example.warehouse.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

// 指定缓存的名称（数据保存到redis中的键的前缀，一般值给标注@CacheConfig注解类的全路径
@CacheConfig(cacheNames = "com.example.warehouse.service.impl.RoleServiceImpl")
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 查询所有角色的业务方法
     */
    @Cacheable(key = "'all:role'")
    @Override
    public List<Role> selectAllRole() {
        return roleMapper.selectAllRole();
    }


    /**
     * 根据userId查询所有角色
     */
    @Override
    public List<Role> selectRoleListByUid(Integer userId) {
        return roleMapper.selectRoleListByUid(userId);
    }
}
