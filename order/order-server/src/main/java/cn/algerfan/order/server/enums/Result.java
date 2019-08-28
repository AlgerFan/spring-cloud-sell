package cn.algerfan.order.server.enums;

import lombok.Getter;

/**
 * @author algerfan
 * @time 2019 20: 29
 */
@Getter
public enum Result {

    /**
     * 枚举
     */
    PARAM_ERROR(1, "参数错误"),
    CART_EMPTY(2, "购物车为空"),
    ORDER_NOT_EXIST(3, "订单不存在"),
    ORDER_STATUS_ERROR(4, "订单状态错误"),
    ORDER_DETAIL_NOT_EXIST(5, "订单详情不存在"),

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
