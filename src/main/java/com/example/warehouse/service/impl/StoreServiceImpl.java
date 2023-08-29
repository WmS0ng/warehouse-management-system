package com.example.warehouse.service.impl;

import com.example.warehouse.entity.Store;
import com.example.warehouse.mapper.StoreMapper;
import com.example.warehouse.service.StoreService;
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
}
