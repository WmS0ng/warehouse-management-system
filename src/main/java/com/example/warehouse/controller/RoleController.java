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

    /**
     * 启用或禁用角色
     */
    @RequestMapping("/role-state-update")
    public Result updateRoleStateByRid(@RequestBody Role role) {
        return roleService.updateRoleStateByRid(role);
    }

    /**
     * 根据角色id删除角色
     */
    @RequestMapping("/role-delete/{roleId}")
    public Result deleteRoleByRid(@PathVariable Integer roleId) {
        return roleService.deleteRoleByRid(roleId);
    }

    /**
     * 根据角色id查询权限
     */
    @RequestMapping("/role-auth")
    public Result selectAuthByRid(Integer roleId) {
        List<Integer> authIdList = authService.selectAuthIdListByRid(roleId);

        return Result.ok(authIdList);
    }

    /**
     * 给角色分配权限
     */
    @RequestMapping("/auth-grant")
    public Result grantAuth(@RequestBody AssignAuthDto assignAuthDto) {
        roleService.insertRoleAuth(assignAuthDto);
        return Result.ok("权限分配成功！");
    }

    /**
     * 修改角色
     */
    @RequestMapping("/role-update")
    public Result updateRole(@RequestBody Role role, @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token) {
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        role.setUpdateBy(currentUser.getUserId());
        return roleService.updateRoleByRid(role);
    }
}
