package com.smart.dao;

import com.smart.domain.LoginLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDao {
    private JdbcTemplate jdbcTemplate;
    private final static String INSERT_LOGIN_SQL = "insert into t_login_log(user_id,ip,login_datetime) values(?,?,?)";

    public void insertLoginlog(LoginLog loginLog) {
        Object[] args = {loginLog.getUserId(), loginLog.getIp(), loginLog.getLoginlogId()};
        jdbcTemplate.update(INSERT_LOGIN_SQL);
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
