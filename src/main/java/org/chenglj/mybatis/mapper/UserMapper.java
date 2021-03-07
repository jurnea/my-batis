package org.chenglj.mybatis.mapper;

import org.chenglj.mybatis.entity.User;

/*
 * @Description 
 * @Date  
 * @Author chenglj
 **/
public interface UserMapper {

    User selectOne(Long id);

}
