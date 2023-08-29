package com.example.warehouse.service.impl;

import com.example.warehouse.entity.Product;
import com.example.warehouse.mapper.ProductMapper;
import com.example.warehouse.page.Page;
import com.example.warehouse.result.Result;
import com.example.warehouse.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    /**
     * 上传图片访问的目录路径
     */
    @Value("${file.access-path}")
    private String fileAccessPath;

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

    /**
     * 添加商品
     */
    @Override
    public Result saveProduct(Product product) {
        // 判断商品的型号是否存在
        Product selcetProduct = productMapper.selectProductByNum(product.getProductNum());
        if (selcetProduct != null) {
            return Result.err(Result.CODE_ERR_BUSINESS, "商品型号已存在！");
        }
        product.setImgs(fileAccessPath + "/" + product.getImgs());
        int i = productMapper.insertProduct(product);
        if (i > 0) {
            return Result.ok("商品添加成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "商品添加失败！");
    }

    /**
     * 根据商品id修改商品状态
     */
    @Override
    public Result updateProductStateByPid(Product product) {
        int i = productMapper.updateProductStateByPid(product.getProductId(), product.getUpDownState());
        if (i > 0) {
            return Result.ok("商品状态修改成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "商品状态修改失败！");
    }

    /**
     * 删除商品
     */
    @Override
    public Result deleteProductByPidList(List<Integer> productIdList) {
        int i = productMapper.deleteProductByPidList(productIdList);
        if (i > 0) {
            return Result.ok("商品删除成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "商品删除失败！");
    }
}
