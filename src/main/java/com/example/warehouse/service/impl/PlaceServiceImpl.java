package com.example.warehouse.service.impl;

import com.example.warehouse.entity.Place;
import com.example.warehouse.mapper.PlaceMapper;
import com.example.warehouse.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "com.example.warehouse.service.impl.PlaceServiceImpl")
public class PlaceServiceImpl implements PlaceService {
    @Autowired
    private PlaceMapper placeMapper;

    /**
     * 查询所有产地
     */
    @Override
    @Cacheable(key = "'all:place'")
    public List<Place> selectAllPlace() {
        return placeMapper.selectList();
    }
}
