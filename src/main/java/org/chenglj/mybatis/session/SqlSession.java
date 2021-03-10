package org.chenglj.mybatis.session;


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
