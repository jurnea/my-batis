package org.chenglj.mybatis.session;

import org.chenglj.mybatis.MybatisDemo;
import org.chenglj.mybatis.config.Configuration;
import org.chenglj.mybatis.config.MapperStatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/*
 * @Description
 * @Date 2021/3/7 18:16
 * @Author chenglj
 **/
public class SqlSessionFactoryBuilder {

    private static Logger logger = LoggerFactory.getLogger(MybatisDemo.class);

    private List<String> mapperSources = new ArrayList<>();

    private String configPropertiesPath ;

    private Configuration configuration;

    public SqlSessionFactoryBuilder(){
        this.configuration = new Configuration();
    }

    public SqlSessionFactory build(InputStream is){
        loadMybatisConfig(is);
        loadJdbcConfig();
        loadMappers();
        return build();
    }

    private SqlSessionFactory build() {
        SqlSessionFactory sessionFactory = new SqlSessionFactory();
        sessionFactory.setConfig(this.configuration);
        return sessionFactory;
    }

    private void loadJdbcConfig() {
        try {
            Properties p = new Properties();
            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(configPropertiesPath);
            p.load(inputStream);

            configuration.setDriver(p.getProperty("driver"));
            configuration.setUrl(p.getProperty("url"));
            configuration.setName(p.getProperty("username"));
            configuration.setPassword(p.getProperty("password"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadMappers() {
        try {

            Map<String,MapperStatement> map = new HashMap<>();

            for (String mapperSource : mapperSources) {
                InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(mapperSource);
                SAXReader saxReader = new SAXReader();
                Document document = saxReader.read(inputStream);
                Element rootElement = document.getRootElement();



                String namespace = rootElement.attributeValue("namespace");
                //加载所有的select语句
                List<Element> selects = rootElement.elements("select");
                for (Element select : selects) {
                    MapperStatement mapper = new MapperStatement();
                    mapper.setNamespace(namespace);
                    String id = select.attributeValue("id");
                    String resultType = select.attributeValue("resultType");
                    String sql = select.getStringValue();

                    mapper.setId(id);
                    mapper.setReturnType(resultType);
                    mapper.setSql(sql);

                    map.put(namespace+"."+id,mapper);
                }

                inputStream.close();


            }
            configuration.setMapperStatement(map);

        } catch (Exception e){

            e.printStackTrace();
        }
    }

    private void loadMybatisConfig(InputStream is) {
        SAXReader saxReader = new SAXReader();
        try {
            Document read = saxReader.read(is);
            Element rootElement = read.getRootElement();

            List<Element> elements = rootElement.element("environments").element("environment").element("dataSource").elements();
            for (Element element : elements) {
                logger.info("jdbc-value:{}",element.attributeValue("value"));
            }


            Element properties = rootElement.element("properties");
            configPropertiesPath = properties.attributeValue("resource");
            logger.info("加载jdbc配置文件路径:{}",configPropertiesPath);

            List<Element> mappers = rootElement.element("mappers").elements("mapper");
            for (Element mapper : mappers) {
                String mapperFilePath = mapper.attributeValue("resource");
                mapperSources.add(mapperFilePath);
                logger.info(mapperFilePath);
            }

            is.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
