package com.chatRobot.service;

import com.chatRobot.model.User;

public interface IRobotService {

    public void learning(String listenContent, String answerContent);

    String Answer(String listenContent);
}