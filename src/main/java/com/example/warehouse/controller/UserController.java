package com.example.warehouse.controller;


import com.example.warehouse.dto.AssignRoleDto;
import com.example.warehouse.dto.CurrentUser;
import com.example.warehouse.entity.Role;
import com.example.warehouse.entity.User;
import com.example.warehouse.page.Page;
import com.example.warehouse.result.Result;
import com.example.warehouse.service.RoleService;
import com.example.warehouse.service.UserService;
import com.example.warehouse.utils.TokenUtils;
import com.example.warehouse.utils.WarehouseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private RoleService roleService;

    /**
     * 用户的分页查询
     */
    @RequestMapping("/user-list")
    public Result userList(Page page, User user) {
        page = userService.selectUserList(page, user);

        return Result.ok(page);
    }

    /**
     * 添加用户
     */
    @RequestMapping("/addUser")
    public Result addUser(@RequestBody User user, @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token) {
        // 拿到当前登陆用户的id，并设置
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        int createBy = currentUser.getUserId();
        user.setCreateBy(createBy);

        // 执行业务
        return userService.insertUser(user);
    }

    /**
     * 启用或禁用用户
     */
    @RequestMapping("/updateState")
    public Result updateUserState(@RequestBody User user) {
        // 执行任务
        return userService.updateState(user);
    }

    /**
     * 根据userId查询对应角色
     */
    @RequestMapping("/user-role-list/{userId}")
    public Result userRoleList(@PathVariable Integer userId) {
        List<Role> roleList = roleService.selectRoleListByUid(userId);

        return Result.ok(roleList);
    }

    /**
     * 给用户分配角色
     */
    @RequestMapping("/assignRole")
    public Result assignRole(@RequestBody AssignRoleDto assignRoleDto) {
        userService.assignRole(assignRoleDto);
        return Result.ok("分配角色成功");
    }

    /**
     * 根据用户id删除用户
     */
    @RequestMapping("/deleteUser/{userId}")
    public Result deleteUserByUid(@PathVariable Integer userId) {
        ArrayList<Integer> userIdList = new ArrayList<>();
        userIdList.add(userId);
        return userService.deleteUserByIds(userIdList);
    }

    /**
     * 根据用户ids批量删除用户
     */
    @RequestMapping("/deleteUserList")
    public Result deleteUsersByUids(@RequestBody List<Integer> userIdList) {
        return userService.deleteUserByIds(userIdList);
    }

    /**
     * 修改userName
     */
    @RequestMapping("/updateUser")
    public Result updateUserName(@RequestBody User user, @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token) {
        user.setUpdateBy(tokenUtils.getCurrentUser(token).getUserId());
        return userService.updateUserName(user);
    }
}
