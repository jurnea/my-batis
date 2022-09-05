package org.chenglj.mybatis.mapper;

import org.apache.ibatis.annotations.Select;

/**
 * Student表Mapper
 */
public interface StudentMapper {

    @Select("select student_name from student where id = #{id}")
    String getNameByPrimaryKey(Integer id);
}
