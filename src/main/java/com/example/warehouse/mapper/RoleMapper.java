package com.example.warehouse.mapper;

import com.example.warehouse.entity.Role;

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
}




