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

//    private static SqlSessionFactory sqlSessionFactory;

    static {
        context = new AnnotationConfigApplicationContext();
        context.register(MybatisSpringDemo.class);


    }

    /**
     * 测试Spring的环境
     */
    @Test
    public void testSpringEnv(){
        context.refresh();

        UserService userService = context.getBean(UserService.class);
        System.out.println(userService.queryUser());
        Assert.assertNotNull(userService.queryUser());
    }

    /**
     * 测试原始Mybatis的调用
     * 注意，这里是通过XML文件访问Mybatis的，所以需要在Mybatis的配置文件中配置如下信息才能生效
     * <mapper resource="mapper/UserMapper.xml"/>
     */
    @Test
    public void testOriginMybatis(){
        context.refresh();
        SqlSessionFactory sqlSessionFactory = context.getBean(SqlSessionFactory.class);
        UserMapper mapper = sqlSessionFactory.openSession().getMapper(UserMapper.class);
        System.out.println(mapper.selectOne(1L));
    }

    @Test
    public void testUserMapper2(){
        context.refresh();
        SqlSessionFactory sqlSessionFactory = context.getBean(SqlSessionFactory.class);
        UserMapper2 mapper = sqlSessionFactory.openSession().getMapper(UserMapper2.class);
        System.out.println(mapper.selectAll());
    }

    /**
     * 测试将Mybatis的UserMapper2接口作为一个bean
     */
    @Test
    public void testUserMapper2ByBeanDefinitionRegister(){
        String beanName = UserMapper2.class.getSimpleName();
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder
                .genericBeanDefinition(UserMapperFactoryBean.class)
                .getBeanDefinition();
        context.registerBeanDefinition(beanName, beanDefinition);
        context.refresh();
        UserMapper2 userMapper2Bean = (UserMapper2) context.getBean(beanName);
        System.out.println(userMapper2Bean.selectAll());
    }

    /**
     * 测试批量注册Mapper,与上一个相比不用写死
     */
    @Test
    public void testRegisterMapperBatch(){
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder
                .genericBeanDefinition(MapperFactoryBean.class)
                .getBeanDefinition();

        //1.为MapperFactoryBean的构造器添加值 UserMapper2.class
        ConstructorArgumentValues constructorArgumentValues = new ConstructorArgumentValues();
        constructorArgumentValues.addGenericArgumentValue(UserMapper2.class);
        beanDefinition.setConstructorArgumentValues(constructorArgumentValues);
        context.registerBeanDefinition(UserMapper2.class.getSimpleName(), beanDefinition);


        //2.注册StudentMapper
        AbstractBeanDefinition studentBeanDefinition = BeanDefinitionBuilder
                .genericBeanDefinition(MapperFactoryBean.class)
                .getBeanDefinition();
        //为构造器传入Student2Mapper.class
        studentBeanDefinition.getConstructorArgumentValues().addGenericArgumentValue(StudentMapper.class);

        context.registerBeanDefinition(StudentMapper.class.getSimpleName(),studentBeanDefinition);

        context.refresh();

        runMybatisMapper(context);
    }

    public void runMybatisMapper(ApplicationContext context){
        UserMapper2 userMapper2Bean = context.getBean(UserMapper2.class);
        System.out.println(userMapper2Bean.selectAll());

        StudentMapper studentMapper = context.getBean(StudentMapper.class);
        System.out.println(studentMapper.getNameByPrimaryKey(2));
    }

    /**
     * 通过包扫描的方式扫描，与上一种方式相比不用一个一个写
     */
    @Test
    public void testRegisterMapperBatchByScan(){
        String mapperPackage = "org.chenglj.mybatis.mapper";
        Set<Class<?>> mapperClasses = ClassUtil.scanPackage(mapperPackage);
        for (Class<?> mapperClazz : mapperClasses) {
            AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder
                    .genericBeanDefinition(MapperFactoryBean.class)
                    .getBeanDefinition();
            beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(mapperClazz);
            context.registerBeanDefinition(mapperClazz.getSimpleName(),beanDefinition);
        }

        context.refresh();

        runMybatisMapper(context);


    }

}