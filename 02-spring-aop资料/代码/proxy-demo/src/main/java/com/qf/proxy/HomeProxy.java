package com.qf.proxy;

/**
 * @Author: 索尔 VX：214490523
 * @技术交流社区： qfjava.cn
 */
public class HomeProxy implements IHomeRent{

    private HomeOwnerPlus homeOwnerPlus;

    public HomeProxy(HomeOwnerPlus homeOwnerPlus) {
        this.homeOwnerPlus = homeOwnerPlus;
    }

    /**
     * 房屋出租
     */
    @Override
    public void rent() {
        System.out.println("中介发布房屋信息");
        System.out.println("中介带租客看房");
        //让房东来签合同
        this.homeOwnerPlus.rent();
    }
}
