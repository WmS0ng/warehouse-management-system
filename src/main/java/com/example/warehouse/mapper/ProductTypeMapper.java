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

    /**
     * 根据分类编码或者分类名称查询商品分类
     */
    ProductType selectProductTypeByCodeOrName(ProductType productType);

    /**
     * 添加商品分类
     */
    int insertProductType(ProductType productType);

    /**
     * 根据分类id或父级分类id删除分类
     */
    int deleteProductType(Integer typeId);
}




