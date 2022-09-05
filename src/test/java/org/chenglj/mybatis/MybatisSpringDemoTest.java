package org.chenglj.mybatis;


import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ReflectUtil;
import org.apache.ibatis.session.SqlSessionFactory;
import org.chenglj.mybatis.mapper.StudentMapper;
import org.chenglj.mybatis.mapper.UserMapper;
import org.chenglj.mybatis.mapper.UserMapper2;
import org.chenglj.mybatis.service.UserService;
import org.chenglj.mybatis.spring.MapperFactoryBean;
import org.chenglj.mybatis.spring.UserMapperFactoryBean;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Set;

public class MybatisSpringDemoTest {

    private static AnnotationConfigApplicationContext context ;


    public void runMybatisMapper(ApplicationContext context){
        UserMapper2 userMapper2Bean = context.getBean(UserMapper2.class);
        System.out.println(userMapper2Bean.selectAll());

        StudentMapper studentMapper = context.getBean(StudentMapper.class);
        System.out.println(studentMapper.getNameByPrimaryKey(2));
    }

    /**
     * 采用@Import注解的方式注册Mapper接口到Spring容器中。
     */
    @Test
    public void testImportRegistrar(){
        context = new AnnotationConfigApplicationContext();
        context.register(MybatisSpringImportDemo.class);
        context.refresh();
        runMybatisMapper(context);
    }

    @Test
    public void testImportRegistrarV2(){
        context = new AnnotationConfigApplicationContext();
        context.register(MybatisSpringImportDemo2.class);
        context.refresh();
        runMybatisMapper(context);
    }


}