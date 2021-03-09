package org.chenglj.mybatis.session;

import org.chenglj.mybatis.config.Configuration;

/*
 * @Description 
 * @Date  
 * @Author chenglj
 **/
public class SqlSession implements AutoCloseable{

    private Configuration configuration;

    public SqlSession(Configuration configuration){
        this.configuration = configuration;
    }
    public <T> T getMapper(Class<T> clazz){


        return (T)clazz;
    }

    @Override
    public void close() throws Exception {
        this.close();
    }
}
