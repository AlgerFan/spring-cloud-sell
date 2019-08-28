package cn.algerfan.user.server.repository;

import cn.algerfan.user.server.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author algerfan
 * @time 2019/8/28 20:39
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

    UserInfo findByOpenid(String openid);
}
