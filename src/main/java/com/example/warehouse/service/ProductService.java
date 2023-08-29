package com.example.warehouse.service;

import com.example.warehouse.entity.Product;
import com.example.warehouse.page.Page;
import com.example.warehouse.result.Result;

import java.util.List;

public interface ProductService {
    /**
     * 分页查询商品
     */
    Page selectPage(Page page, Product product);

    /**
     * 添加商品
     */
    Result insert(Product product);

    /**
     * 根据商品id修改商品状态
     */
    Result updateState(Product product);

    /**
     * 删除商品
     */
    Result deleteByIdList(List<Integer> productIdList);

    /**
     * 修改商品
     */
    Result update(Product product);
}
