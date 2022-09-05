package org.chenglj.mybatis.spring;

import cn.hutool.core.util.ClassUtil;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.StandardAnnotationMetadata;
import org.springframework.util.Assert;

import java.util.Map;
import java.util.Set;

/*
 * @Description 
 * @Date 2022/9/6 0:38
 * @Author chenglj
 **/
public class MybatisMapperImportBeanDefinitionRegistrar2 implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {

        Map<String, Object> annotationAttributes = annotationMetadata
                .getAnnotationAttributes(MybatisMapperScan.class.getName());
        String mapperPackage = (String)annotationAttributes.get("value");

        Assert.notNull(mapperPackage,"@MybatisMapperScan value can not be null");

        Set<Class<?>> mapperClasses = ClassUtil.scanPackage(mapperPackage);

        for (Class<?> mapperClazz : mapperClasses) {
            AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder
                    .genericBeanDefinition(MapperFactoryBean.class)
                    .getBeanDefinition();
            beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(mapperClazz);
            beanDefinitionRegistry.registerBeanDefinition(mapperClazz.getSimpleName(),beanDefinition);
        }

    }
}
