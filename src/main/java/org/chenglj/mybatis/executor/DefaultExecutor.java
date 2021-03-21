package org.chenglj.mybatis.executor;

import org.chenglj.mybatis.config.Configuration;
import org.chenglj.mybatis.config.MapperStatement;
import org.chenglj.mybatis.util.ReflectionUtil;
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
        try {

            //查询语句 jdbc相关操作
            logger.info("executor query SQL[{}]...",statement.getSql().trim());
            Class.forName(config.getDriver());

            connection = DriverManager.getConnection(config.getUrl(), config.getName(), config.getPassword());
            PreparedStatement preparedStatement = connection.prepareStatement(sqlize(statement.getSql()));
            //处理占位符
            paramterize(preparedStatement,param);
            ResultSet resultSet = preparedStatement.executeQuery();

            //利用反射转换成所需的对象
            String returnType = statement.getReturnType();

            return ReflectionUtil.toList(Class.forName(returnType),resultSet);

            //利用反射组合成需要返回的对象
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if( connection != null){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>();
    }

    private String sqlize(String sql) {
        if(sql.contains("#")){
            return sql.replaceAll("\\#\\{\\w*\\}","?");
        }
        return sql;
    }

    /**
     * 处理占位符
     * @param preparedStatement
     * @param param
     */
    private void paramterize(PreparedStatement preparedStatement, Object param) {
        try {
            if(param instanceof Integer){
                preparedStatement.setInt(1,(int)param );
            } else if(param instanceof Long){
                preparedStatement.setLong(1,(long)param );
            } else if(param instanceof String){
                preparedStatement.setString(1,(String) param );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String sql = "select * from user where id = #{id} and name = #{name}";
        sql = sql.replaceAll("\\#\\{\\w*\\}","?");

        System.out.println(sql);
    }
}
