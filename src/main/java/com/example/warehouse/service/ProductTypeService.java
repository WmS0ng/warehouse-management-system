package com.example.warehouse.service;

import com.example.warehouse.entity.ProductType;
import com.example.warehouse.result.Result;

import java.util.List;

public interface ProductTypeService {
    /**
     * 查询所有商品分类树
     */
    List<ProductType> selectTreeList();

    /**
     * 校验分类编码是否存在
     */
    Result checkTypeCode(ProductType productType);

    /**
     * 添加商品分类
     */
    Result insert(ProductType productType);

    /**
     * 删除商品分类
     */
    Result deleteById(Integer typeId);

    /**
     * 修改商品分类
     */
    Result update(ProductType productType);
}
