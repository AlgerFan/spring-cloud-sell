package cn.algerfan.order.server.enums;

import lombok.Getter;

/**
 * @author algerfan
 * @time 2019 17: 27
 */
@Getter
public enum  OrderStatus {

    /**
     * 枚举
     */
    NEW(0, "新订单"),
    FINISHED(1, "完结"),
    CANCEL(2, "取消"),
    ;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 状态信息
     */
    private String message;

    OrderStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
