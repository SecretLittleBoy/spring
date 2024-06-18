package com.qf.proxy;

/**
 * 房东
 * @Author: 索尔 VX：214490523
 * @技术交流社区： qfjava.cn
 */
public class HomeOwner implements IHomeRent{

    /**
     * 房屋出租
     */
    @Override
    public void rent() {
        System.out.println("发布房屋信息");
        System.out.println("带租客看房");
        System.out.println("签合同收房租");
    }
}
