package org.chenglj.mybatis.session;

import org.chenglj.mybatis.config.Configuration;
import org.chenglj.mybatis.config.MapperStatement;
import org.chenglj.mybatis.executor.DefaultExecutor;
import org.chenglj.mybatis.executor.Executor;

import java.lang.reflect.Proxy;
import java.util.List;

/*
 * @Description 
 * @Date 2021/3/9 23:22
 * @Author chenglj
 **/
public class DefaultSqlSession implements SqlSession {

    private Configuration config;

    private Executor executor;

    public DefaultSqlSession(Configuration config) {
        this.config = config;
        executor = new DefaultExecutor(config);
    }

    @Override
    public <T> T selectOne(String statement, Object param) {
        List<Object> objects = this.selectList(statement, param);
        if(objects == null || objects.isEmpty()){
            return null;
        } else if(objects.size() == 1){
            return (T)objects.get(0);
        } else {
            throw new RuntimeException("too much result rows");
        }
    }

    @Override
    public <E> List<E> selectList(String statement, Object param) {
        MapperStatement mapperStatement = config.getMapperStatement().get(statement);
        if(mapperStatement == null){
            throw new RuntimeException("namespace.id:"+statement+"不存在");
        }
        return executor.query(mapperStatement,param);
    }

    @Override
    public void close() throws Exception {

    }

    @Override
    public <T> T getMapper(Class<T> clazz) {

        return (T)Proxy.newProxyInstance(clazz.getClassLoader(),new Class[]{clazz},new SqlSessionProxyHandler(this));
    }
}
