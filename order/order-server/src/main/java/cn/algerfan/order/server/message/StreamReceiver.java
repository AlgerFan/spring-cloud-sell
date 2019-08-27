package cn.algerfan.order.server.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * @author algerfan
 * @time 2019/8/27 08:57
 */
@Component
@EnableBinding(StreamClient.class)
@Slf4j
public class StreamReceiver {

    @StreamListener(StreamClient.INPUT)
    public void process(Object message) {
        byte[] bytes = (byte[]) message;
        String payload = new String(bytes);
        log.info("StreamReceiver: {}", payload);
    }

}
