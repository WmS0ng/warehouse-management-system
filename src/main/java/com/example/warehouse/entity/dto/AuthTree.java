package com.example.warehouse.entity.dto;

import com.example.warehouse.entity.Auth;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class AuthTree extends Auth {
    // 存放当前菜单下的所有子集菜单
    private List<AuthTree> childAuth;
}
