package org.chenglj.mybatis;

//import org.apache.ibatis.io.Resources;
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.chenglj.mybatis.entity.User;
import org.chenglj.mybatis.mapper.UserMapper;
import org.chenglj.mybatis.session.SqlSession;
import org.chenglj.mybatis.session.SqlSessionFactory;
import org.chenglj.mybatis.session.SqlSessionFactoryBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

/*
 * @Description 
 * @Date  
 * @Author chenglj
 **/
public class MybatisDemo {

    private static Logger logger = LoggerFactory.getLogger(MybatisDemo.class);


    public static void main(String[] args) throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        if( true){
            return;
        }
        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            User user = mapper.selectOne(1L);
            logger.info("查询用户:{}",user);
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
