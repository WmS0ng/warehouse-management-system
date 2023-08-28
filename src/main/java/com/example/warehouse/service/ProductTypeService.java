package com.example.warehouse.service;

import com.example.warehouse.entity.ProductType;

import java.util.List;

public interface ProductTypeService {
    /**
     * 查询所有商品分类树
     */
    List<ProductType> productTypeTree();
}
