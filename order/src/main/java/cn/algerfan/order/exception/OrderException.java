package cn.algerfan.order.exception;

import cn.algerfan.order.enums.Result;

/**
 * @author algerfan
 * @time 2019 18: 18
 */
public class OrderException extends RuntimeException {

    /**
     * 状态码
     */
    private Integer code;

    public OrderException(Integer code, String massage) {
        super(massage);
        this.code = code;
    }

    public OrderException(Result result) {
        super(result.getMessage());
        this.code = result.getCode();
    }

}
