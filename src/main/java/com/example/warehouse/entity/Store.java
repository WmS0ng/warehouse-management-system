package com.example.warehouse.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @TableName store
 */
@Data
public class Store implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Integer storeId;
    private String storeName;
    private String storeNum;
    private String storeAddress;
    private String concat;
    private String phone;
}