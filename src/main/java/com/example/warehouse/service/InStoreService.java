package com.example.warehouse.service;

import com.example.warehouse.entity.InStore;
import com.example.warehouse.result.Result;

public interface InStoreService {
    /**
     * 添加入库单
     */
    Result insert(InStore inStore, Integer buyId);
}
