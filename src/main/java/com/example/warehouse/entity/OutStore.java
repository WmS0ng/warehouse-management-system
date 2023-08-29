package com.example.warehouse.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @TableName out_store
 */
@Data
public class OutStore implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Integer outsId;
    private Integer productId;
    private Integer storeId;
    private Integer tallyId;
    private BigDecimal outPrice;
    private Integer outNum;
    private Integer createBy;
    private Date createTime;
    private String isOut;
    // 追加属性
    private BigDecimal salePrice;
}