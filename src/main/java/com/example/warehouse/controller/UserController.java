package com.example.warehouse.controller;


import com.example.warehouse.entity.User;
import com.example.warehouse.page.Page;
import com.example.warehouse.result.Result;
import com.example.warehouse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    /**
     * 用户的分页查询
     */
    @RequestMapping("/user-list")
    public Result userList(Page page, User user) {
        page = userService.selectUserList(page, user);

        return Result.ok(page);
    }
}
