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

    @Test
    public void testHire(){
//        HomeOwner homeOwner = new HomeOwner();
        HomeOwnerPlus homeOwner = new HomeOwnerPlus();
        HomeProxy homeProxy = new HomeProxy(homeOwner);
        homeProxy.rent();

    }

    @Test
    public void testHire2(){
//        HomeOwner homeOwner = new HomeOwner();
        HomeOwnerPlus homeOwner = new HomeOwnerPlus();
        HomeProxy2 homeProxy = new HomeProxy2(homeOwner);
        homeProxy.rent();

    }
}
