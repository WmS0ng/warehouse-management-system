package com.example.warehouse.mapper;

import com.example.warehouse.entity.Purchase;
import com.example.warehouse.page.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author swm
 * @description 针对表【buy_list(采购单)】的数据库操作Mapper
 * @createDate 2023-08-29 13:47:48
 * @Entity generator.domain.BuyList
 */
public interface PurchaseMapper {
    /**
     * 添加采购单
     */
    int insert(Purchase purchase);

    /**
     * 查询采购单的所有行数量
     */
    int countToTal(Purchase purchase);

    /**
     * 分页查询
     */
    List<Purchase> selectPage(@Param("page") Page page, @Param("purchase") Purchase purchase);

    /**
     * 根据id删除采购单
     */
    int deleteById(Integer buyId);

    /**
     * 根据id修改预计采购数量和实际采购数量
     */
    int updateNum(Purchase purchase);
}




