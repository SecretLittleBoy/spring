package com.qf.service;

import com.qf.entity.Product;

public interface IProductService {

    void add(Product product);

    void deleteById(Long id);

    void update(Product product);

    Product getProductById(Long id);

}
