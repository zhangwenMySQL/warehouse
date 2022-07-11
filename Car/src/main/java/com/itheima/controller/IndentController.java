package com.itheima.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.domain.Entity.Car;
import com.itheima.domain.Entity.CarIndent;
import com.itheima.domain.Entity.CarUser;
import com.itheima.service.CarIndentService;
import com.itheima.service.CarIssueService;
import com.itheima.service.CarService;
import com.itheima.service.CarUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author star
 * @date Created on 2021/12/4 16:33
 * @description
 */
@RequestMapping("/indent")
@Controller
public class IndentController {
    @Autowired
    CarUserService carUserService;
    @Autowired
    CarService carService;
    @Autowired
    CarIndentService carIndentService;
    @Autowired
    CarIssueService carIssueService;

    @RequestMapping("/findByPage")
    public void findByPage(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        CarUser user = (CarUser) session.getAttribute("user");
        String pageNum = request.getParameter("pageNum");
        String pageSize = request.getParameter("pageSize");
        String indentNo = request.getParameter("indentNo");
        CarIndent carIndent = new CarIndent();
        int num = 1;
        int size = 5;
        if (pageNum != null) {
            num = Integer.parseInt(pageNum);
        }
        if (pageSize != null) {
            size = Integer.parseInt(pageSize);
        }
        if (indentNo != null && !(indentNo.equalsIgnoreCase(""))) {
            carIndent.setIndentNo(indentNo);
        } else {
            indentNo = null;
            carIndent.setIndentNo(null);
        }
        PageHelper.startPage(num, size);
        List<CarIndent> carIndentList = carIndentService.selectList(carIndent, user);
        PageInfo<CarIndent> pageInfo = new PageInfo<>(carIndentList);
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
        String indentNo = request.getParameter("indentNo");
        CarIndent carIndent = new CarIndent();
        if (indentNo != null && !(indentNo.equalsIgnoreCase(""))) {
            carIndent.setIndentNo(indentNo);
        } else {
            indentNo = null;
            carIndent.setIndentNo(null);
        }
        String json = JSON.toJSONString(carIndent);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(json);
        out.flush();
        out.close();
    }

    @RequestMapping("/updateDeal")
    public void updateDeal(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        String json;
        CarUser user = (CarUser) session.getAttribute("user");
        String id = request.getParameter("id");
        CarIndent carIndent = new CarIndent();
        carIndent.setIndentId(Long.valueOf(id));
        carIndent = carIndentService.selectByOne(carIndent);
        if (user.getUserPhone().equals(carIndent.getSellerUserId())) {
            session.setAttribute("selectByIndentOne", carIndent);
            json = JSON.toJSONString(carIndent);
        } else {
            json = JSON.toJSONString(null);
        }
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(json);
        out.flush();
        out.close();
    }

    @RequestMapping("/updateIndentModify")
    public void updateIndentModify(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        String isDeal = request.getParameter("isDeal");
        CarIndent carIndent = (CarIndent) session.getAttribute("selectByIndentOne");
        String json = null;
        try {
            if (isDeal != null) {
                carIndent.setIsDeal(Integer.valueOf(isDeal));
                carIndentService.updateIndentModify(carIndent);
                if (Integer.valueOf(isDeal) == 0) {
                    Car car = new Car();
                    car.setCarId(carIndent.getCarId());
                    car = carService.selectByOne(car);
                    carService.deleteCar(car);
                    carIssueService.updateByCarCheck(carIndent.getCarId());
                }
            }
            json = JSON.toJSONString(carIndent);
        } catch (Exception e) {
            e.printStackTrace();
            json = JSON.toJSONString(null);
        }
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(json);
        out.flush();
        out.close();
    }

    @RequestMapping("/readCar")
    public void readCar(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        String id = request.getParameter("id");
        Car car=new Car();
        car.setCarId(Long.valueOf(id));
        car = carService.selectByOne(car);
        session.setAttribute("readCar",car);
        String json=JSON.toJSONString(car);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(json);
        out.flush();
        out.close();
    }

    //
//    @RequestMapping("/updateIndentModify")
//    public void updateIndentModify(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
//        Car car = (Car) session.getAttribute("carCheck");
//        String carBrand = request.getParameter("carBrand");
//        String carSeries = request.getParameter("carSeries");
//        String carAgeLimit = request.getParameter("carAgeLimit");
//        String carPrice = request.getParameter("carPrice");
//        String json=null;
//        if (carBrand != null) {
//            car.setCarBrand(carBrand);
//        }
//        if (carSeries != null) {
//            car.setCarSeries(carSeries);
//        }
//        if (carAgeLimit != null) {
//            car.setCarAgeLimit(carAgeLimit);
//        }
//        if (carPrice!=null){
//            try{
//                double a= Double.parseDouble(carPrice);
//                car.setCarPrice(carPrice);
//                json = JSON.toJSONString(car);
//                carService.updateCheck(car);
//            }catch (Exception e){
//                e.printStackTrace();
//                json=JSON.toJSONString(null);
//            }
//        }else{
//            json=JSON.toJSONString(null);
//        }
//        response.setContentType("application/json;charset=utf-8");
//        PrintWriter out = response.getWriter();
//        out.write(json);
//        out.flush();
//        out.close();
//    }

    @RequestMapping("/deleteIndent")
    public void deleteIndent(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        String id = request.getParameter("id");
        CarIndent carIndent = new CarIndent();
        carIndent.setIndentId(Long.valueOf(id));
        carIndent = carIndentService.selectByOne(carIndent);
        int i = carIndentService.delete(carIndent);
        String json = JSON.toJSONString(i);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(json);
        out.flush();
        out.close();
    }


}
