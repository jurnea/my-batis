package org.chenglj.mybatis.session;

import org.chenglj.mybatis.config.Configuration;

/*
 * @Description 
 * @Date  
 * @Author chenglj
 **/
public class SqlSessionFactory {

    private Configuration config;

    public SqlSession openSession(){

        return new SqlSession(config);
    }

    public Configuration getConfig() {
        return config;
    }

    public void setConfig(Configuration config) {
        this.config = config;
    }
}
