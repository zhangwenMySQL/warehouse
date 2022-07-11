package com.itheima.service;

import com.itheima.domain.Entity.Car;
import com.itheima.domain.Entity.CarImage;
import com.itheima.domain.Entity.CarUser;
import com.itheima.domain.dto.CarIssueDto;

import java.util.List;

/**
 * @author star
 * @date Created on 2021/12/4 18:08
 * @description
 */
public interface CarImageService {

    void addImage(CarImage carImage);

    CarImage selecyByOne(CarImage carImage);
}
