package com.example.warehouse.service.impl;

import com.example.warehouse.entity.Unit;
import com.example.warehouse.mapper.UnitMapper;
import com.example.warehouse.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "com.example.warehouse.service.impl.UnitServiceImpl")
public class UnitServiceImpl implements UnitService {
    @Autowired
    private UnitMapper unitMapper;

    /**
     * 查询所有单位
     */
    @Cacheable(key = "'all:unit'")
    @Override
    public List<Unit> selectList() {
        return unitMapper.selectList();
    }
}
