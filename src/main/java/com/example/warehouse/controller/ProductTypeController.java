package com.example.warehouse.controller;

import com.example.warehouse.entity.ProductType;
import com.example.warehouse.result.Result;
import com.example.warehouse.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
        List<ProductType> productTypeTree = productTypeService.selectTreeList();
        return Result.ok(productTypeTree);
    }

    /**
     * 校验分类编码是否存在
     */
    @RequestMapping("/verify-type-code")
    public Result verifyTypeCode(String typeCode) {
        ProductType productType = new ProductType();
        productType.setTypeCode(typeCode);
        return productTypeService.checkTypeCode(productType);
    }

    /**
     * 添加商品分类
     */
    @RequestMapping("/type-add")
    public Result typeAdd(@RequestBody ProductType productType) {
        return productTypeService.insert(productType);
    }

    /**
     * 删除商品分类
     */
    @RequestMapping("/type-delete/{typeId}")
    public Result typeDelete(@PathVariable Integer typeId) {
        return productTypeService.deleteById(typeId);
    }

    /**
     * 修改商品分类
     */
    @RequestMapping("/type-update")
    public Result typeUpdate(@RequestBody ProductType productType) {
        return productTypeService.update(productType);
    }
}
