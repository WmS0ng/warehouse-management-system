package com.example.warehouse.controller;

import com.example.warehouse.dto.CurrentUser;
import com.example.warehouse.entity.*;
import com.example.warehouse.page.Page;
import com.example.warehouse.result.Result;
import com.example.warehouse.service.*;
import com.example.warehouse.utils.TokenUtils;
import com.example.warehouse.utils.WarehouseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
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
    @Value("${file.upload-path}")
    private String uploadPath;
    @Autowired
    private TokenUtils tokenUtils;


    /**
     * 查询所有仓库
     */
    @RequestMapping("/store-list")
    public Result storeList() {
        List<Store> storeList = storeService.selectList();
        return Result.ok(storeList);
    }

    /**
     * 查询所有品牌
     */
    @RequestMapping("/brand-list")
    public Result brandList() {
        List<Brand> brandList = brandService.selectList();
        return Result.ok(brandList);
    }

    /**
     * 分页查询商品
     */
    @RequestMapping("/product-page-list")
    public Result productPageList(Page page, Product product) {
        page = productService.selectPage(page, product);

        return Result.ok(page);
    }

    /**
     * 查询所有商品分类树
     */
    @RequestMapping("/category-tree")
    public Result categoryTree() {
        List<ProductType> productTypeService = this.productTypeService.selectTreeList();
        return Result.ok(productTypeService);
    }

    /**
     * 查询所用供应商
     */
    @RequestMapping("/supply-list")
    public Result supplyList() {
        List<Supply> supplyList = supplyService.selectList();
        return Result.ok(supplyList);
    }

    /**
     * 查询所有场地
     */
    @RequestMapping("/place-list")
    public Result placeList() {
        List<Place> placeList = placeService.selectList();
        return Result.ok(placeList);
    }

    /**
     * 查询所有单位
     */
    @RequestMapping("/unit-list")
    public Result unitList() {
        List<Unit> unitList = unitService.selectList();
        return Result.ok(unitList);
    }

    /**
     * 上传图片
     * 参数MultipartFile file -- 表示封装了请求参数名叫file的上传图片
     * 拿到图片上传到的目录路径的File对象 -- classpath:static/img/upload
     * 因为图片上传到的目录路径是个类路径（resource下的路径classes下的路径，就是带有前缀classpath），所以不能直接将路径封装到file对象，
     * 使用类路径资源工具类ResourceUtils的方法getFile()来解析类路径并拿到封装类路径的File对象
     */
    @RequestMapping("/img-upload")
    @CrossOrigin // 接口允许被跨域请求
    public Result imgUpload(MultipartFile file) {
        try {
            // 拿到图片上传到目录路径的磁盘路径
            File uploadDirFile = ResourceUtils.getFile(uploadPath);
            String uploadDirPath = uploadDirFile.getAbsolutePath();
            // 拿到上传图片的名称
            String fileName = file.getOriginalFilename();
            // 拿到上传文件要保存到的磁盘文件的路径
            String uploadFilePath = uploadDirPath + "/" + fileName;
            // 上传
            file.transferTo(new File(uploadFilePath));
            // 响应
            return Result.ok("图片上传成功！");
        } catch (Exception e) {
            return Result.err(Result.CODE_ERR_BUSINESS, "图片上传失败！");
        }
    }

    /**
     * 添加商品
     */
    @RequestMapping("/product-add")
    public Result productAdd(@RequestBody Product product, @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token) {
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        product.setCreateBy(currentUser.getUserId());
        return productService.insert(product);
    }

    /**
     * 修改商品上架状态
     */
    @RequestMapping("/state-change")
    public Result stateChange(@RequestBody Product product) {
        return productService.updateState(product);
    }

    /**
     * 删除单个商品
     */
    @RequestMapping("/product-delete/{productId}")
    public Result productDelete(@PathVariable Integer productId) {
        ArrayList<Integer> productIdList = new ArrayList<>();
        productIdList.add(productId);
        return productService.deleteByIdList(productIdList);
    }

    /**
     * 批量删除商品
     */
    @RequestMapping("/product-list-delete")
    public Result productListDelete(@RequestBody List<Integer> productIdList) {
        return productService.deleteByIdList(productIdList);
    }

    /**
     * 修改商品
     */
    @RequestMapping("/product-update")
    public Result productUpdate(@RequestBody Product product, @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token) {
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        product.setUpdateBy(currentUser.getUserId());
        return productService.update(product);
    }
}
