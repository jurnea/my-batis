package org.chenglj.mybatis.spring.v4;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

//V4 版本

@Import(MybatisMapperImportBeanDefinitionRegistrarV4.class)
@Documented
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MybatisMapperScanV4 {
    String value();
}
