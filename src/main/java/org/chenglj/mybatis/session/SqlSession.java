package org.chenglj.mybatis.session;

import org.chenglj.mybatis.config.Configuration;
import org.chenglj.mybatis.config.MapperStatement;

import java.util.List;

/*
 * @Description 
 * @Date  
 * @Author chenglj
 **/
public interface SqlSession extends AutoCloseable{

    <T> T getMapper(Class<T> t);

    <T>T selectOne(String statement,Object param);

    <E>List<E> selectList(String statement,Object param);
}
