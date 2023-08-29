package com.example.warehouse.mapper;

import com.example.warehouse.entity.Purchase;

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
}




