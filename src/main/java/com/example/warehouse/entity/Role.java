package com.example.warehouse.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @TableName role
 */
@Data
public class Role implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Integer roleId;
    private String roleName;
    private String roleDesc;
    private String roleCode;
    private String roleState;
    private Integer createBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private Integer updateBy;
    private Date updateTime;
    // 追加属性
    private String getCode;
}