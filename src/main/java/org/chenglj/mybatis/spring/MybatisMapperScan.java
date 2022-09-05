package org.chenglj.mybatis.spring;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Import(MybatisMapperImportBeanDefinitionRegistrar2.class)

@Documented
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MybatisMapperScan {
    String value();
}
