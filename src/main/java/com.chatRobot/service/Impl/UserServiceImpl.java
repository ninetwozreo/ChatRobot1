package com.chatRobot.service.Impl;

import com.chatRobot.dao.IUserDao;
import com.chatRobot.model.User;
import com.chatRobot.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserDao userDao;

    public User selectUser(long userId) {
        return this.userDao.selectUser(userId);
    }
    public void addUser(User user){

        userDao.insertUser(user);
    }

    @Override
    public Boolean checkOut(String username, String password) {
        String password1=userDao.selectPasswordByUserName(username);
        if(password.equals(password1)){
            return true;
        }else{
            return false;
        }

    }

    @Override
    public User selectUserByUserName(String username) {
        return userDao.selectUserByName(username);
    }
}