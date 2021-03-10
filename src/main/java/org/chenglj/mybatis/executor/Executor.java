package org.chenglj.mybatis.executor;

import org.chenglj.mybatis.config.MapperStatement;

import java.util.List;

/**
 * 查询执行接口
 */
public interface Executor {

   <E> List<E> query(MapperStatement statement, Object param);
}
