package com.example.warehouse.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @TableName in_store
 */
@Data
public class InStore implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Integer insId;
    private Integer storeId;
    private Integer productId;
    private Integer inNum;
    private Integer createBy;
    private Date createTime;
    private String isIn;
    // 追加属性
    private String productName;
    private String storeName;
    private BigDecimal inPrice;
    private String userCode;
    private String startTime;
    private String endTime;
}