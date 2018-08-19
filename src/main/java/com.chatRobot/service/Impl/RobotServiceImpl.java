package com.chatRobot.service.Impl;

import com.chatRobot.dao.IRobotDao;
import com.chatRobot.dao.IUserDao;
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
    public void learning(String 我听到的话,String 我应该回答的话) {
//        String password1=userDao.selectPasswordByUserName(username);
        robotDao.insertContent(我听到的话);
        robotDao.insertContent(我应该回答的话);

        String idIn=robotDao.selectContentIDByContent(我听到的话);
        String idOut=robotDao.selectContentIDByContent(我应该回答的话);
        robotDao.insertCorrespond(idIn,idOut);



    }

}