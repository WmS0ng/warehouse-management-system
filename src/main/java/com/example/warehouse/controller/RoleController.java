package com.example.warehouse.controller;

import com.example.warehouse.entity.Role;
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

    @RequestMapping("/role-list")
    public Result roleList() {
        List<Role> roleList = roleService.selectAllRole();
        return Result.ok(roleList);
    }
}
