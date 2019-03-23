package com.smart.service;

import com.smart.config.AppConf;
import com.smart.dao.LoginDao;
import com.smart.dao.UserDao;
import com.smart.domain.LoginLog;
import com.smart.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class UserService {
    private UserDao userDao;
    private LoginDao loginDao;

    @Autowired
    private void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setLoginDao(LoginDao loginDao) {
        this.loginDao = loginDao;
    }

    public boolean hasMatchUser(String userName, String passwd) {
        return userDao.getMatchCount(userName, passwd) > 0;
    }

    public User findUserByUserName(String userName) {
        return userDao.findUserByUserName(userName);
    }

    @Transactional
    public void loginSuccess(User user) {
        user.setCredits(5 + user.getCredits());
        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(user.getUserId());
        loginLog.setIp(user.getLastIp());
        loginLog.setLoginDate(user.getLastVisit());
        userDao.updateLoginInfo(user);
        loginDao.insertLoginlog(loginLog);
    }
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(
                AppConf.class);

        UserService fqaService = (UserService) ctx.getBean("userService");
        System.out.println(fqaService.findUserByUserName("admin").getUserId());
    }
}
