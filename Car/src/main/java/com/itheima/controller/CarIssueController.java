package com.itheima.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.domain.Entity.Car;
import com.itheima.domain.Entity.CarImage;
import com.itheima.domain.Entity.CarIssue;
import com.itheima.domain.Entity.CarUser;
import com.itheima.domain.dto.CarDto;
import com.itheima.domain.dto.CarIssueDto;
import com.itheima.service.CarImageService;
import com.itheima.service.CarIssueService;
import com.itheima.service.CarService;
import com.itheima.service.CarUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

/**
 * @author star
 * @date Created on 2021/12/4 16:33
 * @description
 */
@RequestMapping("/carIssue")
@Controller
public class CarIssueController {
    @Autowired
    CarUserService carUserService;
    @Autowired
    CarService carService;
    @Autowired
    private CarIssueService carIssueService;
    @Autowired
    private CarImageService carImageService;

    /**
     * 查询用户发布车辆相关信息
     *
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
        String issueCheck = request.getParameter("issueCheck");
        Car car = new Car();
        int num = 1;
        int size = 5;
        if (pageNum != null) {
            num = Integer.parseInt(pageNum);
        }
        if (pageSize != null) {
            size = Integer.parseInt(pageSize);
        }
        if (carBrand != null && !(carBrand.equalsIgnoreCase(""))) {
            car.setCarBrand(carBrand);
        } else {
            carBrand = null;
            car.setCarBrand(null);
        }
        if (issueCheck != null && !(issueCheck.equalsIgnoreCase(""))) {
            car.setCarCheck(Integer.valueOf(issueCheck));
        } else {
            issueCheck = null;
            car.setCarCheck(null);
        }
        PageHelper.startPage(num, size);
        List<CarIssueDto> carList = carService.selectByList(car, user);
        PageInfo<CarIssueDto> pageInfo = new PageInfo<>(carList);
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
        String carCheck = request.getParameter("carCheck");
        Car car = new Car();
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
        String id = request.getParameter("id");
        Car car = new Car();
        car.setCarId(Long.valueOf(id));
        car = carService.selectByOne(car);
        session.setAttribute("carCheck", car);
        String json = JSON.toJSONString(car);
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
        String id = request.getParameter("id");
        Car car = new Car();
        car.setCarId(Long.valueOf(id));
        car = carService.selectByOne(car);
        carService.deleteCar(car);
        String json = JSON.toJSONString(car);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(json);
        out.flush();
        out.close();
    }

    /**
     * 用户发布车辆
     *
     * @param carDto
     * @param session
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("userIssueAdd")
    public String userIssueAdd(CarDto carDto, HttpSession session, HttpServletRequest request) throws Exception {
        try {
            //保存数据库的路径
            String sqlPath = null;
            //定义文件保存的本地路径
            String loaclPath1 = "D:\\idea\\java\\car\\warehouse\\Car\\src\\main\\webapp\\images\\";
            String localPath = request.getSession().getServletContext().getRealPath("/images");
            //定义 文件名
            String filename = null;
            if (!carDto.getFile().isEmpty()) {
                //生成uuid作为文件名称
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                //获得文件类型（可以判断如果不是图片，禁止上传）
                String contentType = carDto.getFile().getContentType();
                //获得文件后缀名
                String suffixName = contentType.substring(contentType.indexOf("/") + 1);
                //得到 文件名
                filename = uuid + "." + suffixName;
                System.out.println(filename);
                //文件保存路径
                String s = loaclPath1 + filename;
                System.out.println(s);
                String s1 = localPath + "\\" + filename;
                System.out.println(s1);
                carDto.getFile().transferTo(new File(loaclPath1 + filename));
                carDto.getFile().transferTo(new File(localPath + "\\" + filename));
            }
            CarImage carImage = new CarImage();
            Car car = new Car();
            BeanUtils.copyProperties(carDto, car);
            CarIssue carIssue = new CarIssue();
            CarUser user = (CarUser) session.getAttribute("user");
            car.setUserId(user.getUserPhone());
            car.setUpdatePeople(user.getUserName());
            car.setInsertPeople(user.getUserName());
            car = carService.insert(car);
            carImage.setCarId(car.getCarId());
            //把图片的相对路径保存至数据库
            sqlPath = "../images" + "/" + filename;
            carImage.setImageAddress(sqlPath);
            carImageService.addImage(carImage);
            carIssue.setCarId(car.getCarId());
            carIssue.setUserId(user.getUserPhone());
            carIssue.setInsertPeople(user.getUserName());
            carIssue.setUpdatePeople(user.getUserName());
            carIssueService.insert(carIssue);
            return "redirect:/jsp/userIssue.jsp";
        } catch (Exception e) {
            return "redirect:/jsp/userIssueAdd.jsp";
        }

    }

    @RequestMapping(value = "seePhoto", method = RequestMethod.POST)
    public void seePhoto(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String image = (String) session.getAttribute("image");
        String json = JSON.toJSONString(image);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(json);
        out.flush();
        out.close();
    }

    @RequestMapping(value = "deleteIssue")
    public void deleteIssue(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        Car car = new Car();
        CarIssue carIssue = new CarIssue();
        car.setCarId(Long.valueOf(id));
        carIssue.setCarId(Long.valueOf(id));
        car = carService.selectByOne(car);
        carService.deleteCar(car);
        carIssueService.deleteIssue(carIssue);
        String json = JSON.toJSONString(carIssue);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(json);
        out.flush();
        out.close();
    }
}
