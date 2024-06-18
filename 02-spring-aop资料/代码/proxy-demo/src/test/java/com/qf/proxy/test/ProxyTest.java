package com.qf.proxy.test;

import com.qf.proxy.HomeOwner;
import com.qf.proxy.HomeOwnerPlus;
import com.qf.proxy.HomeProxy;
import com.qf.proxy.HomeProxy2;
import org.junit.Test;

/**
 * @Author: 索尔 VX：214490523
 * @技术交流社区： qfjava.cn
 */
public class ProxyTest {

    /**
     * 模拟租客
     */
    @Test
    public void testRent(){
        HomeOwner homeOwner = new HomeOwner();
        homeOwner.rent();
    }

    /**
     * 使用中介租房
     */
    @Test
    public void testRentByProxy(){
        HomeOwnerPlus homeOwnerPlus = new HomeOwnerPlus();
        HomeProxy homeProxy = new HomeProxy(homeOwnerPlus);
        homeProxy.rent();
    }

    /**
     * 使用更强大的中介租房
     */
    @Test
    public void testRentByProxyPlus(){
        HomeOwnerPlus homeOwnerPlus = new HomeOwnerPlus();
        HomeProxy2 homeProxy = new HomeProxy2(homeOwnerPlus);
        homeProxy.rent();
    }
}
