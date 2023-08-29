package com.example.warehouse.service.impl;

import com.example.warehouse.entity.ProductType;
import com.example.warehouse.mapper.ProductTypeMapper;
import com.example.warehouse.result.Result;
import com.example.warehouse.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
     * 检验分类编码是否存在
     */
    @Override
    public Result checkTypeCode(ProductType productType) {
        ProductType selectProductType = productTypeMapper.selectProductTypeByCodeOrName(productType);
        return Result.ok(selectProductType == null);
    }

    /**
     * 添加商品分类
     */
    @Override
    @Transactional
    @CacheEvict(key = "'all:typeTree'")
    public Result saveProductType(ProductType productType) {
        ProductType tempProductType = new ProductType();
        tempProductType.setTypeName(productType.getTypeName());
        ProductType tempProductType2 = productTypeMapper.selectProductTypeByCodeOrName(tempProductType);
        if (tempProductType2 != null) {
            return Result.err(Result.CODE_ERR_BUSINESS, "分类名称已经存在！");
        }
        int i = productTypeMapper.insertProductType(productType);
        if (i > 0) {
            return Result.ok("添加商品分类成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "添加商品分类失败！");
    }

    /**
     * 删除商品分类
     */
    @Override
    @CacheEvict(key = "'all:typeTree'")
    public Result deleteProductType(Integer typeId) {
        int i = productTypeMapper.deleteProductType(typeId);
        if (i > 0) {
            return Result.ok("删除商品分类成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "删除商品分类失败！");
    }

    /**
     * 修改商品分类
     */
    @Override
    @CacheEvict(key = "'all:typeTree'")
    public Result updateById(ProductType productType) {
        ProductType tempProductType = new ProductType();
        tempProductType.setTypeName(productType.getTypeName());
        ProductType selectProductType = productTypeMapper.selectProductTypeByCodeOrName(tempProductType);
        if (selectProductType != null && !productType.getTypeId().equals(selectProductType.getTypeId())) {
            return Result.err(Result.CODE_ERR_BUSINESS, "分类名字已经存在，无法修改！");
        }
        int i = productTypeMapper.updateById(productType);
        if (i > 0) {
            return Result.ok("商品分类修改成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "商品分类修改失败！");
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
