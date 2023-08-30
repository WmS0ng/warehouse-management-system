package com.example.warehouse.controller;

import com.example.warehouse.dto.CurrentUser;
import com.example.warehouse.entity.InStore;
import com.example.warehouse.entity.Purchase;
import com.example.warehouse.entity.Store;
import com.example.warehouse.page.Page;
import com.example.warehouse.result.Result;
import com.example.warehouse.service.InStoreService;
import com.example.warehouse.service.PurchaseService;
import com.example.warehouse.service.StoreService;
import com.example.warehouse.utils.TokenUtils;
import com.example.warehouse.utils.WarehouseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private StoreService storeService;
    @Autowired
    private InStoreService inStoreService;
    @Autowired
    private TokenUtils tokenUtils;

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

    /**
     * 删除采购单
     */
    @RequestMapping("/purchase-delete/{buyId}")
    public Result purchaseDelete(@PathVariable Integer buyId) {
        return purchaseService.deleteById(buyId);
    }

    /**
     * 修改采购单
     */
    @RequestMapping("/purchase-update")
    public Result purchaseUpdate(@RequestBody Purchase purchase) {
        return purchaseService.updateNum(purchase);
    }

    /**
     * 生成入库单
     */
    @RequestMapping("/in-warehouse-record-add")
    public Result inWarehouseRecordAdd(@RequestBody Purchase purchase, @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token) {
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        InStore inStore = new InStore();
        inStore.setCreateBy(currentUser.getUserId());
        inStore.setStoreId(purchase.getStoreId());
        inStore.setProductId(purchase.getProductId());
        inStore.setInNum(purchase.getFactBuyNum());
        return inStoreService.insert(inStore, purchase.getBuyId());
    }
}
