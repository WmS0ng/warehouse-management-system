package com.example.warehouse.mapper;

import com.example.warehouse.entity.Supply;

import java.util.List;

/**
 * @author swm
 * @description 针对表【supply(供货商)】的数据库操作Mapper
 * @createDate 2023-08-28 11:12:55
 * @Entity com.example.warehouse.entity.Supply
 */
public interface SupplyMapper {
    /**
     * 查询所有供应商
     */
    List<Supply> selectALlSupply();
}




