package com.example.warehouse.service.impl;

import com.example.warehouse.entity.Role;
import com.example.warehouse.mapper.RoleMapper;
import com.example.warehouse.page.Page;
import com.example.warehouse.result.Result;
import com.example.warehouse.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * 角色的分页查询
     */
    @Override
    @Transactional
    public Page selectRolePage(Page page, Role role) {
        Integer count = roleMapper.selectRoleRowCount(role);
        List<Role> roleList = roleMapper.selectRolePage(page, role);
        page.setTotalNum(count);
        page.setResultList(roleList);
        return page;
    }

    /**
     * 插入角色
     */
    @Override
    public Result insertRole(Role role) {
        // 判断角色是否存在
        Role selectRole = roleMapper.selectRoleByNameOrCode(role.getRoleName(), role.getRoleCode());
        // 角色已存在
        if (selectRole != null) {
            return Result.err(Result.CODE_ERR_BUSINESS, "角色已存在！");
        }
        // 角色不存在
        int i = roleMapper.insertRole(role);
        if (i > 0) {
            return Result.ok("角色添加成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "角色添加失败！");
    }
}
