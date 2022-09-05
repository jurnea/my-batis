package org.chenglj.mybatis;

import org.chenglj.mybatis.spring.MybatisMapperImportBeanDefinitionRegistrar;
import org.chenglj.mybatis.spring.MybatisMapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/*
 * 采用@Import注解的方式注册Mybatis Mapper 到Spring容器。与MybatisSpringImportDemo相比优化了包名写死到代码中的缺点
 * @Date 2022/9/6 0:46
 * @Author chenglj
 **/
@ComponentScan("org.chenglj.mybatis")
@MybatisMapperScan("org.chenglj.mybatis.mapper")
public class MybatisSpringImportDemo2 {
}
