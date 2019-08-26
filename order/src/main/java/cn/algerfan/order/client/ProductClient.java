package cn.algerfan.order.client;

import cn.algerfan.order.common.DecreaseStockOutput;
import cn.algerfan.order.common.ProductInfoOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author algerfan
 * @time 2019 21: 39
 */
@FeignClient(name = "product")
public interface ProductClient {

    /**
     * 根据id查找商品
     * @param productIdList id集合
     * @return 传输对象
     */
    @PostMapping("/product/listForOrder")
    List<ProductInfoOutput> productInfoList(@RequestBody List<String> productIdList);

    /**
     * 扣库存
     * @param decreaseStockOutputs 传输对象
     */
    @PostMapping("/product/decreaseStock")
    void decreaseStock(@RequestBody List<DecreaseStockOutput> decreaseStockOutputs);

}
