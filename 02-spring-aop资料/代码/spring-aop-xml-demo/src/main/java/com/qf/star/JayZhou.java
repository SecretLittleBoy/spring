package com.qf.star;

import org.springframework.stereotype.Component;

/**
 * 杰伦
 * 
 * @Author: 索尔 VX：214490523
 *          @技术交流社区： qfjava.cn
 */
@Component
public class JayZhou implements SuperStar {
    @Override
    public String show(String city) {
        if (city == null) {
            throw new RuntimeException("断电了");
        }
        System.out.println("杰伦在" + city + "举行了无与伦比演唱会");
        return "演唱会太精彩了，观众都嗨晕了";
    }
}
