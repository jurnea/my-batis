package org.chenglj.mybatis.config;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

/*
 * 数据库相关的配置环境
 * @Date 2022/9/5 0:36
 * @Author chenglj
 **/
@Configuration
public class DataSourceConfig {

    /**
     * 配置Mybatis的SqlSessionFactory
     * @return
     * @throws IOException
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;
    }
}
