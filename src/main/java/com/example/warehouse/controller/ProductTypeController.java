package com.example.warehouse.controller;

import com.example.warehouse.entity.ProductType;
import com.example.warehouse.result.Result;
import com.example.warehouse.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/productCategory")
public class ProductTypeController {
    @Autowired
    private ProductTypeService productTypeService;

    /**
     * 查询商品分类树
     */
    @RequestMapping("/product-category-tree")
    public Result productTypeTree() {
        List<ProductType> productTypeTree = productTypeService.productTypeTree();
        return Result.ok(productTypeTree);
    }
}
