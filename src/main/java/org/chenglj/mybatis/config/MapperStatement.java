package org.chenglj.mybatis.config;
/*
 * @Description 存放mapper.xml配置文件信息
 * @Date 2021/3/7 21:01
 * @Author chenglj
 **/
public class MapperStatement {

    /***
     * 命名空间
     */
    private String namespace;

    /**
     * sql的ID
     */
    private String id;

    /** 返回值类 */
    private String returnType;

    /**  sql 语句 */
    private String sql;

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
