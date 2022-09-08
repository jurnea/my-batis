package org.chenglj.mybatis.spring.v4;

import org.chenglj.mybatis.spring.MapperFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.classreading.MetadataReader;

import java.io.IOException;

/*
 * 自定义扫描mapper类，继承自Spring提供的ClassPathBeanDefinitionScanner
 * @Date 2022/9/8 22:56
 * @Author chenglj
 **/
public class MybatisMapperScanner extends ClassPathBeanDefinitionScanner {

    private static Logger logger = LoggerFactory.getLogger(MybatisMapperScanner.class);

    public MybatisMapperScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }



    /*@Override
    public int scan(String... basePackages) {
        int scan = super.scan(basePackages);
        logger.info("scan packages {} nums is {}",basePackages,scan);
        return scan;
    }*/
/*
    方案一、在scan结束后循环修改BeanDefinition中的集合内容
    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);
        for (BeanDefinitionHolder beanDefinitionHolder : beanDefinitionHolders) {
            BeanDefinition beanDefinition = beanDefinitionHolder.getBeanDefinition();
            beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(beanDefinition.getBeanClassName());
            beanDefinition.setBeanClassName(MapperFactoryBean.class.getName());
        }
        return beanDefinitionHolders;
    }
*/
    /**
     * 重写2个isCandidateComponent方法  是否候选的组件，我们只需要扫描接口，默认接口不会被扫描的
     * @param metadataReader
     * @return
     * @throws IOException
     */
    @Override
    protected boolean isCandidateComponent(MetadataReader metadataReader) throws IOException {
        return metadataReader.getClassMetadata().isInterface();
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        boolean anInterface = beanDefinition.getMetadata().isInterface();
        return anInterface;
    }

    /**
     * 方案二：重新注册beanDefinition方法，此处修改下beanDefinition的ClassName为我们自定义的MapperFactoryBean即可
     * @param definitionHolder
     * @param registry
     */
    /*@Override
    protected void registerBeanDefinition(BeanDefinitionHolder definitionHolder, BeanDefinitionRegistry registry) {
        BeanDefinition beanDefinition = definitionHolder.getBeanDefinition();
        beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(beanDefinition.getBeanClassName());
        beanDefinition.setBeanClassName(MapperFactoryBean.class.getName());
        super.registerBeanDefinition(definitionHolder, registry);
    }*/

    /**
     * 重新方案三 推荐
     * @param beanDefinition
     * @param beanName
     */
    @Override
    protected void postProcessBeanDefinition(AbstractBeanDefinition beanDefinition, String beanName) {
        beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(beanDefinition.getBeanClassName());
        //BeanClass和BeanClassName二则设置其一即可，Spring会优先使用beanClassName，注意BeanClassName一定要是Class的全称
        beanDefinition.setBeanClass(MapperFactoryBean.class);
        beanDefinition.setBeanClassName(MapperFactoryBean.class.getName());
        super.postProcessBeanDefinition(beanDefinition, beanName);
    }
}
