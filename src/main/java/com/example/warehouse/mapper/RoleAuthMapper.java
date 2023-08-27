package com.example.warehouse.mapper;

import java.util.List;

/**
 * @author swm
 * @description 针对表【role_auth(角色权限表)】的数据库操作Mapper
 * @createDate 2023-08-27 14:15:31
 * @Entity com.example.warehouse.entity.RoleAuth
 */
public interface RoleAuthMapper {
    /**
     * 根据角色id删除角色关系
     */
    int deleteRoleAuthByRid(Integer roleId);


    /**
     * 根据roleId查询权限id
     */
    List<Integer> selectAuthIdListByRid(Integer roleId);
}




