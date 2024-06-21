package com.qf.entity;

/**
 * @Author: 索尔 VX：214490523
 *          @技术交流社区： qfjava.cn
 */
public class Address {
    
        private String address;
    
        public Address() {
            address = "default address";
        }
    
        public Address(String address) {
            this.address = address;
        }
    
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
