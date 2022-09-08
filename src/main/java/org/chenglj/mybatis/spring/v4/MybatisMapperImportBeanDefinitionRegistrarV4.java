package org.chenglj.mybatis.spring.v4;

import org.chenglj.mybatis.spring.MybatisMapperScan;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.Assert;

import java.util.Map;

/*
 * v4 第四个版本 Mybatis扫描注册beanDefinition，采用Spring的扫描类
 * @Date 2022/9/6 0:38
 * @Author chenglj
 **/
public class MybatisMapperImportBeanDefinitionRegistrarV4 implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {

        Map<String, Object> annotationAttributes = annotationMetadata
                .getAnnotationAttributes(MybatisMapperScanV4.class.getName());
        String mapperPackage = (String)annotationAttributes.get("value");

        Assert.notNull(mapperPackage,"@MybatisMapperScan value can not be null");

        ClassPathBeanDefinitionScanner mapperScan = new MybatisMapperScanner(beanDefinitionRegistry);
        mapperScan.scan(mapperPackage);
    }
}
