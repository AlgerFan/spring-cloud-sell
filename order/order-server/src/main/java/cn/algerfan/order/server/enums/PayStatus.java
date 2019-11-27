package cn.algerfan.order.server.enums;

import lombok.Getter;

/**
 * @author algerfan
 * @time 2019 17: 27
 */
@Getter
public enum PayStatus {

    /**
     * 枚举
     */
    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功"),
    ;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 状态信息
     */
    private String message;

    PayStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
