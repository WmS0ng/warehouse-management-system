package com.example.warehouse.service.impl;

import com.example.warehouse.entity.InStore;
import com.example.warehouse.mapper.InStoreMapper;
import com.example.warehouse.mapper.ProductMapper;
import com.example.warehouse.mapper.PurchaseMapper;
import com.example.warehouse.page.Page;
import com.example.warehouse.result.Result;
import com.example.warehouse.service.InStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InStoreServiceImpl implements InStoreService {
    @Autowired
    private InStoreMapper inStoreMapper;
    @Autowired
    private PurchaseMapper purchaseMapper;
    @Autowired
    private ProductMapper productMapper;

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

    /**
     * 分页查询入库单
     */
    @Override
    public Page selectPage(Page page, InStore inStore) {
        int count = inStoreMapper.countTotal(inStore);
        List<InStore> inStoreList = inStoreMapper.selectPage(page, inStore);
        page.setTotalNum(count);
        page.setResultList(inStoreList);
        return page;
    }

    /**
     * 确认入库
     */
    @Override
    @Transactional
    public Result inStoreConfirm(InStore inStore) {
        int i = inStoreMapper.updateIsIn(inStore.getInsId());
        if (i > 0) {
            int j = productMapper.updateInvent(inStore.getProductId(), inStore.getInNum());
            if (j > 0) {
                return Result.ok("入库单确认成功！");
            }
            return Result.err(Result.CODE_ERR_BUSINESS, "入库单确认失败！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "入库单确认失败！");
    }
}
