package com.example.warehouse.mapper;

import com.example.warehouse.entity.InStore;

/**
 * @author swm
 * @description 针对表【in_store(入库单)】的数据库操作Mapper
 * @createDate 2023-08-30 14:32:09
 * @Entity com.example.warehouse.entity.InStore
 */
public interface InStoreMapper {
    /**
     * 添加入库单
     */
    int insert(InStore inStore);
}




