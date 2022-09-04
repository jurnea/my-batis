package org.chenglj.mybatis.mapper;

import org.apache.ibatis.annotations.Select;
import org.chenglj.mybatis.entity.User;

import java.util.List;

/**
 * 仅仅作为mybatis的实现接口
 */

public interface UserMapper2 {

    @Select("select * from user limit 10;")
    List<User> selectAll();
}
