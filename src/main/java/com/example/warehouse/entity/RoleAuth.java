package com.example.warehouse.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @TableName role_auth
 */
@Data
public class RoleAuth implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Integer roleAuthId;
    private Integer roleId;
    private Integer authId;
}