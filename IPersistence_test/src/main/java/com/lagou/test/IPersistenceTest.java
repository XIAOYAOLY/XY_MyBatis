package com.lagou.test;

import com.lagou.dao.IUserDao;
import com.lagou.io.Resources;
import com.lagou.pojo.User;
import com.lagou.sqlSession.SqlSession;
import com.lagou.sqlSession.SqlSessionFactory;
import com.lagou.sqlSession.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IPersistenceTest {

    @Test
    public void test() throws Exception {
        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //调用
        User user = new User();
        user.setId(1);
        user.setUsername("张三");
      /*  User user2 = sqlSession.selectOne("user.selectOne", user);

        System.out.println(user2);*/

       /* List<User> users = sqlSession.selectList("user.selectList");
        for (User user1 : users) {
            System.out.println(user1);
        }*/

        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        List<User> all = userDao.findAll();
        for (User user1 : all) {
            System.out.println(user1);
        }

        User insertUser = new User();
        insertUser.setId(4);
        insertUser.setUsername("tom");
        System.out.println("新增开始！");
        userDao.saveUser(insertUser);
        System.out.println("新增结束！");

        User updateUser = new User();
        updateUser.setId(2);
        updateUser.setUsername("Mary");
        System.out.println("修改开始！");
        userDao.updateUser(updateUser);
        System.out.println("修改结束！");

        int count = userDao.deleteUser(6);
        if(count>0){
            System.out.println("删除成功！");
        }

        List<User> allUserInfos = userDao.findAll();
        for (User user1 : allUserInfos) {
            System.out.println(user1);
        }

    }



}
