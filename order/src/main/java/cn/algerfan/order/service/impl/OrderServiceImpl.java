package cn.algerfan.order.service.impl;

import cn.algerfan.order.repository.OrderDetailRepository;
import cn.algerfan.order.repository.OrderMasterRepository;
import cn.algerfan.order.domain.OrderMaster;
import cn.algerfan.order.dto.OrderDto;
import cn.algerfan.order.enums.OrderStatus;
import cn.algerfan.order.enums.PayStatus;
import cn.algerfan.order.service.OrderService;
import cn.algerfan.order.util.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author algerfan
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Override
    public OrderDto create(OrderDto orderDto) {
        // 1.查询商品信息（调用商品服务）
        // 2.计算总价
        //3.订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDto.setOrderId(KeyUtil.genUniqueKey());
        BeanUtils.copyProperties(orderDto, orderMaster);
        orderMaster.setOrderAmount(new BigDecimal(5));
        orderMaster.setOrderStatus(OrderStatus.NEW.getCode());
        orderMaster.setPayStatus(PayStatus.WAIT.getCode());
        orderMasterRepository.save(orderMaster);
        return orderDto;
    }
}
