package com.example.warehouse.mapper;

import com.example.warehouse.entity.UserRole;

/**
 * @author swm
 * @description 针对表【user_role(用户角色表)】的数据库操作Mapper
 * @createDate 2023-08-26 13:20:17
 * @Entity com.example.warehouse.entity.UserRole
 */
public interface UserRoleMapper {

    /**
     * 根据用户id删除用户已分配的用户角色关系
     */
    int deleteUserRoleByUid(Integer userId);

    /**
     * 添加用户角色关系的方法
     */
    int insertUserRole(UserRole userRole);
}




