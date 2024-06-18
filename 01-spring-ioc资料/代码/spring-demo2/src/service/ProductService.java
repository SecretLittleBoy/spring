package service;

import entity.Product;

public interface ProductService {
    /**
     * 根据商品id获得该商品
     * @return
     */
    Product getProductById();
}
