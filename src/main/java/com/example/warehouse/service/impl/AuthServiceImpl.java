package com.example.warehouse.service.impl;

import com.alibaba.fastjson2.JSON;
import com.example.warehouse.entity.AuthTree;
import com.example.warehouse.mapper.AuthMapper;
import com.example.warehouse.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthMapper authMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 查询用户菜单树
     */
    @Override
    public List<AuthTree> selectAuthTreeByUid(Integer userId) {
        // 先从redis中查找
        String authTreeJson = stringRedisTemplate.opsForValue().get("authTree" + userId);
        if (StringUtils.hasText(authTreeJson)) {
            return JSON.parseArray(authTreeJson, AuthTree.class);
        }
        // redis中不存在在从mysql中查找
        List<AuthTree> authList = authMapper.findAuthListByUid(userId);
        // 将查找出来的authList转换成authTree
        List<AuthTree> authTree = allAuthListToAuthTreeList(authList, 0);
        // 存入redis中一份，方便下次查询
        stringRedisTemplate.opsForValue().set("authTree" + userId, JSON.toJSONString(authTree));
        return authTree;
    }


    private List<AuthTree> allAuthListToAuthTreeList(List<AuthTree> authList, Integer pid) {
        // 查询所有一级菜单
        List<AuthTree> firstLevelAuthList = new ArrayList<>();
        for (AuthTree authTree : authList) {
            if (authTree.getParentId().equals(pid)) {
                firstLevelAuthList.add(authTree);
            }
        }

        // 拿到每个一级菜单的所有二级菜单
        for (AuthTree firstAuth : firstLevelAuthList) {
            List<AuthTree> secondLevelAuthList = allAuthListToAuthTreeList(authList, firstAuth.getAuthId());
            firstAuth.setChildAuth(secondLevelAuthList);
        }

        return firstLevelAuthList;
    }
}
