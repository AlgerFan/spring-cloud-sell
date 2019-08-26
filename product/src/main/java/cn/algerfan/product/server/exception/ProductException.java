package cn.algerfan.product.server.exception;

import cn.algerfan.product.server.enums.ResultEnum;

/**
 * @author algerfan
 * @time 2019 20: 09
 */
public class ProductException extends RuntimeException {

    private Integer code;

    public ProductException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public ProductException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

}
