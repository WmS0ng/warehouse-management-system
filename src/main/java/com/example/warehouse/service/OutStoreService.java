package com.example.warehouse.service;

import com.example.warehouse.entity.OutStore;
import com.example.warehouse.page.Page;
import com.example.warehouse.result.Result;

public interface OutStoreService {
    /**
     * 添加出库单
     */
    Result insert(OutStore outStore);

    /**
     * 分页查询出库单
     */
    Page selectPage(Page page, OutStore outStore);

    /**
     * 确认出库
     */
    Result outStoreConfirm(OutStore outStore);
}
