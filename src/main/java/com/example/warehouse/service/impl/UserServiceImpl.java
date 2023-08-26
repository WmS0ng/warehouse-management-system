package com.example.warehouse.service.impl;

import com.example.warehouse.dto.AssignRoleDto;
import com.example.warehouse.entity.User;
import com.example.warehouse.entity.UserRole;
import com.example.warehouse.mapper.RoleMapper;
import com.example.warehouse.mapper.UserMapper;
import com.example.warehouse.mapper.UserRoleMapper;
import com.example.warehouse.page.Page;
import com.example.warehouse.result.Result;
import com.example.warehouse.service.UserService;
import com.example.warehouse.utils.DigestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    // 注入UserMapper
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public User selectUserByCode(String userCode) {
        return userMapper.selectUserByCode(userCode);
    }

    @Override
    @Transactional
    public Page selectUserList(Page page, User user) {
        // 查询总用户数量
        Integer count = userMapper.selectUserRowCount(user);
        // 分页查询
        List<User> userList = userMapper.selectUserList(page, user);

        page.setTotalNum(count);
        page.setResultList(userList);

        return page;
    }

    @Override
    public Result insertUser(User user) {
        // 判断账号是否已存在
        User u = userMapper.selectUserByCode(user.getUserCode());
        // 账号已存在
        if (u != null) {
            return Result.err(Result.CODE_ERR_BUSINESS, "账号已经存在！");
        }

        String password = DigestUtil.hmacSign(user.getUserPwd());
        user.setUserPwd(password);

        // 执行添加
        int i = userMapper.insertUser(user);
        if (i > 0) {
            return Result.ok("用户添加成功！");
        }

        return Result.err(Result.CODE_ERR_BUSINESS, "用户添加失败！");
    }


    /**
     * 启用或禁用用户
     */
    @Override
    public Result updateState(User user) {
        int i = userMapper.updateStateByUid(user.getUserId(), user.getUserState());
        if (i > 0) {
            return Result.ok("启用或禁用成功！");
        }

        return Result.err(Result.CODE_ERR_BUSINESS, "启用或禁用失败！");
    }


    /**
     * 给用户分配角色
     */
    @Override
    @Transactional // 事物处理
    public void assignRole(AssignRoleDto assignRoleDto) {
        userRoleMapper.deleteUserRoleByUid(assignRoleDto.getUserId());
        List<String> roleNameList = assignRoleDto.getRoleCheckList();
        for (String roleName : roleNameList) {
            Integer roleId = roleMapper.selectRoleIdByName(roleName);
            UserRole userRole = new UserRole();
            userRole.setUserId(assignRoleDto.getUserId());
            userRole.setRoleId(roleId);
            userRoleMapper.insertUserRole(userRole);
        }
    }

    /**
     * 批量删除用户
     */
    @Override
    public Result deleteUserByIds(List<Integer> userIdList) {
        int i = userMapper.updateIsDeleteByUidList(userIdList);
        if (i > 0) {
            return Result.ok("用户删除成功");
        }

        return Result.err(Result.CODE_ERR_BUSINESS, "用户删除失败");
    }

    /**
     * 修改userName
     */
    @Override
    public Result updateUserName(User user) {
        int i = userMapper.updateUserName(user);
        if (i > 0) {
            return Result.ok("修改用户成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "修改用户失败！");
    }
}
