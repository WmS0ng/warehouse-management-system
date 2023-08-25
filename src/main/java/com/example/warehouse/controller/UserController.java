package com.example.warehouse.controller;


import com.example.warehouse.entity.User;
import com.example.warehouse.entity.dto.CurrentUser;
import com.example.warehouse.page.Page;
import com.example.warehouse.result.Result;
import com.example.warehouse.service.UserService;
import com.example.warehouse.utils.TokenUtils;
import com.example.warehouse.utils.WarehouseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    TokenUtils tokenUtils;

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
     * 启用或禁用用户的url
     */
    @RequestMapping("/updateState")
    public Result updateUserState(@RequestBody User user) {
        // 执行任务
        return userService.updateState(user);
    }
}
