package cn.algerfan.product.server.util;

import cn.algerfan.product.server.vo.ResultVo;

/**
 * @author algerfan
 * @time 2019 16: 23
 */
public class ResultVoUtil {

    public static ResultVo<Object> success(Object object) {
        ResultVo<Object> resultVo = new ResultVo<>();
        resultVo.setData(object);
        resultVo.setCode(0);
        resultVo.setMsg("成功");
        return resultVo;
    }
}
