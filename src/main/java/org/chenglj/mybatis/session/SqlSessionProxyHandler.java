package org.chenglj.mybatis.session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Collection;

/*
 * @Description 
 * @Date 2021/3/9 23:28
 * @Author chenglj
 **/
public class SqlSessionProxyHandler implements InvocationHandler {

    private static Logger logger = LoggerFactory.getLogger(SqlSessionProxyHandler.class);

    private SqlSession sqlSession;

    public SqlSessionProxyHandler(SqlSession sqlSession) {

        logger.info("===");
        this.sqlSession = sqlSession;
    }

    /**
     * 通过动态代理调用方法
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {


        String statement = method.getDeclaringClass().getName()+"."+method.getName();
        logger.info("动态代理实际需要查询的类与方法:{}",statement);
        //返回类型是集合->则是查询语句
        if (Collection.class.isAssignableFrom(method.getReturnType())) {
            return sqlSession.selectList(statement,args ==null ? null:args[0]);
        } else {
            return sqlSession.selectOne(statement,args ==null ? null:args[0]);
        }
    }
}
