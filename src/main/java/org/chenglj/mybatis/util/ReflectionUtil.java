package org.chenglj.mybatis.util;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * @Description 
 * @Date 2021/3/11 8:03
 * @Author chenglj
 **/
public class ReflectionUtil {
    public static <E>List<E> toList(Class<?> clazz, ResultSet resultSet) {

         List<E> list = new ArrayList<>();

        try {

            while (resultSet.next()){
                Object obj = clazz.newInstance();
                for (Field field : clazz.getDeclaredFields()) {
                    String name = field.getName();
                    Class<?> type = field.getType();
                    boolean longType = Long.class.isAssignableFrom(type);
                    boolean integerType = Integer.class.isAssignableFrom(type);

                    Object value = null;
                    if(longType){
                        value = resultSet.getLong(name);
                    } else if(integerType){
                        value = resultSet.getInt(name);
                    }else {
                        value = resultSet.getString(name);

                    }
                    field.setAccessible(true);
                    field.set(obj,value);

                }

                list.add((E)obj);

            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
