package com.example.warehouse.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 权限表
 *
 * @TableName auth_info
 */
@Data
public class Auth implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
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
    // 追加属性
    private List<Auth> childAuth;
}