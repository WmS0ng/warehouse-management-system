package com.example.warehouse.service.impl;

import com.example.warehouse.dto.AssignAuthDto;
import com.example.warehouse.entity.Role;
import com.example.warehouse.entity.RoleAuth;
import com.example.warehouse.mapper.RoleAuthMapper;
import com.example.warehouse.mapper.RoleMapper;
import com.example.warehouse.page.Page;
import com.example.warehouse.result.Result;
import com.example.warehouse.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
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
    @Autowired
    private RoleAuthMapper roleAuthMapper;

    /**
     * 查询所有角色的业务方法
     */
    @Cacheable(key = "'all:role'")
    @Override
    public List<Role> selectList() {
        return roleMapper.selectList();
    }


    /**
     * 根据userId查询所有角色
     */
    @Override
    public List<Role> selectListByUserId(Integer userId) {
        return roleMapper.selectListByUserId(userId);
    }

    /**
     * 角色的分页查询
     */
    @Override
    @Transactional
    public Page selectPage(Page page, Role role) {
        Integer count = roleMapper.countTotal(role);
        List<Role> roleList = roleMapper.selectPage(page, role);
        page.setTotalNum(count);
        page.setResultList(roleList);
        return page;
    }

    /**
     * 插入角色
     */
    @Override
    @CacheEvict(key = "'all:role'")
    public Result insert(Role role) {
        // 判断角色是否存在
        Role selectRole = roleMapper.selectByNameOrCode(role.getRoleName(), role.getRoleCode());
        // 角色已存在
        if (selectRole != null) {
            return Result.err(Result.CODE_ERR_BUSINESS, "角色已存在！");
        }
        // 角色不存在
        int i = roleMapper.insert(role);
        if (i > 0) {
            return Result.ok("角色添加成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "角色添加失败！");
    }

    /**
     * 启动或禁用角色
     */
    @Override
    @CacheEvict(key = "'all:role'")
    public Result updateState(Role role) {
        int i = roleMapper.updateState(role.getRoleId(), role.getRoleState());
        if (i > 0) {
            return Result.ok("启用或禁用角色成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "启用或禁用角色失败！");
    }

    /**
     * 根据角色id删除角色
     */
    @CacheEvict(key = "'all:role'")
    @Transactional
    @Override
    public Result deleteById(Integer roleId) {
        int i = roleMapper.deleteById(roleId);
        if (i > 0) {
            // 删除角色权限关系
            roleAuthMapper.deleteByRoleId(roleId);
            return Result.ok("角色删除成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "角色删除失败！");
    }

    /**
     * 给角色分配权限
     */
    @Override
    @Transactional
    public void assignAuth(AssignAuthDto assignAuthDto) {
        // 删除角色之前的所有权限
        roleAuthMapper.deleteByRoleId(assignAuthDto.getRoleId());
        // 添加角色权限关系
        List<Integer> authIdList = assignAuthDto.getAuthIds();
        for (Integer authId : authIdList) {
            RoleAuth roleAuth = new RoleAuth();
            roleAuth.setRoleId(assignAuthDto.getRoleId());
            roleAuth.setAuthId(authId);
            roleAuthMapper.insert(roleAuth);
        }
    }

    /**
     * 修改角色
     */
    @Override
    @CacheEvict(key = "'all:role'")
    public Result update(Role role) {
        int i = roleMapper.updateDesc(role);
        if (i > 0) {
            return Result.ok("角色修改成功！");
        }

        return Result.err(Result.CODE_ERR_BUSINESS, "角色修改失败！");
    }
}
