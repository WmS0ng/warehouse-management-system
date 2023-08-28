package com.example.warehouse.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @TableName product_type
 */
@Data
public class ProductType implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Integer typeId;
    private Integer parentId;
    private String typeCode;
    private String typeName;
    private String typeDesc;
}