package com.qf.entity;

import org.springframework.beans.factory.annotation.Value;

/**
 * @Author: 索尔 VX：214490523
 *          @技术交流社区： qfjava.cn
 */
public class Address {
    
        @Value("default address")
        private String address;
    
        public String getAddress() {
            return address;
        }
    
        @Override
        public String toString() {
            return "Address{" +
                    "address='" + address + '\'' +
                    '}';
        }
}
