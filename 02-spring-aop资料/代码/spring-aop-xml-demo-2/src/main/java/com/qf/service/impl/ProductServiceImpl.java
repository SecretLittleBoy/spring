package com.qf.service.impl;

import com.qf.entity.Product;
import com.qf.service.IProductService;
import org.springframework.stereotype.Component;

/**
 * @Author: 索尔 VX：214490523
 *          @技术交流社区： qfjava.cn
 */
@Component
public class ProductServiceImpl implements IProductService {
    @Override
    public void add(Product product) {
        System.out.println("添加商品");
    }

    @Override
    public void deleteById(Long id) {
        if (id == null) {
            throw new RuntimeException("id不能为空");
        }
        System.out.println("根据id删除商品");
    }

    @Override
    public void update(Product product) {
        System.out.println("修改商品");
    }

    @Override
    public Product getProductById(Long id) {
        System.out.println("根据id查询商品");
        return new Product();
    }
}
