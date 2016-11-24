package com.demorest.connection;

/**
 * Created by marcus on 09/11/2016.
 */

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages="com.demorest.database")
public class MyBatisConnectionFactory {

    @Bean
    @ConfigurationProperties(prefix="datasource.mysql")
    public DataSource mysqlDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public SqlSessionFactory getSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(mysqlDataSource());
        sessionFactory.setTypeAliasesPackage("com.demorest.entity");
        return sessionFactory.getObject();
    }
}
