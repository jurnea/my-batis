package org.chenglj.mybatis.mapper;

import org.chenglj.mybatis.entity.User;

import java.util.List;

/*
 * @Description 
 * @Date  
 * @Author chenglj
 **/
public interface UserMapper {

    User selectOne(Long id);

    List<User> selectAll();

}
