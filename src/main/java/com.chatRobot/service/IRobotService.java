package com.chatRobot.service;

import com.centit.fileserver.client.po.FileStoreInfo;
import com.chatRobot.model.OneContent;

import java.util.List;

public interface IRobotService {

    public void learning(OneContent listenContent, OneContent answerContent);

    List<OneContent> Answer(String listenContent);

    int makeSureTheAnswer(OneContent answerContent);

    List<OneContent> getQuestions();

    //保存文件信息
    void saveFileMsg(String fileId, String fileMd5, long fileSize, String fileName);
    //获取文件信息

    FileStoreInfo getFileMsgById(String fileId);
}