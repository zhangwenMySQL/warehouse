package com.itheima.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itheima.dao.CarUserMapper;
import com.itheima.domain.Entity.CarUser;
import com.itheima.service.CarUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author star
 * @date Created on 2021/12/4 18:08
 * @description
 */
@Service("CarUserService")
public class CarUserServiceImpl implements CarUserService {
    @Autowired
    CarUserMapper carUserMapper;
    @Override
    public CarUser findByUser(CarUser carUser) {
        List<CarUser> carUsers = carUserMapper.selectByAll();
        LocalDateTime insertTime = carUsers.get(0).getInsertTime();

        System.out.println(insertTime);
        System.out.println(carUsers);
        QueryWrapper<CarUser> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",carUser.getUsername()).eq("password",carUser.getPassword());
        CarUser carUser1 = carUserMapper.selectOne(queryWrapper);
        return carUser1;
    }

    @Override
    public List<CarUser> selectByAll() {
        return carUserMapper.selectByAll();
    }
}
