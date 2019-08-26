package cn.algerfan.order.converter;

import cn.algerfan.order.exception.OrderException;
import cn.algerfan.order.form.OrderForm;
import cn.algerfan.order.domain.OrderDetail;
import cn.algerfan.order.dto.OrderDto;
import cn.algerfan.order.enums.Result;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author algerfan
 * @time 2019 20: 44
 */
@Slf4j
public class OrderFormToOrderDto {

    public static OrderDto convert(OrderForm orderForm) {
        Gson gson = new Gson();
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName(orderForm.getName());
        orderDto.setBuyerAddress(orderForm.getAddress());
        orderDto.setBuyerPhone(orderForm.getPhone());
        orderDto.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList;
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>() {}.getType());
        } catch (Exception e) {
            log.error("json转化错误，String={}", orderForm.getItems());
            throw new OrderException(Result.PARAM_ERROR);
        }
        orderDto.setOrderDetailList(orderDetailList);
        return orderDto;
    }
}
