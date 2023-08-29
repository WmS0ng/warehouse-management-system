package com.example.warehouse.mapper;

import com.example.warehouse.entity.Unit;

import java.util.List;

/**
 * @author swm
 * @description 针对表【unit(规格单位表)】的数据库操作Mapper
 * @createDate 2023-08-28 11:13:29
 * @Entity com.example.warehouse.entity.Unit
 */
public interface UnitMapper {
    /**
     * 查询所有单位
     */
    List<Unit> selectList();
}




