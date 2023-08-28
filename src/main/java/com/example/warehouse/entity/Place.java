package com.example.warehouse.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @TableName place
 */
@Data
public class Place implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Integer placeId;
    private String placeName;
    private String placeNum;
    private String introduce;
    private String isDelete;
}