package cn.algerfan.order.server.repository;

import cn.algerfan.order.server.domain.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author algerfan
 * @time 2019 17: 21
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

    /**
     * 通过orderId查询订单详情
     * @param orderId
     * @return
     */
    List<OrderDetail> findByOrderId(String orderId);
}
