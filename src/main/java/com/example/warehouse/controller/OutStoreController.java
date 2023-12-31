package com.example.warehouse.controller;

import com.example.warehouse.dto.CurrentUser;
import com.example.warehouse.entity.OutStore;
import com.example.warehouse.entity.Store;
import com.example.warehouse.page.Page;
import com.example.warehouse.result.Result;
import com.example.warehouse.service.OutStoreService;
import com.example.warehouse.service.StoreService;
import com.example.warehouse.utils.TokenUtils;
import com.example.warehouse.utils.WarehouseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("outstore")
public class OutStoreController {
    @Autowired
    private OutStoreService outStoreService;
    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private StoreService storeService;

    /**
     * 添加出库单
     */
    @RequestMapping("/outstore-add")
    public Result outStoreAdd(@RequestBody OutStore outStore, @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token) {
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        outStore.setCreateBy(currentUser.getUserId());
        return outStoreService.insert(outStore);
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
     * 分页查询出库单
     */
    @RequestMapping("/outstore-page-list")
    public Result outStorePageList(Page page, OutStore outStore) {
        page = outStoreService.selectPage(page, outStore);
        return Result.ok(page);
    }

    /**
     * 确认出库
     */
    @RequestMapping("/outstore-confirm")
    public Result outStoreConfirm(@RequestBody OutStore outStore) {
        return outStoreService.outStoreConfirm(outStore);
    }
}
