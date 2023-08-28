package com.example.warehouse.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @TableName supply
 */
@Data
public class Supply implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Integer supplyId;
    private String supplyNum;
    private String supplyName;
    private String supplyIntroduce;
    private String concat;
    private String phone;
    private String address;
    private String isDelete;
}