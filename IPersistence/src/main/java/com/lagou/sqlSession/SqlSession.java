package com.lagou.sqlSession;

import java.util.List;

public interface SqlSession {

    //查询所有
    //E多个实体类型 Object...合并参
    public <E> List<E> selectList(String statementid,Object... params) throws Exception;

    //根据条件查询单个
    //T某一个实体类型
    public <T> T selectOne(String statementid,Object... params) throws Exception;


    //为Dao接口生成代理实现类
    public <T> T getMapper(Class<?> mapperClass);


}
