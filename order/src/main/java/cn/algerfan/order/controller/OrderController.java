package cn.algerfan.order.controller;

import cn.algerfan.order.form.OrderForm;
import cn.algerfan.order.converter.OrderFormToOrderDto;
import cn.algerfan.order.dto.OrderDto;
import cn.algerfan.order.enums.Result;
import cn.algerfan.order.exception.OrderException;
import cn.algerfan.order.service.OrderService;
import cn.algerfan.order.util.ResultVoUtil;
import cn.algerfan.order.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author algerfan
 * @time 2019 17: 44
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     *  1.参数校验
     *  2.查询商品信息（调用商品服务）
     *  3.计算总价
     *  4.扣库存（调用商品服务）
     *  5.订单入库
     */
    @PostMapping("/create")
    public ResultVo<Object> create(@Valid OrderForm orderForm,
                                   BindingResult bindingResult) throws OrderException {
        if(bindingResult.hasErrors()) {
         log.error("【创建订单】参数不正确，orderForm={}", orderForm);
         throw new OrderException(Result.PARAM_ERROR.getCode(),
                 Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }

        OrderDto orderDto = OrderFormToOrderDto.convert(orderForm);
        if(CollectionUtils.isEmpty(orderDto.getOrderDetailList())) {
            log.error("创建订单，购物车为空");
            throw new OrderException(Result.CART_EMPTY);
        }
        OrderDto orderDto1 = orderService.create(orderDto);
        Map<String, String> map = new HashMap<>(5);
        map.put("order", orderDto1.getOrderId());
        return ResultVoUtil.success(map);
    }

}
