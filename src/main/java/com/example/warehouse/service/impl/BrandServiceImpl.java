package com.example.warehouse.service.impl;

import com.example.warehouse.entity.Brand;
import com.example.warehouse.mapper.BrandMapper;
import com.example.warehouse.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "com.example.warehouse.service.impl.BrandServiceImpl")
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandMapper brandMapper;

    /**
     * 查询所有品牌
     */
    @Cacheable(key = "'all:brand'")
    @Override
    public List<Brand> selectList() {
        return brandMapper.selectList();
    }
}
