package com.example.warehouse.controller;

import com.example.warehouse.entity.Store;
import com.example.warehouse.page.Page;
import com.example.warehouse.result.Result;
import com.example.warehouse.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/store")
public class StoreController {
    @Autowired
    private StoreService storeService;

    @RequestMapping("/store-page-list")
    public Result storePageList(Page page, Store store) {
        page = storeService.selectPage(page, store);
        return Result.ok(page);
    }
}
