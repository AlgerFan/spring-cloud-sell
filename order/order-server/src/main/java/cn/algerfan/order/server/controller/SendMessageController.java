package cn.algerfan.order.server.controller;

import cn.algerfan.order.server.dto.OrderDto;
import cn.algerfan.order.server.message.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author algerfan
 * @time 2019/8/27 09:04
 */
@RestController
public class SendMessageController {

    @Autowired
    private StreamClient streamClient;

    /**
     * 发送消息测试
     */
    /*@GetMapping("/sendMessage")
    public void send(){
            streamClient.output().send(MessageBuilder.withPayload("Produce a message").build());
    }*/

    /**
     * 发送OrderDto对象
     */
    @GetMapping("/sendMessage")
    public void send(){
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId("123456");
        streamClient.output().send(MessageBuilder.withPayload(orderDto).build());
    }

}
