package com.example.warehouse.service.impl;

import com.example.warehouse.entity.Purchase;
import com.example.warehouse.mapper.PurchaseMapper;
import com.example.warehouse.page.Page;
import com.example.warehouse.result.Result;
import com.example.warehouse.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    @Autowired
    private PurchaseMapper purchaseMapper;

    /**
     * 添加采购单
     */
    @Override
    public Result insert(Purchase purchase) {
        // purchase.setFactBuyNum(purchase.getBuyNum());
        int i = purchaseMapper.insert(purchase);
        if (i > 0) {
            return Result.ok("采购单添加成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "采购单添加失败！");
    }

    /**
     * 分页查询
     */
    @Override
    public Page selectPage(Page page, Purchase purchase) {
        int count = purchaseMapper.countToTal(purchase);
        List<Purchase> purchaseList = purchaseMapper.selectPage(page, purchase);
        page.setTotalNum(count);
        page.setResultList(purchaseList);
        return page;
    }

    /**
     * 根据id删除采购单
     */
    @Override
    public Result deleteById(Integer buyId) {
        int i = purchaseMapper.deleteById(buyId);
        if (i > 0) {
            return Result.ok("采购单删除成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "采购单删除失败！");
    }

    /**
     * 修改采购单的采购数量
     */
    @Override
    public Result updateNum(Purchase purchase) {
        int i = purchaseMapper.updateNum(purchase);
        if (i > 0) {
            return Result.ok("修改采购单成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "修改采购单失败");
    }
}
