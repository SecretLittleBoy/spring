package service.impl;

import dao.ProductDao;
import dao.impl.ProductDaoMySQLImpl;
import dao.impl.ProductDaoOracleImpl;
import entity.Product;
import service.ProductService;

/**
 * @Author: 索尔 QQ：214490523
 * @技术交流社区： qfjava.cn
 */
public class ProductServiceImpl implements ProductService {

    ProductDao productDao = new ProductDaoMySQLImpl();
//    ProductDao productDao = new ProductDaoOracleImpl();

    @Override
    public Product getProductById() {
        return productDao.selectProductById();
    }
}
