package org.chenglj.mybatis.spring;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.FactoryBean;

import javax.annotation.Resource;

/*
 * @Description 
 * @Date 2022/9/5 22:28
 * @Author chenglj
 **/
public class MapperFactoryBean implements FactoryBean {

    private Class<?> mapperClass;

    public MapperFactoryBean(Class<?> mapperClass) {
        this.mapperClass = mapperClass;
    }

    @Resource
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public Object getObject() throws Exception {
        return sqlSessionFactory.openSession().getMapper(mapperClass);
    }

    @Override
    public Class<?> getObjectType() {
        return mapperClass;
    }
}
