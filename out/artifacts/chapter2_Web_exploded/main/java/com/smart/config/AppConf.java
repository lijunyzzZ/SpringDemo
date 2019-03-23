package com.smart.config;

import com.smart.dao.UserDao;
import com.smart.domain.User;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan({"com.smart.service","com.smart.dao","com.smart.aop"})
@Import(DatasourceConfig.class)
public class AppConf {
    @Bean(name = "user")
    public User user() {
        return new User();
    }

    @Bean(name = "UserDao")
    public UserDao getUserDao() {
        return new UserDao();
    }
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AppConf.class);
        ctx.refresh();
        UserDao user = (UserDao) ctx.getBean(UserDao.class);
        System.out.println();
    }
}
