package com.example.warehouse.service.impl;

import com.alibaba.fastjson2.JSON;
import com.example.warehouse.entity.Auth;
import com.example.warehouse.mapper.AuthMapper;
import com.example.warehouse.mapper.RoleAuthMapper;
import com.example.warehouse.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@CacheConfig(cacheNames = "com.example.warehouse.service.impl.AuthServiceImpl")
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthMapper authMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RoleAuthMapper roleAuthMapper;

    /**
     * 查询用户菜单树
     */
    @Override
    public List<Auth> selectAuthTreeByUid(Integer userId) {
        // 先从redis中查找
        String authTreeJson = stringRedisTemplate.opsForValue().get("authTree" + userId);
        if (StringUtils.hasText(authTreeJson)) {
            return JSON.parseArray(authTreeJson, Auth.class);
        }
        // redis中不存在在从mysql中查找
        List<Auth> authList = authMapper.selectListById(userId);
        // 将查找出来的authList转换成authTree
        List<Auth> authTree = allAuthListToAuthTreeList(authList, 0);
        // 存入redis中一份，方便下次查询
        stringRedisTemplate.opsForValue().set("authTree" + userId, JSON.toJSONString(authTree));
        return authTree;
    }

    /**
     * 查询所有标签树
     */
    @Override
    @Cacheable("'all:authTreeList'")
    public List<Auth> selectAuthTree() {
        List<Auth> authList = authMapper.selectList();

        return allAuthListToAuthTreeList(authList, 0);
    }

    /**
     * 根据roleId查询authIdList
     */
    @Override
    public List<Integer> selectAuthIdListByRid(Integer roleId) {
        return roleAuthMapper.selectIdListByRoleId(roleId);
    }

    private List<Auth> allAuthListToAuthTreeList(List<Auth> authList, Integer pid) {
        // 查询所有一级菜单
        List<Auth> firstLevelAuthList = new ArrayList<>();
        for (Auth authTree : authList) {
            if (authTree.getParentId().equals(pid)) {
                firstLevelAuthList.add(authTree);
            }
        }

        // 拿到每个一级菜单的所有二级菜单
        for (Auth firstAuth : firstLevelAuthList) {
            List<Auth> secondLevelAuthList = allAuthListToAuthTreeList(authList, firstAuth.getAuthId());
            firstAuth.setChildAuth(secondLevelAuthList);
        }

        return firstLevelAuthList;
    }
}
