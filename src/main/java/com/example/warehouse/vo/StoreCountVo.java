package com.example.warehouse.vo;

import lombok.Data;

/**
 * 封装每个商品数量的vo类
 */
@Data
public class StoreCountVo {
    private Integer storeId;
    private String storeName;
    private Integer totalInvent;
}
