package cn.algerfan.order.server.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author algerfan
 * @time 2019/8/27 08:56
 */
public interface StreamClient {

    String INPUT = "myMessage-input";
    String OUTPUT = "myMessage-output";

    String INPUT2 = "myMessage-input2";
    String OUTPUT2 = "myMessage-output2";

    @Input(INPUT)
    SubscribableChannel input();

    @Output(OUTPUT)
    MessageChannel output();

    @Input(INPUT2)
    SubscribableChannel input2();

    @Output(OUTPUT2)
    MessageChannel output2();

}
