package cn.algerfan.order.server.vo;

import lombok.Data;

/**
 * @author algerfan
 * @time 2019 08: 31
 */
@Data
public class ResultVo<T> {

    private Integer code;

    private String msg;

    private T data;

}
