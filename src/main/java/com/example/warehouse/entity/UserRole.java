package com.example.warehouse.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @TableName user_role
 */
@Data
public class UserRole implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Integer userRoleId;
    private Integer roleId;
    private Integer userId;
}