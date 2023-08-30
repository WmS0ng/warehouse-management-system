package com.example.warehouse.mapper;

import com.example.warehouse.entity.Store;
import com.example.warehouse.page.Page;
import com.example.warehouse.vo.StoreCountVo;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 查询仓库总行数
     */
    int countTotal(Store store);

    /**
     * 分页查询仓库数量
     */
    List<Store> selectPage(@Param("page") Page page, @Param("store") Store store);

    /**
     * 根据编号查询
     */
    Store selectByNum(String storeNum);

    /**
     * 添加仓库
     */
    int insert(Store store);

    /**
     * 修改仓库
     */
    int update(Store store);
}
