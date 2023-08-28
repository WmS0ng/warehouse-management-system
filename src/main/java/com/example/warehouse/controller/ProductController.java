package com.example.warehouse.controller;

import com.example.warehouse.entity.*;
import com.example.warehouse.page.Page;
import com.example.warehouse.result.Result;
import com.example.warehouse.service.*;
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
    @Autowired
    private ProductTypeService productTypeService;
    @Autowired
    private SupplyService supplyService;
    @Autowired
    private PlaceService placeService;
    @Autowired
    private UnitService unitService;


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

    /**
     * 查询所有商品分类树
     */
    @RequestMapping("/category-tree")
    public Result loadTypeTree() {
        List<ProductType> productTypeService = this.productTypeService.productTypeTree();
        return Result.ok(productTypeService);
    }

    /**
     * 查询所用供应商
     */
    @RequestMapping("/supply-list")
    public Result supplyList() {
        List<Supply> supplyList = supplyService.selectAllSupply();
        return Result.ok(supplyList);
    }

    /**
     * 查询所有场地
     */
    @RequestMapping("/place-list")
    public Result placeList() {
        List<Place> placeList = placeService.selectAllPlace();
        return Result.ok(placeList);
    }

    /**
     * 查询所有单位
     */
    @RequestMapping("/unit-list")
    public Result unitList() {
        List<Unit> unitList = unitService.selectAllUnit();
        return Result.ok(unitList);
    }
}
