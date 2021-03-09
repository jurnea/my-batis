package org.chenglj.mybatis.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @Description Mybatis配置类
 * @Date 2021/3/7 20:56
 * @Author chenglj
 **/
public class Configuration {

    /** 驱动*/
    private String driver;
    /** url*/
    private String url;
    /** 用户名*/
    private String name;
    /** 密码*/
    private String password;

    Map<String,MapperStatement> mapperStatement = new HashMap<>();

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map<String, MapperStatement> getMapperStatement() {
        return mapperStatement;
    }

    public void setMapperStatement(Map<String, MapperStatement> mapperStatement) {
        this.mapperStatement = mapperStatement;
    }
}
