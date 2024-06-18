package dao.impl;

import dao.ProductDao;
import entity.Product;

/**
 * @Author: 索尔 QQ：214490523
 * @技术交流社区： qfjava.cn
 */
public class ProductDaoOracleImpl implements ProductDao {
    @Override
    public Product selectProductById() {
        System.out.println("查询Oracle数据库");
        return new Product();
    }
}
