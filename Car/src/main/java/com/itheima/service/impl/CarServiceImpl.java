package com.itheima.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itheima.dao.CarMapper;
import com.itheima.dao.CarUserMapper;
import com.itheima.domain.Entity.Car;
import com.itheima.domain.Entity.CarIssue;
import com.itheima.domain.Entity.CarUser;
import com.itheima.domain.dto.CarIssueDto;
import com.itheima.service.CarService;
import com.itheima.service.CarUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author star
 * @date Created on 2021/12/4 18:08
 * @description
 */
@Service("carService")
public class CarServiceImpl implements CarService {
    @Autowired
    private CarUserMapper carUserMapper;
    @Autowired
    private CarMapper carMapper;

    @Override
    public List<Car> selectList(Car car,CarUser user) {
        QueryWrapper<Car> queryWrapper = new QueryWrapper<>();
        if (car.getCarBrand() != null) {
            queryWrapper.like("car_brand", car.getCarBrand());
        }
        if (car.getCarPrice() != null) {
            queryWrapper.likeRight("car_price", car.getCarPrice());
        }
        if (car.getCarCheck() != null) {
            queryWrapper.eq("car_check", car.getCarCheck());
        }
        if (user.getRoleId()==0){
            queryWrapper.eq("car_check",0);
//            queryWrapper.notIn("user_id",user.getUserPhone());
        }
        queryWrapper.eq("is_delete", 1);
        queryWrapper.orderByDesc("car_check");
        List<Car> carList = carMapper.selectList(queryWrapper);
        return carList;
    }

    @Override
    public Car selectByOne(Car car) {
        QueryWrapper<Car> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("car_id",car.getCarId());
        car=carMapper.selectOne(queryWrapper);
        return car;
    }

    @Override
    public void updateCheck(Car car) {
        carMapper.updateById(car);
    }

    @Override
    public void deleteCar(Car car) {
        car.setIsDelete(-1);
        carMapper.updateById(car);
    }

    @Override
    public Car insert(Car car) {
        carMapper.insert(car);
        return car;
    }

    @Override
    public List<CarIssueDto> selectByList(Car car, CarUser user) {
        CarIssueDto carIssueDto=new CarIssueDto();
        carIssueDto.setCarBrand(car.getCarBrand());
        carIssueDto.setCarCheck(car.getCarCheck());
        carIssueDto.setUserId(user.getUserPhone());
        return carMapper.selectByList(carIssueDto);
    }

    @Override
    public void deleteCarByIssue(Car car, CarUser user) {
        car.setIsDelete(-1);
        car.setCarCheck(-1);
        car.setUpdatePeople(user.getUserName());
        carMapper.updateById(car);
    }

//
//    @Override
//    public void deleteUser(CarUser carUser) {
//        carUser.setIsDelete(-1);
//        carUserMapper.updateById(carUser);
//    }


}
