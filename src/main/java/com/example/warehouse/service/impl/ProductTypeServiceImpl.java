package com.example.warehouse.service.impl;

import com.example.warehouse.entity.ProductType;
import com.example.warehouse.mapper.ProductTypeMapper;
import com.example.warehouse.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@CacheConfig(cacheNames = "com.example.warehouse.service.impl.ProductTypeServiceImpl")
public class ProductTypeServiceImpl implements ProductTypeService {
    @Autowired
    private ProductTypeMapper productTypeMapper;

    /**
     * 查询所有商品分类树
     */
    @Cacheable(key = "'all:typeTree'")
    @Override
    public List<ProductType> productTypeTree() {
        List<ProductType> productTypeList = productTypeMapper.selectAllProductType();
        List<ProductType> productTypeTree = allTypeToTypeTree(productTypeList, 0);

        return productTypeTree;
    }

    /**
     * 将所有商品分类转换成商品分类树
     */
    private List<ProductType> allTypeToTypeTree(List<ProductType> productTypeList, Integer pid) {
        // 拿到所有一级分类
        List<ProductType> firstLevelType = new ArrayList<>();
        for (ProductType productType : productTypeList) {
            if (productType.getParentId().equals(pid)) {
                firstLevelType.add(productType);
            }
        }

        // 查出每个一级分类下的所有二级分类
        for (ProductType productType : firstLevelType) {
            List<ProductType> secondLevelType = allTypeToTypeTree(productTypeList, productType.getTypeId());
            productType.setChildProductCategory(secondLevelType);
        }

        return firstLevelType;
    }

}
