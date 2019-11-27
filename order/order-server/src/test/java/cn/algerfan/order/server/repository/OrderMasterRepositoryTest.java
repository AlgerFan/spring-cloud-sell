package cn.algerfan.order.server.repository;

import cn.algerfan.order.server.OrderApplicationTests;
import cn.algerfan.order.server.domain.OrderMaster;
import cn.algerfan.order.server.enums.OrderStatus;
import cn.algerfan.order.server.enums.PayStatus;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author algerfan
 */
@Component
public class OrderMasterRepositoryTest extends OrderApplicationTests {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Test
    public void testSave() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1234567");
        orderMaster.setBuyerName("AlgerFan");
        orderMaster.setBuyerPhone("1886131241241");
        orderMaster.setBuyerAddress("红旗区");
        orderMaster.setBuyerOpenid("1101110");
        orderMaster.setOrderAmount(new BigDecimal(2.5));
        orderMaster.setOrderStatus(OrderStatus.NEW.getCode());
        orderMaster.setPayStatus(PayStatus.WAIT.getCode());

        OrderMaster result = orderMasterRepository.save(orderMaster);
        Assert.assertTrue(result != null);
    }

}