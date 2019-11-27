package cn.algerfan.user.server.enums;

import lombok.Getter;

/**
 * @author algerfan
 * @time 2019 20: 10
 */
@Getter
public enum ResultEnum {

    /**
     * 枚举
     */
    LOGIN_FAIL(2, "登录失败"),
    ROLE_ERROR(1, "角色错误"),
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
