package com.example.warehouse.service.impl;

import com.example.warehouse.entity.Product;
import com.example.warehouse.mapper.ProductMapper;
import com.example.warehouse.page.Page;
import com.example.warehouse.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    /**
     * 分页查询商品
     */
    @Override
    public Page selectProductPage(Page page, Product product) {
        Integer count = productMapper.selectProductRowCount(product);
        List<Product> productList = productMapper.selectProductList(page, product);
        page.setTotalNum(count);
        page.setResultList(productList);
        return page;
    }
}
