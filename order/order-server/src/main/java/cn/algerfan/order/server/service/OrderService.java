package cn.algerfan.order.server.service;

import cn.algerfan.order.server.dto.OrderDto;

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

    /**
     * 完结订单（只能卖家操作）
     * @param orderId
     * @return
     */
    OrderDto finish(String orderId);
}
