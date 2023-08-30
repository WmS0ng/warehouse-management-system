package com.example.warehouse.service.impl;

import com.example.warehouse.entity.InStore;
import com.example.warehouse.mapper.InStoreMapper;
import com.example.warehouse.mapper.PurchaseMapper;
import com.example.warehouse.result.Result;
import com.example.warehouse.service.InStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InStoreServiceImpl implements InStoreService {
    @Autowired
    private InStoreMapper inStoreMapper;
    @Autowired
    private PurchaseMapper purchaseMapper;

    /**
     * 添加入库单
     */
    @Override
    @Transactional
    public Result insert(InStore inStore, Integer buyId) {
        int i = inStoreMapper.insert(inStore);
        if (i > 0) {
            int j = purchaseMapper.updateIsIn(buyId);
            if (j > 0) {
                return Result.ok("入库单添加成功");
            }
            return Result.err(Result.CODE_ERR_BUSINESS, "入库单添加失败！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "入库单添加失败！");
    }
}
