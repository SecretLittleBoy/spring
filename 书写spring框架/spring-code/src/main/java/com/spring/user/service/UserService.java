package com.spring.user.service;

import com.spring.springframework.*;

@Component("userService")
@Transactional
public class UserService implements BeanNameAware, ApplicationContextAware {

    @Autowired
    private AddressService addressService;

    private SpringApplicationContext applicationContext;
    private String beanName;

    public void test() {
        System.out.println(addressService);
        System.out.println(applicationContext);
        System.out.println(beanName);
    }

    @Override
    public void setApplicationContext(SpringApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }
}
