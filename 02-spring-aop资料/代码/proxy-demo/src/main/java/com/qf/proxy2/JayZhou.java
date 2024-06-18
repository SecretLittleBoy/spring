package com.qf.proxy2;

/**
 * 杰伦
 * @Author: 索尔 VX：214490523
 * @技术交流社区： qfjava.cn
 */
public class JayZhou implements SuperStar{
    @Override
    public String show(String city) {
        System.out.println("杰伦在"+city+"举行了无与伦比演唱会");
        return "演唱会太精彩了，观众都嗨晕了";
    }
}
