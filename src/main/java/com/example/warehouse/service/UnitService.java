package com.example.warehouse.service;

import com.example.warehouse.entity.Unit;

import java.util.List;

public interface UnitService {
    /**
     * 查询所有单位
     */
    List<Unit> selectList();
}
