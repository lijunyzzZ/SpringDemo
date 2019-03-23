package com.smart.service;

import com.smart.config.AppConf;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) //使用junit4进行测试
@ContextConfiguration(classes = {AppConf.class})
public class UserServiceTest {
    @Autowired
    private ApplicationContext context;

    @Test
    public void serviceTest() {
        UserService userService = (UserService) context.getBean("userService");
        System.out.println(userService.findUserByUserName("admin").getPassword());
    }

}
