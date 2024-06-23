package com.qf.proxy;

/**
 * 中介
 * 
 * @Author: 索尔 VX：214490523
 *          @技术交流社区： qfjava.cn
 */
public class HomeProxy implements IHomeRent {

    private HomeOwnerPlus homeOwner;

    public HomeProxy(HomeOwnerPlus homeOwner) {
        this.homeOwner = homeOwner;
    }

    @Override
    public void rent() {
        System.out.println("发布房屋信息");
        System.out.println("带租客看房");
        this.homeOwner.rent();
    }
}
