package org.chenglj.mybatis.spring;

import org.apache.ibatis.session.SqlSessionFactory;
import org.chenglj.mybatis.mapper.UserMapper2;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/*
 * 注册UserMapper2作为Spring的Bean
 * @Date 2022/9/5 1:55
 * @Author chenglj
 **/
@Component
public class UserMapperFactoryBean implements FactoryBean<UserMapper2> {

    @Resource
    private SqlSessionFactory sqlSessionFactory;

    public UserMapperFactoryBean(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public UserMapper2 getObject() throws Exception {
        return sqlSessionFactory.openSession().getMapper(UserMapper2.class);
    }

    @Override
    public Class<?> getObjectType() {
        return UserMapper2.class;
    }
}
