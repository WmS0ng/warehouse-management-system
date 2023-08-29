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
    public Page selectPage(Page page, Product product) {
        Integer count = productMapper.countTotal(product);
        List<Product> productList = productMapper.selectPage(page, product);
        page.setTotalNum(count);
        page.setResultList(productList);
        return page;
    }

    /**
     * 添加商品
     */
    @Override
    public Result insert(Product product) {
        // 判断商品的型号是否存在
        Product selcetProduct = productMapper.selectByNum(product.getProductNum());
        if (selcetProduct != null) {
            return Result.err(Result.CODE_ERR_BUSINESS, "商品型号已存在！");
        }
        product.setImgs(fileAccessPath + "/" + product.getImgs());
        int i = productMapper.insert(product);
        if (i > 0) {
            return Result.ok("商品添加成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "商品添加失败！");
    }

    /**
     * 根据商品id修改商品状态
     */
    @Override
    public Result updateState(Product product) {
        int i = productMapper.updateState(product.getProductId(), product.getUpDownState());
        if (i > 0) {
            return Result.ok("商品状态修改成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "商品状态修改失败！");
    }

    /**
     * 删除商品
     */
    @Override
    public Result deleteByIdList(List<Integer> productIdList) {
        int i = productMapper.deleteByIdList(productIdList);
        if (i > 0) {
            return Result.ok("商品删除成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "商品删除失败！");
    }

    /**
     * 修改商品
     */
    @Override
    public Result update(Product product) {
        // 判断修改后的型号是否存在
        Product selectProduct = productMapper.selectByNum(product.getProductNum());
        if (selectProduct != null && !selectProduct.getProductId().equals(product.getProductId())) {
            return Result.err(Result.CODE_ERR_BUSINESS, "商品型号已经存在，修改失败！");
        }
        // 判断上传的图片是否被修改
        if (!product.getImgs().contains(fileAccessPath)) {
            product.setImgs(fileAccessPath + product.getImgs());
        }
        // 修改商品
        int i = productMapper.update(product);
        if (i > 0) {
            return Result.ok("商品修改成功！");
        }

        return Result.err(Result.CODE_ERR_BUSINESS, "商品修改失败！");
    }
}
