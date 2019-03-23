package com.smart.dao;

import com.smart.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private JdbcTemplate jdbcTemplate;
    private final static String GETUSER_SQL = "select * From t_user where user_name = ?";

    private final static String MATCHCOUNT_SQL = "select count(*) From t_user where user_name = ? and user_password";

    private final static String UPDATE_LOGIN_INFO_SQL = "update t_user set last_visit=?,last_ip=?,credits=?,where user_id" +
            "=?";

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getMatchCount(String userName, String passwd) {
        return jdbcTemplate.queryForObject(MATCHCOUNT_SQL, new Object[]{userName, passwd}, Integer.class);
    }

    public User findUserByUserName(final String userName) {
        User user1 = jdbcTemplate.queryForObject(GETUSER_SQL, new Object[]{userName}, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User customer = new User();
                customer.setUserId(rs.getInt("user_id"));
                customer.setUserName(rs.getString("user_name"));
                return customer;
            }
        });
        return user1;
    }

    public void updateLoginInfo(User user) {
        jdbcTemplate.update(UPDATE_LOGIN_INFO_SQL, new Object[]{user.getLastVisit(), user.getLastIp(), user.getCredits(), user.getUserId()});
    }

}
