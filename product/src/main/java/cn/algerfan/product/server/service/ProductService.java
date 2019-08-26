package cn.algerfan.product.server.service;

import cn.algerfan.product.server.common.DecreaseStockOutput;
import cn.algerfan.product.server.common.ProductInfoOutput;
import cn.algerfan.product.server.domain.ProductInfo;

import java.util.List;

/**
 * @author algerfan
 * @time 2019 11: 34
 */
public interface ProductService {
    /**
     * 查询所有在架商品列表
     */
    List<ProductInfo> findUpAll();

    /**
     * 获取商品列表（订单服务用）
     * @param productIdList
     * @return
     */
    List<ProductInfoOutput> productInfoList(List<String> productIdList);

    /**
     * 扣库存（订单服务用）
     * @param cartDtoList
     */
    void decreaseStock(List<DecreaseStockOutput> cartDtoList);
}
