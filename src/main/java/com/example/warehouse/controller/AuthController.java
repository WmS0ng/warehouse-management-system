package com.example.warehouse.controller;

import com.example.warehouse.entity.Auth;
import com.example.warehouse.result.Result;
import com.example.warehouse.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    /**
     * 查询所有菜单树
     */
    @RequestMapping("/auth-tree")
    public Result loadAllAuthTree() {
        List<Auth> authTreeList = authService.selectAuthTree();
        return Result.ok(authTreeList);
    }
}
