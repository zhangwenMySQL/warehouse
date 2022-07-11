package com.itheima.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.domain.Entity.*;
import com.itheima.domain.util.ResultInfo;
import com.itheima.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;

/**
 * @author star
 * @date Created on 2021/12/4 16:33
 * @description
 */
@RequestMapping("/car")
@Controller
public class CarController {
    @Autowired
    CarUserService carUserService;
    @Autowired
    CarService carService;
    @Autowired
    CarIndentService carIndentService;
    @Autowired
    CarIssueService carIssueService;
    @Autowired
    CarImageService carImageService;

    /**
     * 查询车辆相关信息
     * @param request
     * @param response
     * @param session
     * @throws IOException
     */
    @RequestMapping("/findByPage")
    public void findByPage(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        CarUser user = (CarUser) session.getAttribute("user");
        String pageNum = request.getParameter("pageNum");
        String pageSize = request.getParameter("pageSize");
        String carBrand = request.getParameter("carBrand");
        String carCheck = request.getParameter("carCheck");
        String carPrice = request.getParameter("carPrice");
        Car car = new Car();
        int num = 1;
        int size = 5;
        if (pageNum != null) {
            num = Integer.parseInt(pageNum);
        }
        if (pageSize != null) {
            size = Integer.parseInt(pageSize);
        }
        if (carPrice != null) {
            try {
                if (carPrice == "" || carPrice.length() <= 0) {
                    carPrice = null;
                    car.setCarPrice(null);
                } else {
                    car.setCarPrice(carPrice);
                }
            } catch (Exception e) {
                e.printStackTrace();
                car.setCarPrice(null);
            }
        } else {
            car.setCarPrice(null);
        }
        if (carBrand != null && !(carBrand.equalsIgnoreCase(""))) {
            car.setCarBrand(carBrand);
        } else {
            carBrand = null;
            car.setCarBrand(null);
        }
        if (carCheck != null && !(carCheck.equalsIgnoreCase(""))) {
            car.setCarCheck(Integer.valueOf(carCheck));
        } else {
            carCheck = null;
            car.setCarCheck(null);
        }
        PageHelper.startPage(num, size);
        List<Car> carList = carService.selectList(car, user);
        PageInfo<Car> pageInfo = new PageInfo<>(carList);
        String json = JSON.toJSONString(pageInfo);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(json);
        out.flush();
        out.close();
    }

    @RequestMapping("/findByQuery")
    public void findByQuery(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String carBrand = request.getParameter("carBrand");
        String carPrice = request.getParameter("carPrice");
        String carCheck = request.getParameter("carCheck");
        Car car = new Car();
        if (carPrice != null) {
            try {
                if (carPrice == "" || carPrice.length() <= 0) {
                    carPrice = null;
                    car.setCarPrice(null);
                } else {
                    car.setCarPrice(carPrice);
                }
            } catch (Exception e) {
                e.printStackTrace();
                car.setCarPrice(null);
            }
        } else {
            car.setCarPrice(null);
        }
        if (carBrand != null && !(carBrand.equalsIgnoreCase(""))) {
            car.setCarBrand(carBrand);
        } else {
            carBrand = null;
            car.setCarBrand(null);
        }
        if (carCheck != null && !(carCheck.equalsIgnoreCase(""))) {
            car.setCarCheck(Integer.valueOf(carCheck));
        } else {
            carCheck = null;
            car.setCarCheck(null);
        }
        String json = JSON.toJSONString(car);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(json);
        out.flush();
        out.close();
    }

    @RequestMapping("/carCheck")
    public void userCheck(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        CarUser user = (CarUser) session.getAttribute("user");
        String id = request.getParameter("id");
        Car car = new Car();
        car.setCarId(Long.valueOf(id));
        car = carService.selectByOne(car);
        String json;
        if (user.getRoleId() == 1 || car.getCarCheck() == 1) {
            session.setAttribute("carCheck", car);
            json = JSON.toJSONString(car);
        } else {
            json = JSON.toJSONString(null);
        }
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(json);
        out.flush();
        out.close();
    }

