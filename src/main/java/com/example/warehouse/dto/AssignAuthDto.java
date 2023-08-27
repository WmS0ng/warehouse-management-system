package com.example.warehouse.dto;

import lombok.Data;

import java.util.List;


/**
 * 结构给角色分配权限请求的Dto类
 */
@Data
public class AssignAuthDto {
    private Integer roleId;
    private List<Integer> authIds;
}
