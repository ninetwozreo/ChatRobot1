package com.chatRobot.controller;

import com.chatRobot.model.User;
import com.chatRobot.service.IRobotService;
import com.chatRobot.service.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Controller
@RequestMapping("/jarvers")
public class RobotController {

    @Resource
    private IRobotService robotService;

    @RequestMapping("/leaning")
    public void 我在学习呢(HttpServletRequest request, HttpServletResponse response,String 我听到的话,String 我应该回答的话) {
//        userService.learning(我听到的话,我应该回答的话);
        robotService.learning(我听到的话,我应该回答的话);

    }

}