package com.example.warehouse.service.impl;

import com.example.warehouse.entity.OutStore;
import com.example.warehouse.mapper.OutStoreMapper;
import com.example.warehouse.mapper.ProductMapper;
import com.example.warehouse.page.Page;
import com.example.warehouse.result.Result;
import com.example.warehouse.service.OutStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OutStoreServiceImpl implements OutStoreService {
    @Autowired
    private OutStoreMapper outStoreMapper;
    @Autowired
    private ProductMapper productMapper;

    /**
     * 添加出库单
     */
    @Override
    public Result insert(OutStore outStore) {
        int i = outStoreMapper.insert(outStore);
        if (i > 0) {
            return Result.ok("出库单添加成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "出库单添加失败！");
    }

    /**
     * 分页查询出库单
     */
    @Override
    public Page selectPage(Page page, OutStore outStore) {
        int count = outStoreMapper.countTotal(outStore);
        List<OutStore> outStoreList = outStoreMapper.selectPage(page, outStore);
        page.setTotalNum(count);
        page.setResultList(outStoreList);
        return page;
    }

    /**
     * 确认出库
     */
    @Override
    @Transactional
    public Result outStoreConfirm(OutStore outStore) {
        int invent = productMapper.selectInventById(outStore.getProductId());
        if (invent < outStore.getOutsId()) {
            return Result.err(Result.CODE_ERR_BUSINESS, "商品库存不足！");
        }
        int i = outStoreMapper.updateIsOut(outStore.getOutsId());
        if (i > 0) {
            int j = productMapper.updateInvent(outStore.getProductId(), -outStore.getOutNum());
            if (j > 0) {
                return Result.ok("确认出库成功！");
            }
            return Result.err(Result.CODE_ERR_BUSINESS, "确认出库失败！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "确认出库失败！");
    }
}
