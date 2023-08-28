package com.example.warehouse.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @TableName product
 */
@Data
public class Product implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Integer productId;
    private Integer storeId;
    private Integer brandId;
    private String productName;
    private String productNum;
    private Integer productInvent;
    private Integer typeId;
    private Integer supplyId;
    private Integer placeId;
    private Integer unitId;
    private String introduce;
    private String upDownState;
    private BigDecimal inPrice;
    private BigDecimal salePrice;
    private BigDecimal memPrice;
    private Date createTime;
    private Date updateTime;
    private Integer createBy;
    private Integer updateBy;
    private String imgs;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date productDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date suppDate;
    // 追加属性
    private String brandName;
    private String supplyName;
    private String placeName;
    private String typeName;
    private Integer isOverDate;
    private String storeName;
    private String unitName;
}