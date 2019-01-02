package com.chatRobot.service;

import com.chatRobot.model.OneContent;

import java.util.List;

public interface IRobotService {

    public void learning(OneContent listenContent, OneContent answerContent);

    List<OneContent> Answer(String listenContent);

    int makeSureTheAnswer(OneContent answerContent);

    List<OneContent> getQuestions();
}