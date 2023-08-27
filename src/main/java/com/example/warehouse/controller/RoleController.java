package com.example.warehouse.controller;

import com.example.warehouse.dto.CurrentUser;
import com.example.warehouse.entity.Role;
import com.example.warehouse.page.Page;
import com.example.warehouse.result.Result;
import com.example.warehouse.service.RoleService;
import com.example.warehouse.utils.TokenUtils;
import com.example.warehouse.utils.WarehouseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private TokenUtils tokenUtils;

    /**
     * 查询所有角色
     */
    @RequestMapping("/role-list")
    public Result roleList() {
        List<Role> roleList = roleService.selectAllRole();
        return Result.ok(roleList);
    }

    /**
     * 分页查询所有角色
     */
    @RequestMapping("/role-page-list")
    public Result roleListPage(Page page, Role role) {
        page = roleService.selectRolePage(page, role);
        return Result.ok(page);
    }

    /**
     * 添加角色的接口
     */
    @RequestMapping("/role-add")
    public Result addRole(@RequestBody Role role, @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token) {
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        role.setCreateBy(currentUser.getUserId());
        return roleService.insertRole(role);
    }
}
