package com.example.warehouse.mapper;

import com.example.warehouse.entity.Place;

import java.util.List;

/**
 * @author swm
 * @description 针对表【place(产地)】的数据库操作Mapper
 * @createDate 2023-08-28 11:13:15
 * @Entity com.example.warehouse.entity.Place
 */
public interface PlaceMapper {

    /**
     * 查询所有产地
     */
    List<Place> selectList();
}




