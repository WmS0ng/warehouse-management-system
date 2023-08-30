package com.example.warehouse.service;

import com.example.warehouse.entity.Store;
import com.example.warehouse.page.Page;
import com.example.warehouse.result.Result;
import com.example.warehouse.vo.StoreCountVo;

import java.util.List;

public interface StoreService {
    /**
     * 查询所有仓库
     */
    List<Store> selectList();

    /**
     * 查询每个仓库商品的数量
     */
    List<StoreCountVo> selectStoreCount();

    /**
     * 分页查询仓库
     */
    Page selectPage(Page page, Store store);

    /**
     * 检查仓库编号是否存在
     */
    Result storeNumCheck(String storeNum);

    /**
     * 添加仓库
     */
    Result insert(Store store);

    /**
     * 修改仓库
     */
    Result update(Store store);

    /**
     * 根据id删除仓库
     */
    Result deleteById(Integer storeId);
}
