package com.example.warehouse.mapper;

import com.example.warehouse.entity.OutStore;

/**
 * @author swm
 * @description 针对表【out_store(出库单)】的数据库操作Mapper
 * @createDate 2023-08-29 15:13:23
 * @Entity com.example.warehouse.entity.OutStore
 */
public interface OutStoreMapper {
    /**
     * 添加出库单
     */
    int insertOutStore(OutStore outStore);
}




