package com.example.warehouse.service;

import com.example.warehouse.entity.Place;

import java.util.List;

public interface PlaceService {
    /**
     * 查询所有产地
     */
    List<Place> selectAllPlace();
}
