package org.chenglj.mybatis;


import org.apache.ibatis.session.SqlSessionFactory;
import org.chenglj.mybatis.mapper.UserMapper;
import org.chenglj.mybatis.mapper.UserMapper2;
import org.chenglj.mybatis.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
     * 测试将Mybatis的接口作为一个bean
     */
/*    @Test
    public void testRegisterMapperToSpringBean(){
        context.refresh();
        SqlSessionFactory sqlSessionFactory = context.getBean(SqlSessionFactory.class);
        UserMapper2 mapper = sqlSessionFactory.openSession().getMapper(UserMapper2.class);

        String beanName = UserMapper2.class.getSimpleName();


        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(UserMapper2.class).getBeanDefinition();
        beanDefinition.setBeanClass(mapper.getClass());
        beanDefinition.setBeanClassName(beanName);
        context.registerBeanDefinition(beanName, beanDefinition);
        context.refresh();


        UserMapper2 userMapper2Bean = (UserMapper2) context.getBean(beanName);

        System.out.println(userMapper2Bean.selectAll());
    }*/

    @Test
    public void test(){
        context.refresh();
        UserMapper2 bean = context.getBean(UserMapper2.class);
        System.out.println(bean.selectAll());
    }
}