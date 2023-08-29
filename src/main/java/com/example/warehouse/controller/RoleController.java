package com.example.warehouse.controller;

import com.example.warehouse.dto.AssignAuthDto;
import com.example.warehouse.dto.CurrentUser;
import com.example.warehouse.entity.Role;
import com.example.warehouse.page.Page;
import com.example.warehouse.result.Result;
import com.example.warehouse.service.AuthService;
import com.example.warehouse.service.RoleService;
import com.example.warehouse.utils.TokenUtils;
import com.example.warehouse.utils.WarehouseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private AuthService authService;

    /**
     * 查询所有角色
     */
    @RequestMapping("/role-list")
    public Result roleList() {
        List<Role> roleList = roleService.selectList();
        return Result.ok(roleList);
    }

    /**
     * 分页查询所有角色
     */
    @RequestMapping("/role-page-list")
    public Result rolePageList(Page page, Role role) {
        page = roleService.selectPage(page, role);
        return Result.ok(page);
    }

    /**
     * 添加角色的接口
     */
    @RequestMapping("/role-add")
    public Result roleAdd(@RequestBody Role role, @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token) {
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        role.setCreateBy(currentUser.getUserId());
        return roleService.insert(role);
    }

    /**
     * 启用或禁用角色
     */
    @RequestMapping("/role-state-update")
    public Result roleStateUpdate(@RequestBody Role role) {
        return roleService.updateState(role);
    }

    /**
     * 根据角色id删除角色
     */
    @RequestMapping("/role-delete/{roleId}")
    public Result roleDelete(@PathVariable Integer roleId) {
        return roleService.deleteById(roleId);
    }

    /**
     * 根据角色id查询权限
     */
    @RequestMapping("/role-auth")
    public Result roleAuth(Integer roleId) {
        List<Integer> authIdList = authService.selectIdListByRoleId(roleId);
        return Result.ok(authIdList);
    }

    /**
     * 给角色分配权限
     */
    @RequestMapping("/auth-grant")
    public Result authGrant(@RequestBody AssignAuthDto assignAuthDto) {
        roleService.assignAuth(assignAuthDto);
        return Result.ok("权限分配成功！");
    }

    /**
     * 修改角色
     */
    @RequestMapping("/role-update")
    public Result roleUpdate(@RequestBody Role role, @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token) {
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        role.setUpdateBy(currentUser.getUserId());
        return roleService.update(role);
    }
}
