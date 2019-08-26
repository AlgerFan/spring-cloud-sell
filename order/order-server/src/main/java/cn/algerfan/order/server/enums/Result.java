package cn.algerfan.order.server.enums;

import lombok.Getter;

/**
 * @author algerfan
 * @time 2019 20: 29
 */
@Getter
public enum Result {

    /**
     * 参数错误
     */
    PARAM_ERROR(1, "参数错误"),
    /**
     * 购物车为空
     */
    CART_EMPTY(2, "购物车为空");
    ;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 状态信息
     */
    private String message;

    Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
