package cn.algerfan.product.server.enums;

import lombok.Getter;

/**
 * 商品上下架状态
 * @author algerfan
 * @time 2019 15: 21
 */
@Getter
public enum ProductStatusEnum {

    /**
     * 枚举
     */
    UP(0, "在架"),
    DOWN(1, "下架"),
    ;
    /**
     * 状态码
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
