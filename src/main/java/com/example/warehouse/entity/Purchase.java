package com.example.warehouse.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName buy_list
 */
@Data
public class Purchase implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer buyId;
    private Integer productId;
    private Integer storeId;
    private Integer buyNum;
    private Integer factBuyNum;
    private Date buyTime;
    private Integer supplyId;
    private Integer placeId;
    private String buyUser;
    private String phone;
    private String isIn;
    // 追加属性
    private String startTime;
    private String endTime;
    private String storeName;
    private String productName;
}