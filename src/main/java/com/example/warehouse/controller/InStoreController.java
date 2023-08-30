package com.example.warehouse.controller;

import com.example.warehouse.entity.InStore;
import com.example.warehouse.entity.Store;
import com.example.warehouse.page.Page;
import com.example.warehouse.result.Result;
import com.example.warehouse.service.InStoreService;
import com.example.warehouse.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/instore")
public class InStoreController {
    @Autowired
    private InStoreService inStoreService;
    @Autowired
    private StoreService storeService;

    /**
     * 查询所有仓库
     */
    @RequestMapping("/store-list")
    public Result storeList() {
        List<Store> storeList = storeService.selectList();
        return Result.ok(storeList);
    }

    /**
     * 入库单的分页查询
     */
    @RequestMapping("/instore-page-list")
    public Result instorePageList(Page page, InStore inStore) {
        page = inStoreService.selectPage(page, inStore);
        return Result.ok(page);
    }

    /**
     * 确认入库
     */
    @RequestMapping("/instore-confirm")
    public Result instoreConfirm(@RequestBody InStore inStore) {
        return inStoreService.inStoreConfirm(inStore);
    }
}
