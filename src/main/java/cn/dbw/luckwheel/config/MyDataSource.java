package cn.dbw.luckwheel.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Configuration
public class MyDataSource {

    @Value("${db.url}")
    private String url;
    @Value("${db.name}")
    private String name;
    @Value("${db.pass}")
    private String pass;


    @Bean
    public JdbcTemplate mydataSource(){
        // JDBC模板依赖于连接池来获得数据的连接，所以必须先要构造连接池
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(url);
        dataSource.setUsername(name);
        dataSource.setPassword(pass);

        // 创建JDBC模板
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        // 这里也可以使用构造方法
        jdbcTemplate.setDataSource(dataSource);

        return jdbcTemplate;
    }

}
