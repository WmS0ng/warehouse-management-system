package com.example.warehouse.service;

import com.example.warehouse.entity.Product;
import com.example.warehouse.page.Page;

public interface ProductService {
    /**
     * 分页查询商品
     */
    Page selectProductPage(Page page, Product product);
}
