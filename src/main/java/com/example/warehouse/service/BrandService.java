package com.example.warehouse.service;

import com.example.warehouse.entity.Brand;

import java.util.List;

public interface BrandService {
    /**
     * 查询所有品牌
     */
    List<Brand> selectAllBrand();
}
