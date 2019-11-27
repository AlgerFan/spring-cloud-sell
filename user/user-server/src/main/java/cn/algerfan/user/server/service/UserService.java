package cn.algerfan.user.server.service;

import cn.algerfan.user.server.domain.UserInfo;

/**
 * @author algerfan
 * @time 2019/8/28 20:41
 */
public interface UserService {

    /**
     * 通过openid查找
     * @param openid
     * @return
     */
    UserInfo findByOpenid(String openid);

}
