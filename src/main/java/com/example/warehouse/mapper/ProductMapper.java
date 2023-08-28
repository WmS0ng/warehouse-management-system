package com.example.warehouse.mapper;

import com.example.warehouse.entity.Product;
import com.example.warehouse.page.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author swm
 * @description 针对表【product(商品表)】的数据库操作Mapper
 * @createDate 2023-08-28 12:29:55
 * @Entity com.example.warehouse.entity.Product
 */
public interface ProductMapper {
    /**
     * 查询商品行数
     */
    Integer selectProductRowCount(Product product);

    /**
     * 分页查询商品
     */
    List<Product> selectProductList(@Param("page") Page page, @Param("product") Product product);

    /**
     * 添加商品
     */
    int insertProduct(Product product);

    /**
     * 根据型号查询商品方法
     */
    Product selectProductByNum(String productNum);
}




