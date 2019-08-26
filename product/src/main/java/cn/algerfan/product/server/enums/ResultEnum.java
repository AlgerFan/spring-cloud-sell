package cn.algerfan.product.server.enums;

import lombok.Getter;

/**
 * @author algerfan
 * @time 2019 20: 10
 */
@Getter
public enum ResultEnum {

    /**
     * 库存不足
     */
    PRODUCT_STOCK_ERROR(2, "库存不足"),
    /**
     * 商品不存在异常
     */
    PRODUCT_NOT_EXIST(1, "商品不存在"),
    ;

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 状态信息
     */
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
