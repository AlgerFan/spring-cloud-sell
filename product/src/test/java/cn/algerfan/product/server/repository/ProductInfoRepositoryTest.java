package cn.algerfan.product.server.repository;

import cn.algerfan.product.server.domain.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author algerfan
 * @time 2019 11: 48
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductInfoRepositoryTest {
    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    public void findByProductStatus() {
        List<ProductInfo> byProductStatus = productInfoRepository.findByProductStatus(0);
        Assert.assertTrue(byProductStatus.size() > 0);
    }
}
