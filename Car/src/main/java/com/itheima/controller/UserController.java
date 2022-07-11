package com.itheima.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.domain.Entity.Car;
import com.itheima.domain.Entity.CarUser;
import com.itheima.domain.util.ResultInfo;
import com.itheima.service.CarUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

    /**
     * 用户的登录操作
     *
     * @param response
     * @param request
     * @param session
     * @return
     * @throws IOException
     */
    @RequestMapping("/login")
    public String login(HttpServletResponse response, HttpServletRequest request, HttpSession session) throws IOException {
        //验证校验
        String check = request.getParameter("check");
        //从sesion中获取验证码
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");//为了保证验证码只能使用一次
        //比较
        session.removeAttribute("errorMsg");
        if (checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)) {
            //验证码错误
            ResultInfo info = new ResultInfo();
            //注册失败
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            //将info对象序列化为json
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            session.setAttribute("errorMsg", "验证码错误");
            return "redirect:/login.jsp";
        }
        try {
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            CarUser user = new CarUser();
            user.setUserPhone(Long.valueOf(userName));
            user.setUserPassword(password);
            if (userName == null || userName.length() == 0 || password == null || password.length() == 0) {
                session.setAttribute("errorMsg", "账号或密码不能为空");
                return "redirect:/login.jsp";
            }
            user = carUserService.findByUser(user);
            if (user != null) {
                session.setAttribute("user", user);
                return "redirect:/jsp/frame.jsp";
            }
            session.setAttribute("errorMsg", "账号或密码错误,");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("errorMsg", "账号或密码错误");
        }
        return "redirect:/login.jsp";
    }

    @RequestMapping("/findByPage")
    public void findByPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pageNum = request.getParameter("pageNum");
        String pageSize = request.getParameter("pageSize");
        String userPhone = request.getParameter("userPhone");
        String userAuditStatus = request.getParameter("userAuditStatus");
        CarUser carUser = new CarUser();
        int num = 1;
        int size = 5;
        if (pageNum != null) {
            num = Integer.parseInt(pageNum);
        }
        if (pageSize != null) {
            size = Integer.parseInt(pageSize);
        }
        if (userPhone != null) {
            try {
                if (userPhone == "" || userPhone.length() <= 0) {
                    userPhone = null;
                    carUser.setUserPhone(null);
                } else {
                    carUser.setUserPhone(Long.valueOf(userPhone));
                }
            } catch (Exception e) {
                e.printStackTrace();
                carUser.setUserPhone(null);
            }

        } else {
            carUser.setUserPhone(null);
        }
        if (userAuditStatus != null && !(userAuditStatus.equalsIgnoreCase(""))) {
            carUser.setUserAuditStatus(Integer.valueOf(userAuditStatus));
        } else {
            userAuditStatus = null;
            carUser.setUserAuditStatus(null);
        }
        PageHelper.startPage(num, size);
        List<CarUser> userList = carUserService.selectList(carUser);
        PageInfo<CarUser> pageInfo = new PageInfo<>(userList);
        String json = JSON.toJSONString(pageInfo);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(json);
        out.flush();
        out.close();
    }

    @RequestMapping("/findByQuery")
    public void findByQuery(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userPhone = request.getParameter("userPhone");
        String userAuditStatus = request.getParameter("userAuditStatus");
        CarUser carUser = new CarUser();
        try {
            if (userPhone == "" || userPhone.length() <= 0 || userPhone == null) {
                userPhone = null;
                carUser.setUserPhone(null);
            } else {
                carUser.setUserPhone(Long.valueOf(userPhone));
            }
        } catch (Exception e) {
            e.printStackTrace();
            carUser.setUserPhone(null);
        }
        if (userAuditStatus != null && !(userAuditStatus.equalsIgnoreCase(""))) {
            carUser.setUserAuditStatus(Integer.valueOf(userAuditStatus));
        } else {
            userAuditStatus = null;
            carUser.setUserAuditStatus(null);
        }
        String json = JSON.toJSONString(carUser);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(json);
        out.flush();
        out.close();
    }

    @RequestMapping("/userCheck")
    public void userCheck(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        String id = request.getParameter("id");
        CarUser carUser = new CarUser();
        carUser.setUserId(Long.valueOf(id));
        carUser = carUserService.selectByOne(carUser);
        session.setAttribute("userCheck", carUser);
        String json = JSON.toJSONString(carUser);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(json);
        out.flush();
        out.close();
    }

    @RequestMapping("/checkResult")
    public String checkResult(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        String userAuditStatus = request.getParameter("userAuditStatus");
        CarUser userCheck = (CarUser) session.getAttribute("userCheck");
        if (userAuditStatus != null) {
            userCheck.setUserAuditStatus(Integer.valueOf(userAuditStatus));
            carUserService.updateCheck(userCheck);
        }
        return "redirect:/jsp/userList.jsp";

    }

    @RequestMapping("/updateModify")
    public String updateModify(CarUser carUser, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        CarUser userCheck = (CarUser) session.getAttribute("userCheck");
        carUser.setUserId(userCheck.getUserId());
        carUserService.updateCheck(carUser);
        return "redirect:/jsp/userList.jsp";
    }

    @RequestMapping("/deleteUser")
    public void deleteUser(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        String id = request.getParameter("id");
        CarUser carUser = new CarUser();
        carUser.setUserId(Long.valueOf(id));
        carUser = carUserService.selectByOne(carUser);
        carUserService.deleteUser(carUser);
        String json = JSON.toJSONString(carUser);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(json);
        out.flush();
        out.close();
    }

    @RequestMapping("updatePassword")
    public void updatePassword(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        String newPassword = request.getParameter("newpassword");
        CarUser user = (CarUser) session.getAttribute("user");
        user.setUserPassword(newPassword);
        String json = null;
        if (user.getUserPassword() != null) {
            int i = carUserService.updatePassword(user);
            json = JSON.toJSONString(i);
        } else {
            json = JSON.toJSONString(null);
        }
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(json);
        out.flush();
        out.close();
    }

    @RequestMapping("register")
    public void register(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        String json="true";
        String userName = request.getParameter("userName");
        String userPassword = request.getParameter("userPassword");
        String userPhone = request.getParameter("userPhone");
        String userEmail = request.getParameter("userEmail");
        String userAddress = request.getParameter("userAddress");
        CarUser carUser=new CarUser();
        carUser.setUserName(userName);
        carUser.setUserPassword(userPassword);
        carUser.setUserPhone(Long.valueOf(userPhone));
        carUser.setUserEmail(userEmail);
        carUser.setUserAddress(userAddress);
        CarUser carUser1=carUserService.selectByUserPhone(carUser);
        if (carUser1!=null){
            json=JSON.toJSONString(null);
        }else {
            carUserService.insert(carUser);
            json = JSON.toJSONString(json);
        }
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(json);
        out.flush();
        out.close();
    }

}
