package cn.algerfan.product.server.service.impl;

import cn.algerfan.product.server.domain.ProductCategory;
import cn.algerfan.product.server.service.ProductCategoryService;
import cn.algerfan.product.server.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author algerfan
 * @time 2019 15: 32
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return productCategoryRepository.findByCategoryTypeIn(categoryTypeList);
    }
}
