package cn.algerfan.product.server.service;

import cn.algerfan.product.server.domain.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @author algerfan
 * @time 2019 15: 33
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductCategoryServiceTest {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> byCategoryTypeIn = productCategoryService.findByCategoryTypeIn(Arrays.asList(11, 12));
        Assert.assertTrue(byCategoryTypeIn.size() > 0);
    }
}
