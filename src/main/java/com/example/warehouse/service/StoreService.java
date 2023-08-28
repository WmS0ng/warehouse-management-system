package com.example.warehouse.service;

import com.example.warehouse.entity.Store;

import java.util.List;

public interface StoreService {
    /**
     * 查询所有仓库
     */
    List<Store> selectAllStore();
}
