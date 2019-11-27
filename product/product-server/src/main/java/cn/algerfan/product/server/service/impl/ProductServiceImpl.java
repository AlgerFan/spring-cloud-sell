package cn.algerfan.product.server.service.impl;

import cn.algerfan.product.common.DecreaseStockOutput;
import cn.algerfan.product.common.ProductInfoOutput;
import cn.algerfan.product.server.domain.ProductInfo;
import cn.algerfan.product.server.enums.ProductStatusEnum;
import cn.algerfan.product.server.enums.ResultEnum;
import cn.algerfan.product.server.exception.ProductException;
import cn.algerfan.product.server.repository.ProductInfoRepository;
import cn.algerfan.product.server.service.ProductService;
import cn.algerfan.product.server.util.JsonUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author algerfan
 * @time 2019 11: 39
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfoOutput> productInfoList(List<String> productIdList) {
        List<ProductInfoOutput> productInfoOutput = new ArrayList<>();
        List<ProductInfo> byProductIdIn = productInfoRepository.findByProductIdIn(productIdList);
        for (ProductInfo productInfo : byProductIdIn) {
            ProductInfoOutput productInfoOutput1 = new ProductInfoOutput();
            BeanUtils.copyProperties(productInfo, productInfoOutput1);
            productInfoOutput.add(productInfoOutput1);
        }
        return productInfoOutput;
    }

    @Override
    public void decreaseStock(List<DecreaseStockOutput> cartDtoList) {
        List<ProductInfo> productInfoList = decreaseStockProcess(cartDtoList);
        //发送mq消息
        List<ProductInfoOutput> collect = productInfoList.stream().map(e -> {
            ProductInfoOutput productInfoOutput = new ProductInfoOutput();
            BeanUtils.copyProperties(e, productInfoOutput);
            return productInfoOutput;
        }).collect(Collectors.toList());
        amqpTemplate.convertAndSend("productInfo", JsonUtil.toJson(collect));
    }

    @Transactional
    public List<ProductInfo> decreaseStockProcess(List<DecreaseStockOutput> cartDtoList) {
        List<ProductInfo> productInfoList = new ArrayList<>();
        for (DecreaseStockOutput cartDto : cartDtoList) {
            Optional<ProductInfo> productInfoOptional = productInfoRepository.findById(cartDto.getProductId());
            //判断商品是否存在
            if (!productInfoOptional.isPresent()) {
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            ProductInfo productInfo = productInfoOptional.get();
            //判断库存是否足够
            int result = productInfo.getProductStock() - cartDto.getProductQuantity();
            if (result < 0) {
                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
            productInfoList.add(productInfo);
        }
        return productInfoList;
    }
}
