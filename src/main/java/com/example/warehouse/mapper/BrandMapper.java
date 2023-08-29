package com.example.warehouse.mapper;

import com.example.warehouse.entity.Brand;

import java.util.List;

/**
 * @author swm
 * @description 针对表【brand(品牌)】的数据库操作Mapper
 * @createDate 2023-08-28 11:12:20
 * @Entity com.example.warehouse.entity.Brand
 */
public interface BrandMapper {
    /**
     * 查询所有品牌
     */
    List<Brand> selectList();
}