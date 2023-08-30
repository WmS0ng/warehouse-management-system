package com.example.warehouse.controller;

import com.example.warehouse.entity.Purchase;
import com.example.warehouse.entity.Store;
import com.example.warehouse.page.Page;
import com.example.warehouse.result.Result;
import com.example.warehouse.service.PurchaseService;
import com.example.warehouse.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private StoreService storeService;

    /**
     * 添加采购单
     */
    @RequestMapping("/purchase-add")
    public Result purchaseAdd(@RequestBody Purchase purchase) {
        return purchaseService.insert(purchase);
    }

    /**
     * 查询所有仓库
     */
    @RequestMapping("/store-list")
    public Result storeList() {
        List<Store> storeList = storeService.selectList();
        return Result.ok(storeList);
    }

    /**
     * 分页查询采购单
     */
    @RequestMapping("/purchase-page-list")
    public Result purchasePageList(Page page, Purchase purchase) {
        page = purchaseService.selectPage(page, purchase);
        return Result.ok(page);
    }
}
