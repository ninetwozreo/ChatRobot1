package com.chatRobot.controller;

import javax.servlet.http.HttpServletRequest;

import com.chatRobot.model.User;
import com.chatRobot.service.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    @RequestMapping("/showUser.do")
    public void selectUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        long userId = Long.parseLong(request.getParameter("id"));
        User user = this.userService.selectUser(userId);
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(user));
        response.getWriter().close();
    }
    //注册新用户
    @RequestMapping("/addUser.do")
    public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response){
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String username = request.getParameter("username");
        String role = request.getParameter("role");
        User user=new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setUsername(username);
        user.setRole(role);
        user.setStatus(1);
        /*获取当前系统时间*/
      //  SimpleDateFormat ff=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        user.setRegTime(new Date());
        /*待修改，如何获取当前注册的IP*/
        user.setRegIp("1211");

        userService.addUser(user);
        User user1 = userService.selectUserByUserName(username);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject(user1);
        modelAndView.setViewName("redirect:/welcome.jsp");
        return modelAndView;
    }
    @RequestMapping("/login.do")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (userService.checkOut(username,password)) {
            userService.selectUserByUserName(username);
        }
        else {
            System.out.println("用户名或密码不正确");
        }

    }
}