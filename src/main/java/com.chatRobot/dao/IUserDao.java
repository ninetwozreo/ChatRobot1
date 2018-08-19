package com.chatRobot.dao;

import com.chatRobot.model.User;

public interface IUserDao {

    User selectUser(long id);
    void insertUser(User user);

    String selectPasswordByUserName(String username);

    User selectUserByName(String username);
}