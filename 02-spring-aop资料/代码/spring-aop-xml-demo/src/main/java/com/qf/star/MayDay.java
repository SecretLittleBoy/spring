package com.qf.star;

import org.springframework.stereotype.Component;

/**
 * 五月天
 * 
 * @Author: 索尔 VX：214490523
 *          @技术交流社区： qfjava.cn
 */
@Component
public class MayDay {

    public String show(String city) {
        System.out.println("五月天" + city + "举行了诺亚方舟演唱会");
        // if (city != null) {
        //     throw new RuntimeException("断电了");
        // }
        return "演唱会太精彩了，观众都嗨晕了";
    }
}
