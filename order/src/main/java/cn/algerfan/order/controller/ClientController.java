package cn.algerfan.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author algerfan
 * @time 2019 11: 04
 */
@RestController
@Slf4j
public class ClientController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/getProductMsg")
    public String getProductMsg() {
        //第一种方式（直接使用restTemplate，url写死）
        /*
        @Autowired
        private RestTemplate restTemplate;
        response = restTemplate.getForObject("http://localhost:8080/msg", String.class);
        */
        //第二种方式（通过loadBalancerClient，获取URL）
        String response = "";
        RestTemplate restTemplate = new RestTemplate();
        ServiceInstance product = loadBalancerClient.choose("PRODUCT");
        String format = String.format("http://%s:%s", product.getHost(), product.getPort()) + "/msg";
        response = restTemplate.getForObject(format, String.class);
        log.info("response："+ response);
        //第三种方式（）
        /*
        @Autowired
        private ProductClient productClient;
        response = restTemplate.getForObject("http://PRODUCT/msg", String.class);
        return response;
        String response = productClient.productMsg();
        */
        return response;
    }


}