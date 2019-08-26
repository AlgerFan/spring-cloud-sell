package cn.algerfan.order.common;

import lombok.Data;

/**
 * @author algerfan
 * @time 2019 18: 00
 */
@Data
public class DecreaseStockOutput {
    /**
     * 商品id
     */
    private String productId;

    /**
     * 商品数量
     */
    private Integer productQuantity;

    public DecreaseStockOutput(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
