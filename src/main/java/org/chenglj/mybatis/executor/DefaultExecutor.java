package org.chenglj.mybatis.executor;

import org.chenglj.mybatis.config.Configuration;
import org.chenglj.mybatis.config.MapperStatement;
import org.chenglj.mybatis.session.SqlSessionProxyHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
 * @Description 真实查询实现类
 * @Date 2021/3/9 23:40
 * @Author chenglj
 **/
public class DefaultExecutor implements Executor{

    private static Logger logger = LoggerFactory.getLogger(DefaultExecutor.class);

    private Configuration config;

    public DefaultExecutor(Configuration config) {
        this.config = config;
    }


    @Override
    public <E> List<E> query(MapperStatement statement, Object param) {

        Connection connection = null;
        List<E> list = new ArrayList<>();
        try {

            //查询语句 jdbc相关操作
            logger.info("executor query SQL[{}]...",statement.getSql());
            Class.forName(config.getDriver());

            connection = DriverManager.getConnection(config.getUrl(), config.getName(), config.getPassword());
            PreparedStatement preparedStatement = connection.prepareStatement(statement.getSql());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                System.out.println(resultSet.getRow());
                System.out.println(resultSet.getString("id"));
                System.out.println(resultSet.getString("name"));

            }
            //利用反射组合成需要返回的对象
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if( connection != null){
                    connection.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }
}
