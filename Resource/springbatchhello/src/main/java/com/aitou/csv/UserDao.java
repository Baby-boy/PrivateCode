package com.aitou.csv;

import java.util.List;

import com.aitou.mes.User;



public interface UserDao {

 User selectById(String id);
    List<User> findUsers();
    int insertman(User user);
}