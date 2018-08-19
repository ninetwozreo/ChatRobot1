package com.chatRobot.service;

import com.chatRobot.model.User;

public interface IUserService {

    public User selectUser(long userId);
    public void addUser(User user);

    Boolean checkOut(String username, String password);

    User selectUserByUserName(String username);

//    public String learning(String 我听到的话,String 我应该回答的话);
}