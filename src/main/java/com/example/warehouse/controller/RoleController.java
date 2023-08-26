package com.example.warehouse.controller;

import com.example.warehouse.entity.Role;
import com.example.warehouse.page.Page;
import com.example.warehouse.result.Result;
import com.example.warehouse.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

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
}
