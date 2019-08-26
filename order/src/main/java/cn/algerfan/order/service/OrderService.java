package cn.algerfan.order.service;

import cn.algerfan.order.dto.OrderDto;

/**
 * @author algerfan
 * @time 2019 17: 42
 */
public interface OrderService {

    /**
     *  创建订单
     * @param orderDto dto对象
     * @return dto对象
     */
    OrderDto create(OrderDto orderDto);
}
