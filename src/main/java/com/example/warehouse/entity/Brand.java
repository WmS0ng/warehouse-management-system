package com.example.warehouse.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @TableName brand
 */
@Data
public class Brand implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Integer brandId;
    private String brandName;
    private String brandLeter;
    private String brandDesc;
}