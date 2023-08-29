package com.example.warehouse.service;

import com.example.warehouse.entity.ProductType;
import com.example.warehouse.result.Result;

import java.util.List;

public interface ProductTypeService {
    /**
     * 查询所有商品分类树
     */
    List<ProductType> productTypeTree();

    /**
     * 校验分类编码是否存在
     */
    Result checkTypeCode(ProductType productType);

    /**
     * 添加商品分类
     */
    Result saveProductType(ProductType productType);
}
