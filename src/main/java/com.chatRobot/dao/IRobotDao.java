package com.chatRobot.dao;

import com.chatRobot.model.User;

public interface IRobotDao {

    User selectUser(long id);
    void learning(String 我听到的话,String 我应该回答的话);

    String selectPasswordByUserName(String username);

    User selectUserByName(String username);

//    void insertListening(String 我听到的话);
//
//    void insertAnswering(String 我应该回答的话);

    String selectContentIDByContent(String 那句话);

    void insertCorrespond(String idIn, String idOut);

    void insertContent(String 一句话);
}