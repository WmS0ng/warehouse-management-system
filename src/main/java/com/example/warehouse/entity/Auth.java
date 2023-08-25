package com.example.warehouse.entity;

import lombok.Data;

import java.util.Date;

/**
 * 权限表
 *
 * @TableName auth_info
 */
@Data
public class Auth {
    private Integer authId;

    private Integer parentId;

    private String authName;

    private String authDesc;

    private Integer authGrade;

    private String authType;

    private String authUrl;

    private String authCode;

    private Integer authOrder;

    private String authState;

    private Integer createBy;

    private Date createTime;

    private Integer updateBy;

    private Date updateTime;
}