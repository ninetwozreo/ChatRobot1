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
    public void Learning(HttpServletRequest request, HttpServletResponse response,String listenContent,String answerContent) {
//        userService.learning(listenContent,answerContent);
        robotService.learning(listenContent,answerContent);

    }

    @RequestMapping("/talk")
    public String Talking(HttpServletRequest request, HttpServletResponse response,String listenContent) {
//        userService.learning(listenContent,answerContent);
        String a =robotService.Answer(listenContent);
        request.setAttribute("back",a);

        return a;

    }

}