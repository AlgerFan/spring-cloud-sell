package cn.algerfan.user.server.enums;

import lombok.Getter;

/**
 * @author algerfan
 * @time 2019/8/28 20:57
 */
@Getter
public enum RoleEnum {

    /**
     * 枚举
     */
    BUYER(1, "买家"),
    SELLER(2, "卖家"),
    ;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 状态信息
     */
    private String message;

    RoleEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
