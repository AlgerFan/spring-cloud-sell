package cn.algerfan.user.server.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author algerfan
 * @time 2019/8/28 20:36
 */
@Entity
@Data
public class UserInfo {

    @Id
    private String id;

    private String username;

    private String password;

    private String openid;

    private Integer role;

}
