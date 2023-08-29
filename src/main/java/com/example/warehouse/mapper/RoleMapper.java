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
    List<Role> selectList();

    /**
     * 根据userId查询所有角色
     */
    List<Role> selectListByUserId(Integer userId);

    /**
     * 根据角色名查询角色id的方法
     */
    Integer selectIdByName(String roleName);

    /**
     * 查询所有角色行数
     */
    Integer countTotal(Role role);

    /**
     * 分页查询角色
     */
    List<Role> selectPage(@Param("page") Page page, @Param("role") Role role);

    /**
     * 根据角色名称或角色代码查询角色
     */
    Role selectByNameOrCode(@Param("roleName") String roleName, @Param("roleCode") String roleCode);

    /**
     * 添加角色
     */
    int insert(Role role);

    /**
     * 根据角色id修改角色状态
     */
    int updateStateById(@Param("roleId") Integer roleId, @Param("roleState") String roleState);

    /**
     * 根据角色id删除角色
     */
    int deleteById(Integer roleId);

    /**
     * 根据角色id修改角色描述
     */
    int updateDescById(Role role);
}