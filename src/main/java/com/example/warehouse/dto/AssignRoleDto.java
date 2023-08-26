package com.example.warehouse.dto;


import lombok.Data;

import java.util.List;

/**
 * 接受分配角色请求json数据的Dto类
 */
@Data
public class AssignRoleDto {
    private Integer userId;
    private List<String> roleCheckList;
}
