package cn.algerfan.order.server.bus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author algerfan
 * @time 2019/8/26 10:46
 */
@RestController
public class GirlController {

    @Autowired
    private GirlConfig girlConfig;

    @RequestMapping("/girl/print")
    public String girl() {
        return "name: "+girlConfig.getName()+ "  age: "+girlConfig.getAge();
    }

}
