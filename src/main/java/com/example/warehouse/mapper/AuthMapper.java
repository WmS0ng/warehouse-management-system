package com.example.warehouse.mapper;

import com.example.warehouse.dto.AuthTree;

import java.util.List;

/**
 * @author swm
 * @description 针对表【auth_info(权限表)】的数据库操作Mapper
 * @createDate 2023-08-25 11:21:28
 * @Entity com.example.warehouse.entity.Auth
 */
public interface AuthMapper {
    List<AuthTree> findAuthListByUid(Integer userId);
}




