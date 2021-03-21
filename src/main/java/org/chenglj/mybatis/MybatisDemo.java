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
import java.util.List;

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

        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserMapper userMapper = session.getMapper(UserMapper.class);
            User user = userMapper.selectOne(1L);
            logger.info("selectOne查询用户:{}",user);
            List<User> users = userMapper.selectAll();
            logger.info("selectAll查询用户:{}",users);
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
