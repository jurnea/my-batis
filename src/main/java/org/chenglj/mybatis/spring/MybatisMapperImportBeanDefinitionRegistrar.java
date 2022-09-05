package org.chenglj.mybatis.spring;

import cn.hutool.core.util.ClassUtil;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Set;

/*
 * 通过实现 @ImportBeanDefinitionRegistrar的方式，向Spring容器中注入BeanDefinition
 * @Date 2022/9/6 0:38
 * @Author chenglj
 **/
public class MybatisMapperImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {

        String mapperPackage = "org.chenglj.mybatis.mapper";
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
