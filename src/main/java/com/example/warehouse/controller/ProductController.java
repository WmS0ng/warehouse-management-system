package com.example.warehouse.controller;

import com.example.warehouse.entity.Brand;
import com.example.warehouse.entity.Product;
import com.example.warehouse.entity.Store;
import com.example.warehouse.page.Page;
import com.example.warehouse.result.Result;
import com.example.warehouse.service.BrandService;
import com.example.warehouse.service.ProductService;
import com.example.warehouse.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private StoreService storeService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private ProductService productService;

    /**
     * 查询所有仓库
     */
    @RequestMapping("/store-list")
    public Result storeList() {
        List<Store> storeList = storeService.selectAllStore();
        return Result.ok(storeList);
    }

    /**
     * 查询所有品牌
     */
    @RequestMapping("/brand-list")
    public Result brandList() {
        List<Brand> brandList = brandService.selectAllBrand();
        return Result.ok(brandList);
    }

    /**
     * 分页查询商品
     */
    @RequestMapping("/product-page-list")
    public Result productListPage(Page page, Product product) {
        page = productService.selectProductPage(page, product);

        return Result.ok(page);
    }
}
