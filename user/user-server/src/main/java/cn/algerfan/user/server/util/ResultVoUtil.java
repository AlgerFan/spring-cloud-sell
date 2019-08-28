package cn.algerfan.user.server.util;

import cn.algerfan.user.server.enums.ResultEnum;
import cn.algerfan.user.server.vo.ResultVo;

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

    public static ResultVo<Object> success() {
        ResultVo<Object> resultVo = new ResultVo<>();
        resultVo.setCode(0);
        resultVo.setMsg("成功");
        return resultVo;
    }

    public static ResultVo<Object> error(ResultEnum resultEnum) {
        ResultVo<Object> resultVo = new ResultVo<>();
        resultVo.setCode(resultEnum.getCode());
        resultVo.setMsg(resultEnum.getMessage());
        return resultVo;
    }

}
