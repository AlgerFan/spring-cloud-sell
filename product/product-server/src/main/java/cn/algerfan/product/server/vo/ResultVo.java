package cn.algerfan.product.server.vo;

import lombok.Data;

/**
 * http请求返回的最外层对象
 * @author algerfan
 * @time 2019 15: 39
 */
@Data
public class ResultVo<T> {
    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 具体内容
     */
    private T data;
}
