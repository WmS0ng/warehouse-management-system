package com.example.warehouse.mapper;

import com.example.warehouse.entity.Store;
import com.example.warehouse.vo.StoreCountVo;

import java.util.List;

/**
 * @author swm
 * @description 针对表【store(仓库表)】的数据库操作Mapper
 * @createDate 2023-08-28 11:10:23
 * @Entity com.example.warehouse.entity.Store
 */
public interface StoreMapper {

    /**
     * 查询所有仓库
     */
    List<Store> selectList();

    /**
     * 查询每个仓库的商品数量
     */
    List<StoreCountVo> selectStoreCount();
}
