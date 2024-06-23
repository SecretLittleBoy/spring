package com.qf.proxy;

/**
 * 比较聪明的房东
 * 
 * @Author: 索尔 VX：214490523
 *          @技术交流社区： qfjava.cn
 */
public class HomeOwnerPlus implements IHomeRent {
    @Override
    public void rent() {
        System.out.println("签合同收租金");
    }
}
