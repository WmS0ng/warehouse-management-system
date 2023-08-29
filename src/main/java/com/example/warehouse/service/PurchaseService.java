package com.example.warehouse.service;

import com.example.warehouse.entity.Purchase;
import com.example.warehouse.result.Result;

public interface PurchaseService {
    /**
     * 添加采购单
     */
    Result insertPurchase(Purchase purchase);
}
