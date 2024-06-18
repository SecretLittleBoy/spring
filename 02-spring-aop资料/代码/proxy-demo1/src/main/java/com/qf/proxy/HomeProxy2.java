package com.qf.proxy;

/**
 * @Author: 索尔 VX：214490523
 * @技术交流社区： qfjava.cn
 */
public class HomeProxy2 implements IHomeRent{

    private HomeOwnerPlus homeOwnerPlus;

    public HomeProxy2(HomeOwnerPlus homeOwnerPlus) {
        this.homeOwnerPlus = homeOwnerPlus;
    }

    @Override
    public void rent() {
        System.out.println("发布房屋信息");
        System.out.println("带租客看房");
        this.homeOwnerPlus.rent();
        System.out.println("维修");
        System.out.println("转租");
    }
}
