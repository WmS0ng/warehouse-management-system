package com.example.warehouse.service;

import com.example.warehouse.entity.OutStore;
import com.example.warehouse.result.Result;

public interface OutStoreService {
    /**
     * 添加出库单
     */
    Result insert(OutStore outStore);
}
