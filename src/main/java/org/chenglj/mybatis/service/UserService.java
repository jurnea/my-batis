package org.chenglj.mybatis.service;

import org.springframework.stereotype.Service;

/*
 * 一个普通的Spring Service Bean 用于测试Spring环境
 * @Date 2022/9/5 0:32
 * @Author chenglj
 **/
@Service
public class UserService {

    public String queryUser(){
        return "查询成功";
    }
}