    //
    @RequestMapping("/checkResult")
    public String checkResult(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        String carCheck = request.getParameter("carCheck");
        Car car = (Car) session.getAttribute("carCheck");
        if (carCheck != null) {
            car.setCarCheck(Integer.valueOf(carCheck));
            carService.updateCheck(car);
            CarIssue carIssue = new CarIssue();
            carIssue.setCarId(car.getCarId());
            carIssue.setIssueCheck(Integer.valueOf(carCheck));
            carIssueService.updateByIssueCheck(carIssue);

        }
        return "redirect:/jsp/carList.jsp";

    }

    //
    @RequestMapping("/updateModify")
    public void updateModify(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        Car car = (Car) session.getAttribute("carCheck");
        String carBrand = request.getParameter("carBrand");
        String carSeries = request.getParameter("carSeries");
        String carAgeLimit = request.getParameter("carAgeLimit");
        String carPrice = request.getParameter("carPrice");
        String json = null;
        if (carBrand != null) {
            car.setCarBrand(carBrand);
        }
        if (carSeries != null) {
            car.setCarSeries(carSeries);
        }
        if (carAgeLimit != null) {
            car.setCarAgeLimit(carAgeLimit);
        }
        if (carPrice != null) {
            try {
                double a = Double.parseDouble(carPrice);
                car.setCarPrice(carPrice);
                json = JSON.toJSONString(car);
                carService.updateCheck(car);
            } catch (Exception e) {
                e.printStackTrace();
                json = JSON.toJSONString(null);
            }
        } else {
            json = JSON.toJSONString(null);
        }
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(json);
        out.flush();
        out.close();
    }

    @RequestMapping("/deleteCar")
    public void deleteCar(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        CarUser user = (CarUser) session.getAttribute("user");
        String id = request.getParameter("id");
        Car car = new Car();
        car.setCarId(Long.valueOf(id));
        car = carService.selectByOne(car);
        carService.deleteCarByIssue(car,user);
        String json = JSON.toJSONString(car);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(json);
        out.flush();
        out.close();
    }

    @RequestMapping("/carIssueSee")
    public void carIssueSee(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        String id = request.getParameter("id");
        CarImage carImage=new CarImage();
        carImage.setCarId(Long.valueOf(id));
        carImage=carImageService.selecyByOne(carImage);
        session.setAttribute("image",carImage.getImageAddress());
        String json = JSON.toJSONString(carImage);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(json);
        out.flush();
        out.close();
    }

    @RequestMapping("/buyCar")
    public void buyCar(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        String id = request.getParameter("id");
        CarIndent carIndent = new CarIndent();
        carIndent.setCarId(Long.valueOf(id));
        Car car = new Car();
        car.setCarId(Long.valueOf(id));
        car = carService.selectByOne(car);
        CarUser user = (CarUser) session.getAttribute("user");
        carIndent.setBuyerUserId(user.getUserPhone());
        carIndent = carIndentService.selectByUserPhone(carIndent);
        Random random = new Random();
        int x;
        StringBuilder stringBuilder = new StringBuilder();
        String json;
        if (carIndent == null) {
            json = null;
            carIndent = new CarIndent();
            while (true) {
                for (int i = 0; i < 10; i++) {
                    x = random.nextInt(10);
                    stringBuilder.append(x);
                }
                String code = stringBuilder.toString();
                carIndent.setIndentNo(code);
                carIndent = carIndentService.selectByIndentNo(carIndent);
                if (carIndent == null) {
                    carIndent = new CarIndent();
                    carIndent.setIndentNo(code);
                    break;
                }
            }
            carIndent.setCarId(car.getCarId());
            carIndent.setSellerUserId(car.getUserId());
            carIndent.setBuyerUserId(user.getUserPhone());
            carIndentService.insert(carIndent);
        } else {
            json = "true";
        }
        json = JSON.toJSONString(json);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(json);
        out.flush();
        out.close();
    }


}
