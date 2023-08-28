package com.example.warehouse.service;

import com.example.warehouse.entity.Supply;

import java.util.List;

public interface SupplyService {
    /**
     * 查询所有供应商的方法
     */
    List<Supply> selectAllSupply();
}
