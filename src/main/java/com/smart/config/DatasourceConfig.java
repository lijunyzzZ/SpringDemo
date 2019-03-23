package com.smart.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class DatasourceConfig {
    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://192.168.33.10:3306/sampledb");
        dataSource.setUsername("windows");
        dataSource.setPassword("XWY2ljya!");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return dataSource;

    }
    @Bean(name = "jdbcTemplate")
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(getDataSource());
    }
    public DataSourceTransactionManager dataSourceTransactionManager(){
        return new DataSourceTransactionManager(getDataSource());
    }

    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(DatasourceConfig.class);
        ctx.refresh();
        DataSource dataSource = (DataSource) ctx.getBean("dataSource");
        dataSource.getConnection();
    }
}
