package com.example.warehouse.mapper;

import com.example.warehouse.entity.Role;
import com.example.warehouse.page.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author swm
 * @description 针对表【role(角色表)】的数据库操作Mapper
 * @createDate 2023-08-25 19:39:08
 * @Entity com.example.warehouse.entity.Role
 */
public interface RoleMapper {

    /**
     * 查询所有角色的方法
     */
    List<Role> selectAllRole();

    /**
     * 根据userId查询所有角色
     */
    List<Role> selectRoleListByUid(Integer userId);

    /**
     * 根据角色名查询角色id的方法
     */
    Integer selectRoleIdByName(String roleName);

    /**
     * 查询所有角色行数
     */
    Integer selectRoleRowCount(Role role);

    /**
     * 分页查询角色
     */
    List<Role> selectRolePage(@Param("page") Page page, @Param("role") Role role);

    /**
     * 根据角色名称或角色代码查询角色
     */
    Role selectRoleByNameOrCode(@Param("roleName") String roleName, @Param("roleCode") String roleCode);

    /**
     * 添加角色
     */
    int insertRole(Role role);

    /**
     * 根据角色id修改角色状态
     */
    int updateRoleStateByRid(@Param("roleId") Integer roleId, @Param("roleState") String roleState);
}