package cn.algerfan.order.server.controller;

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

    @GetMapping("/sendMessage")
    public void send(){
            String message = "now " + new Date();
            streamClient.output().send(MessageBuilder.withPayload(message).build());
    }

}
