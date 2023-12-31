package com.example.warehouse.service;

import com.example.warehouse.entity.Purchase;
import com.example.warehouse.page.Page;
import com.example.warehouse.result.Result;

public interface PurchaseService {
    /**
     * 添加采购单
     */
    Result insert(Purchase purchase);

    /**
     * 分页查询采购单
     */
    Page selectPage(Page page, Purchase purchase);

    /**
     * 删除采购单
     */
    Result deleteById(Integer buyId);

    /**
     * 修改采购单的采购数量
     */
    Result updateNum(Purchase purchase);
}
