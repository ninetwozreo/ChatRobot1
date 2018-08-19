package com.chatRobot.dao;

import com.chatRobot.model.Talk;
import com.chatRobot.model.User;

public interface IRobotDao {

    User selectUser(long id);
    void learning(String listenContent,String answerContent);

    String selectPasswordByUserName(String username);

    User selectUserByName(String username);

//    void insertListening(String listenContent);
//
//    void insertAnswering(String answerContent);

    String selectContentIDByContent(String theWord);

    String selecttest(int id);

    int insertCorrespond(Talk talk);

    int insertContent(String theWord);

    int afterInsert();

    String selectAnswerByListenContent(String listenContent);
}