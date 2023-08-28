package com.example.warehouse.service;

import com.example.warehouse.entity.Product;
import com.example.warehouse.page.Page;
import com.example.warehouse.result.Result;

public interface ProductService {
    /**
     * 分页查询商品
     */
    Page selectProductPage(Page page, Product product);

    /**
     * 添加商品
     */
    Result saveProduct(Product product);

    /**
     * 根据商品id修改商品状态
     */
    Result updateProductStateByPid(Product product);
}
