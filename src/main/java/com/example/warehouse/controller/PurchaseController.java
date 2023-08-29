package com.example.warehouse.controller;

import com.example.warehouse.entity.Purchase;
import com.example.warehouse.result.Result;
import com.example.warehouse.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    /**
     * 添加采购单
     */
    @RequestMapping("/purchase-add")
    public Result purchaseAdd(@RequestBody Purchase purchase) {
        return purchaseService.insert(purchase);
    }
}
