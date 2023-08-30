package com.example.warehouse.service.impl;

import com.example.warehouse.entity.Store;
import com.example.warehouse.mapper.StoreMapper;
import com.example.warehouse.page.Page;
import com.example.warehouse.result.Result;
import com.example.warehouse.service.StoreService;
import com.example.warehouse.vo.StoreCountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "com.example.warehouse.service.impl.StoreServiceImpl")
public class StoreServiceImpl implements StoreService {
    @Autowired
    private StoreMapper storeMapper;

    /**
     * 查询所有仓库
     */
    @Cacheable(key = "'all:store'")
    @Override
    public List<Store> selectList() {
        return storeMapper.selectList();
    }

    /**
     * 查询每个仓库商品的数量
     */
    @Override
    public List<StoreCountVo> selectStoreCount() {
        return storeMapper.selectStoreCount();
    }

    /**
     * 分页查询仓库
     */
    @Override
    public Page selectPage(Page page, Store store) {
        int count = storeMapper.countTotal(store);
        List<Store> storeList = storeMapper.selectPage(page, store);
        page.setTotalNum(count);
        page.setResultList(storeList);
        return page;
    }

    /**
     * 查看仓库编号是否存在
     */
    @Override
    public Result storeNumCheck(String storeNum) {
        Store store = storeMapper.selectByNum(storeNum);
        return Result.ok(store == null);
    }

    /**
     * 添加仓库
     */
    @Override
    @CacheEvict(key = "'all:store'")
    public Result insert(Store store) {
        int i = storeMapper.insert(store);
        if (i > 0) {
            return Result.ok("仓库添加成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "仓库添加失败！");
    }

    /**
     * 修改仓库
     */
    @Override
    @CacheEvict(key = "'all:store'")
    public Result update(Store store) {
        int i = storeMapper.update(store);
        if (i > 0) {
            return Result.ok("仓库修改成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "仓库修改失败！");
    }

    /**
     * 根据id删除仓库
     */
    @Override
    @CacheEvict(key = "'all:store'")
    public Result deleteById(Integer storeId) {
        int i = storeMapper.deleteById(storeId);
        if (i > 0) {
            return Result.ok("仓库删除成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "仓库删除失败！");
    }
}
