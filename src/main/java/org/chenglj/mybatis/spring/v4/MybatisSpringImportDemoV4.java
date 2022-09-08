package org.chenglj.mybatis.spring.v4;

import org.chenglj.mybatis.spring.MybatisMapperScan;
import org.springframework.context.annotation.ComponentScan;

/*
 * 采用@Import注解的方式注册Mybatis Mapper 到Spring容器。与MybatisSpringImportDemo相比优化了包名写死到代码中的缺点
 * @Date 2022/9/6 0:46
 * @Author chenglj
 **/
@ComponentScan("org.chenglj.mybatis")
@MybatisMapperScanV4("org.chenglj.mybatis.mapper")
public class MybatisSpringImportDemoV4 {
}
