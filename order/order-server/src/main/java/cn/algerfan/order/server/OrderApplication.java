package cn.algerfan.order.server;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * *    @SpringBootApplication
 * *    @EnableDiscoveryClient   ——> @SpringCloudApplication
 * *    @EnableCircuitBreaker
 * @author algerfan
 */
@EnableFeignClients(basePackages = "cn.algerfan.product.client")
@SpringCloudApplication
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

}
