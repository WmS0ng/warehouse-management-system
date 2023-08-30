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
    Integer countTotal(Product product);

    /**
     * 分页查询商品
     */
    List<Product> selectPage(@Param("page") Page page, @Param("product") Product product);

    /**
     * 添加商品
     */
    int insert(Product product);

    /**
     * 根据型号查询商品方法
     */
    Product selectByNum(String productNum);

    /**
     * 根据商品id修改商品上下架状态
     */
    int updateState(@Param("productId") Integer productId, @Param("upDownState") String upDownState);

    /**
     * 根据商品id删除商品
     */
    int deleteByIdList(List<Integer> productIdList);

    /**
     * 根据商品id修改商品方法
     */
    int update(Product product);

    /**
     * 修改商品表库存
     */
    int updateInvent(@Param("productId") Integer productId, @Param("invent") Integer invent);

    /**
     * 根据商品id查询库存的方法
     */
    int selectInventById(Integer productId);
}




