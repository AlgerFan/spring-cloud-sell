package cn.algerfan.product.server.service;

import cn.algerfan.product.server.domain.ProductCategory;

import java.util.List;

/**
 * 类目
 * @author algerfan
 * @time 2019 15: 30
 */
public interface ProductCategoryService {
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
