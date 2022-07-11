package com.itheima.service;

import com.itheima.domain.Entity.Car;
import com.itheima.domain.Entity.CarUser;
import com.itheima.domain.dto.CarIssueDto;

import java.util.List;

/**
 * @author star
 * @date Created on 2021/12/4 18:08
 * @description
 */
public interface CarService {
    List<Car> selectList(Car car,CarUser user);

    Car selectByOne(Car car);

    void updateCheck(Car car);


    void deleteCar(Car car);

    Car insert(Car car);

    List<CarIssueDto> selectByList(Car car, CarUser user);

    void deleteCarByIssue(Car car, CarUser user);
}
