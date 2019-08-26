package cn.algerfan.order.server.controller;

import cn.algerfan.product.client.ProductClient;
import cn.algerfan.product.common.DecreaseStockOutput;
import cn.algerfan.product.common.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

/**
 * @author algerfan
 * @time 2019 11: 04
 */
@RestController
@Slf4j
public class ClientController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private ProductClient productClient;


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

    @PostMapping("/productInfoList")
    public String productInfoList() {
        List<ProductInfoOutput> productInfos = productClient.productInfoList(Collections.singletonList("157875227953464068"));
        log.info("response={}", productInfos);
        return "ok";
    }

    @PostMapping("/decreaseStock")
    public String decreaseStock() {
        productClient.decreaseStock(Collections.singletonList(new DecreaseStockOutput("157875227953464068", 2)));
        return "ok";
    }

}
