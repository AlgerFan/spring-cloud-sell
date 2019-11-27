package cn.algerfan.product.server.service;

import cn.algerfan.product.common.DecreaseStockOutput;
import cn.algerfan.product.common.ProductInfoOutput;
import cn.algerfan.product.server.domain.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author algerfan
 * @time 2019 15: 26
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductServiceImplTest {
    @Autowired
    private ProductService productService;

    @Test
    public void findUpAll() {
        List<ProductInfo> upAll = productService.findUpAll();
        Assert.assertTrue(upAll.size() > 0);
    }

    @Test
    public void productInfoList() {
        List<ProductInfoOutput> productInfoList = productService.productInfoList(Arrays.asList("157875196366160022", "157875227953464068"));
        Assert.assertTrue(productInfoList.size() > 0);
    }

    @Test
    public void decreaseStock() {
        DecreaseStockOutput cartDto = new DecreaseStockOutput("157875227953464068", 2);
        productService.decreaseStock(Collections.singletonList(cartDto));
    }
}
