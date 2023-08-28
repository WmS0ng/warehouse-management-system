package com.example.warehouse.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @TableName unit
 */
@Data
public class Unit implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Integer unitId;
    private String unitName;
    private String unitDesc;
}