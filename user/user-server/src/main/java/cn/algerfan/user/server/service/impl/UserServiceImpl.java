package cn.algerfan.user.server.service.impl;

import cn.algerfan.user.server.domain.UserInfo;
import cn.algerfan.user.server.repository.UserInfoRepository;
import cn.algerfan.user.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author algerfan
 * @time 2019/8/28 20:43
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserInfo findByOpenid(String openid) {
        return userInfoRepository.findByOpenid(openid);
    }

}
