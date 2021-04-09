package com.lagou.dao;

import com.lagou.pojo.User;

import java.util.List;

public interface IUserDao {

    //查询所有用户
    public List<User> findAll() throws Exception;


    //根据条件进行用户查询
    public User findByCondition(User user) throws Exception;

    //Add
    public void saveUser(User user);

    //Modify
    public void updateUser(User user);

    //Delete
    public int deleteUser(int id);


}
