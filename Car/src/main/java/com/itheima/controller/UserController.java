package com.itheima.controller;

import com.itheima.domain.Entity.CarUser;
import com.itheima.domain.Entity.User;
import com.itheima.service.CarUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author star
 * @date Created on 2021/12/4 16:33
 * @description
 */
@RequestMapping("/user")
@Controller
public class UserController {
    @Autowired
    CarUserService carUserService;

    @RequestMapping("/login")
    public String login(CarUser carUser, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        CarUser carUser1 = carUserService.findByUser(carUser);
        session.setAttribute("user", carUser1);
        if (carUser1 != null) {
            return "redirect:/jsp/frame.jsp";
        }
        return "redirect:/index.jsp";

    }
    @RequestMapping("/register")
    public void register(HttpServletRequest request,HttpServletResponse response) throws ParseException {
        String username = request.getParameter("username");

        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String telephone = request.getParameter("telephone");
        String sex = request.getParameter("sex");
        String birthday = request.getParameter("birthday");
        User user=new User();
        user.setUserUsername(username);
        user.setUserPassword(password);
        user.setUserEmail(email);
        user.setUserPhone(Long.valueOf(telephone));
        if (sex.equalsIgnoreCase("男")){
            user.setUserSex(1);
        }else {
            user.setUserSex(0);
        }
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Date parse = format.parse(birthday);
        user.setUserBirthday(parse);
    }

}
