package com.chatRobot.dao;

import com.chatRobot.model.OneContent;
import com.chatRobot.model.Talk;
import com.chatRobot.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IRobotDao  {

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
    //滴滴滴，mybatis的坑？
    int insertContent(OneContent oneContent);

    int afterInsert();

    List<OneContent> selectAnswerByListenContent(String listenContent);
    //查询是否已存在该问题
    OneContent selectListenContentByListenContent(String listenContent);
    //查询是否已存在该问题
    OneContent selectAnswerContentByAnswerContent(String answerContent);

    int updateOneContentTimes(OneContent askContent);
    //查询所有问题
    List<OneContent> selectQuestions();
}