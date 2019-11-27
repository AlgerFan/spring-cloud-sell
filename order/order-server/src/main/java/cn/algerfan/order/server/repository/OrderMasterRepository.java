package cn.algerfan.order.server.repository;

import cn.algerfan.order.server.domain.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author algerfan
 * @time 2019 17: 20
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {
}
