package com.example.warehouse.service;

import com.example.warehouse.entity.InStore;
import com.example.warehouse.page.Page;
import com.example.warehouse.result.Result;

public interface InStoreService {
    /**
     * 添加入库单
     */
    Result insert(InStore inStore, Integer buyId);

    /**
     * 分页查询入库单
     */
    Page selectPage(Page page, InStore inStore);
}
