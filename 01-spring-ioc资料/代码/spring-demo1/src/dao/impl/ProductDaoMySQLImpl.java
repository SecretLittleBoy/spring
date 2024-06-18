package dao.impl;

import dao.ProductDao;
import entity.Product;

/**
 * @Author: 索尔 QQ：214490523
 * @技术交流社区： qfjava.cn
 */
public class ProductDaoMySQLImpl implements ProductDao {
    /**
     * 根据商品id查询商品
     * @return
     */
    @Override
    public Product selectProductById() {
        System.out.println("查询MySQL数据库");
        return new Product();
    }
}
