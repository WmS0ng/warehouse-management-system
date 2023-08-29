package com.example.warehouse.service.impl;

import com.example.warehouse.entity.OutStore;
import com.example.warehouse.mapper.OutStoreMapper;
import com.example.warehouse.result.Result;
import com.example.warehouse.service.OutStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OutStoreServiceImpl implements OutStoreService {
    @Autowired
    private OutStoreMapper outStoreMapper;

    /**
     * 添加出库单
     */
    @Override
    public Result insertOutStore(OutStore outStore) {
        int i = outStoreMapper.insert(outStore);
        if (i > 0) {
            return Result.ok("出库单添加成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "出库单添加失败！");
    }
}
