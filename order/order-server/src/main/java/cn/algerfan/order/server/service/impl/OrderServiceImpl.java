package cn.algerfan.order.server.service.impl;

import cn.algerfan.order.server.repository.OrderDetailRepository;
import cn.algerfan.order.server.repository.OrderMasterRepository;
import cn.algerfan.order.server.domain.OrderDetail;
import cn.algerfan.order.server.domain.OrderMaster;
import cn.algerfan.order.server.dto.OrderDto;
import cn.algerfan.order.server.enums.OrderStatus;
import cn.algerfan.order.server.enums.PayStatus;
import cn.algerfan.order.server.service.OrderService;
import cn.algerfan.order.server.util.KeyUtil;
import cn.algerfan.product.client.ProductClient;
import cn.algerfan.product.common.DecreaseStockOutput;
import cn.algerfan.product.common.ProductInfoOutput;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author algerfan
 * @time 2019 17: 43
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private ProductClient productClient;

    @Override
    public OrderDto create(OrderDto orderDto) {
        String orderId = KeyUtil.genUniqueKey();
        // 1.查询商品信息（调用商品服务）
        List<String> productIds = orderDto.getOrderDetailList().stream()
                .map(OrderDetail::getProductId)
                .collect(Collectors.toList());
        List<ProductInfoOutput> productInfoList = productClient.productInfoList(productIds);
        // 2.计算总价
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail orderDetail : orderDto.getOrderDetailList()) {
            for (ProductInfoOutput productInfo : productInfoList) {
                if(productInfo.getProductId().equals(orderDetail.getProductId())) {
                    //计算总价
                    orderAmount = productInfo.getProductPrice()
                            .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                            .add(orderAmount);
                    BeanUtils.copyProperties(productInfo, orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.genUniqueKey());
                    //订单入库
                    orderDetailRepository.save(orderDetail);
                }
            }
        }
        // 3.扣库存（调用商品服务）
        List<DecreaseStockOutput> cartDtoList = orderDto.getOrderDetailList().stream()
                .map(e -> new DecreaseStockOutput(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productClient.decreaseStock(cartDtoList);
        //4.订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDto.setOrderId(KeyUtil.genUniqueKey());
        BeanUtils.copyProperties(orderDto, orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatus.NEW.getCode());
        orderMaster.setPayStatus(PayStatus.WAIT.getCode());
        orderMasterRepository.save(orderMaster);
        return orderDto;
    }
}
