package com.chatRobot.service.Impl;

import com.chatRobot.dao.IRobotDao;
import com.chatRobot.dao.IUserDao;
import com.chatRobot.model.Talk;
import com.chatRobot.model.User;
import com.chatRobot.service.IRobotService;
import com.chatRobot.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("robotService")
public class RobotServiceImpl implements IRobotService {

    @Resource
    private IRobotDao robotDao;
    @Override
    public void learning(String listenContent,String answerContent) {
//        String password1=userDao.selectPasswordByUserName(username);
        robotDao.insertContent(listenContent);
        int idIn=robotDao.afterInsert();
        robotDao.insertContent(answerContent);
        int idOut=robotDao.afterInsert();

//        String idqIn=robotDao.selectContentIDByContent(listenContent);
//        String idOut=robotDao.selectContentIDByContent(answerContent);
        Talk talk=new Talk();
        talk.setOutId(idOut);
        talk.setInId(idIn);
        robotDao.insertCorrespond(talk);

    }

    @Override
    public String Answer(String listenContent) {
       return robotDao.selectAnswerByListenContent(listenContent);
    }

}