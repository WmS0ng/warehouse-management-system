package com.example.warehouse.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

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
    // 追加属性
    private List<ProductType> childProductCategory;
}