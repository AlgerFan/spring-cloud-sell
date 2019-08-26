package cn.algerfan.product.server.controller;

import cn.algerfan.product.server.common.DecreaseStockOutput;
import cn.algerfan.product.server.common.ProductInfoOutput;
import cn.algerfan.product.server.domain.ProductCategory;
import cn.algerfan.product.server.domain.ProductInfo;
import cn.algerfan.product.server.service.ProductCategoryService;
import cn.algerfan.product.server.service.ProductService;
import cn.algerfan.product.server.util.ResultVoUtil;
import cn.algerfan.product.server.vo.ProductInfoVo;
import cn.algerfan.product.server.vo.ProductVo;
import cn.algerfan.product.server.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author algerfan
 * @time 2019 11: 33
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 1. 查询所有在架的商品
     * 2. 获取类目type列表
     * 3. 查询类目
     * 4. 构造数据
     */
    @RequestMapping("/list")
    public ResultVo<Object> list() {
        //1. 查询所有在架的商品
        List<ProductInfo> productInfoList = productService.findUpAll();
        //2. 获取类目type列表
        List<Integer> collect = productInfoList.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());
        //3. 查询类目
        List<ProductCategory> categoryList = productCategoryService.findByCategoryTypeIn(collect);
        //4. 构造数据
        List<ProductVo> productVos = new ArrayList<>();
        for (ProductCategory productCategory : categoryList) {
            ProductVo productVo = new ProductVo();
            productVo.setCategoryName(productCategory.getCategoryName());
            productVo.setCategoryType(productCategory.getCategoryType());
            List<ProductInfoVo> productInfoVos = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    BeanUtils.copyProperties(productInfo, productInfoVo);
                    productInfoVos.add(productInfoVo);
                }
            }
            productVo.setProductInfoVoList(productInfoVos);
            productVos.add(productVo);
        }
        return ResultVoUtil.success(productVos);
    }

    /**
     * 获取商品列表（订单服务用）
     * @param productIdList
     * @return
     */
    @PostMapping("/listForOrder")
    public List<ProductInfoOutput> productInfoList(@RequestBody List<String> productIdList) {
        return productService.productInfoList(productIdList);
    }

    /**
     * 扣库存（订单服务用）
     * @param cartDtoList
     */
    @PostMapping("/decreaseStock")
    public void decreaseStock(@RequestBody List<DecreaseStockOutput> cartDtoList) {
        productService.decreaseStock(cartDtoList);
    }

}
