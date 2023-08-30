package com.example.warehouse.mapper;

import com.example.warehouse.entity.OutStore;
import com.example.warehouse.page.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
    int insert(OutStore outStore);

    /**
     * 查询出库总行数
     */
    int countTotal(OutStore outStore);

    /**
     * 分页查询出库单
     */
    List<OutStore> selectPage(@Param("page") Page page, @Param("outStore") OutStore outStore);
}




