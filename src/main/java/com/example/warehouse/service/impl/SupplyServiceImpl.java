package com.example.warehouse.service.impl;

import com.example.warehouse.entity.Supply;
import com.example.warehouse.mapper.SupplyMapper;
import com.example.warehouse.service.SupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "com.example.warehouse.service.impl.SupplyServiceImpl")
public class SupplyServiceImpl implements SupplyService {
    @Autowired
    private SupplyMapper supplyMapper;

    /**
     * 查询所有供应商
     */
    @Override
    @Cacheable(key = "'all:supply'")
    public List<Supply> selectList() {
        return supplyMapper.selectList();
    }
}
