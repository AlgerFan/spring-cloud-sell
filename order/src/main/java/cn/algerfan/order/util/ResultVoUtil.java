package cn.algerfan.order.util;

import cn.algerfan.order.vo.ResultVo;

/**
 * @author algerfan
 * @time 2019 08: 32
 */
public class ResultVoUtil {

    public static ResultVo<Object> success(Object object) {
        ResultVo<Object> resultVo = new ResultVo<>();
        resultVo.setCode(0);
        resultVo.setMsg("成功");
        resultVo.setData(object);
        return resultVo;
    }
}
