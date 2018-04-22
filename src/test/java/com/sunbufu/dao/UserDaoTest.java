package com.sunbufu.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {

    Logger log = LoggerFactory.getLogger(UserDaoTest.class);

    @Autowired
    private UserDao userDao;

    @Test
    public void testSave() {
//        //插入
//        User user1 = new User();
//        user1.setUserName("王一蛋");
//        user1.setPassWord("123456");
//        userDao.save(user1);
//        log.info("user1={}", user1);
//        //批量插入
//        User user2 = new User();
//        user2.setUserName("王二蛋");
//        user2.setPassWord("123456");
//        User user3 = new User();
//        user3.setUserName("王三蛋");
//        user3.setPassWord("123456");
//        List<User> users = new ArrayList<>();
//        users.add(user2);
//        users.add(user3);
//        userDao.saveAll(users);
//        //全部查询
//        List<User> allUsers = userDao.findAll();
//        log.info("allUsers=" + allUsers);
//        //查询一个
//        User one = userDao.getOne(1);
//        log.info("user id=1 {}", one);
//        //修改
//        one.setPassWord("oneoneone");
//        userDao.saveAndFlush(one);
//        one = userDao.getOne(1);
//        log.info("updated one={}", one);
//        //删除
//        userDao.delete(one);
//        one = userDao.getOne(1);
//        log.info("deleted one={}", one);
    }

}