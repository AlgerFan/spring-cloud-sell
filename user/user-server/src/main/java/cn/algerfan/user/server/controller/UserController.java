package cn.algerfan.user.server.controller;

import cn.algerfan.user.server.constant.CookieConstant;
import cn.algerfan.user.server.constant.RedisConstant;
import cn.algerfan.user.server.domain.UserInfo;
import cn.algerfan.user.server.enums.ResultEnum;
import cn.algerfan.user.server.enums.RoleEnum;
import cn.algerfan.user.server.service.UserService;
import cn.algerfan.user.server.util.CookieUtil;
import cn.algerfan.user.server.util.ResultVoUtil;
import cn.algerfan.user.server.vo.ResultVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author algerfan
 * @time 2019/8/28 20:44
 */
@RestController
@RequestMapping("/login")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 买家登录
     * @param openid
     * @param response
     * @return
     */
    @RequestMapping("/buyer")
    public ResultVo buyer(@RequestParam("openid") String openid,
                          HttpServletResponse response) {
        //1.openid是否与数据库匹配
        UserInfo userInfo = userService.findByOpenid(openid);
        if(userInfo == null) {
            return ResultVoUtil.error(ResultEnum.LOGIN_FAIL);
        }
        //2.判断角色
        if(!RoleEnum.BUYER.getCode().equals(userInfo.getRole())) {
            return ResultVoUtil.error(ResultEnum.ROLE_ERROR);
        }
        //3.cookie里设置openid=abc
        CookieUtil.set(response, CookieConstant.OPENID, openid, CookieConstant.EXPIRE);
        return ResultVoUtil.success();
    }

    @RequestMapping("/seller")
    public ResultVo seller(@RequestParam("openid") String openid,
                           HttpServletResponse response,
                           HttpServletRequest request) {
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if(cookie != null &&
                !StringUtils.isEmpty(stringRedisTemplate.opsForValue()
                        .get(String.format(RedisConstant.TOKEN_TEMPLATE, cookie.getValue())))) {
                return ResultVoUtil.success();
        }
        //1.openid是否与数据库匹配
        UserInfo userInfo = userService.findByOpenid(openid);
        if(userInfo == null) {
            return ResultVoUtil.error(ResultEnum.LOGIN_FAIL);
        }
        //2.判断角色
        if(!RoleEnum.SELLER.getCode().equals(userInfo.getRole())) {
            return ResultVoUtil.error(ResultEnum.ROLE_ERROR);
        }
        //3.redis设置key=UUID，value=xyz
        String token = UUID.randomUUID().toString();
        Integer expire = CookieConstant.EXPIRE;
        stringRedisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_TEMPLATE, token),
                openid, expire, TimeUnit.SECONDS);
        //4.cookie里设置openid=abc
        CookieUtil.set(response, CookieConstant.TOKEN, token, CookieConstant.EXPIRE);
        return ResultVoUtil.success();
    }

}
