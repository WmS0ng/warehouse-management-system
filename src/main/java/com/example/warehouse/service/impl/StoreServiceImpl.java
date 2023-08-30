package com.example.warehouse.service.impl;

import com.example.warehouse.entity.Store;
import com.example.warehouse.mapper.StoreMapper;
import com.example.warehouse.page.Page;
import com.example.warehouse.service.StoreService;
import com.example.warehouse.vo.StoreCountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "com.example.warehouse.service.impl.StoreServiceImpl")
public class StoreServiceImpl implements StoreService {
    @Autowired
    private StoreMapper storeMapper;

    /**
     * 查询所有仓库
     */
    @Cacheable(key = "'all:store'")
    @Override
    public List<Store> selectList() {
        return storeMapper.selectList();
    }

    /**
     * 查询每个仓库商品的数量
     */
    @Override
    public List<StoreCountVo> selectStoreCount() {
        return storeMapper.selectStoreCount();
    }

    /**
     * 分页查询仓库
     */
    @Override
    public Page selectPage(Page page, Store store) {
        int count = storeMapper.countTotal(store);
        List<Store> storeList = storeMapper.selectPage(page, store);
        page.setTotalNum(count);
        page.setResultList(storeList);
        return page;
    }
}
