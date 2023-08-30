package com.example.warehouse.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private String isOut;
    // 追加属性
    private BigDecimal salePrice;
    private String productName;
    private String storeName;
    private String userCode;
    private String startTime;
    private String endTime;
}