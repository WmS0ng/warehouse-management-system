package com.example.warehouse.mapper;

import com.example.warehouse.entity.ProductType;

import java.util.List;

/**
 * @author swm
 * @description 针对表【product_type(商品分类表)】的数据库操作Mapper
 * @createDate 2023-08-28 11:12:40
 * @Entity com.example.warehouse.entity.ProductType
 */
public interface ProductTypeMapper {
    /**
     * 查询所有商品分类的方法
     */
    List<ProductType> selectAllProductType();
}




