package org.chenglj.mybatis;

import org.chenglj.mybatis.spring.MybatisMapperImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/*
 * 采用@Import注解的方式注册Mybatis Mapper 到Spring容器。
 * @Date 2022/9/6 0:46
 * @Author chenglj
 **/
@ComponentScan("org.chenglj.mybatis")
@Import(MybatisMapperImportBeanDefinitionRegistrar.class)
public class MybatisSpringImportDemo {
}
