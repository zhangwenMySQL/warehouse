package com.itheima.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itheima.dao.CarImageMapper;
import com.itheima.dao.CarMapper;
import com.itheima.dao.CarUserMapper;
import com.itheima.domain.Entity.Car;
import com.itheima.domain.Entity.CarImage;
import com.itheima.domain.Entity.CarUser;
import com.itheima.domain.dto.CarIssueDto;
import com.itheima.service.CarImageService;
import com.itheima.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author star
 * @date Created on 2021/12/4 18:08
 * @description
 */
@Service("carImageService")
public class CarImageServiceImpl implements CarImageService {
    @Autowired
    private CarImageMapper carImageMapper;

    @Override
    public void addImage(CarImage carImage) {
        carImageMapper.insert(carImage);
    }

    @Override
    public CarImage selecyByOne(CarImage carImage) {
        QueryWrapper<CarImage> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("car_id",carImage.getCarId());
        return carImageMapper.selectOne(queryWrapper);
    }
}
