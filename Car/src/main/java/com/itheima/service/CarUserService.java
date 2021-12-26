package com.itheima.service;

import com.itheima.domain.Entity.CarUser;

import java.util.List;

/**
 * @author star
 * @date Created on 2021/12/4 18:08
 * @description
 */
public interface CarUserService {
    CarUser findByUser(CarUser carUser);
    List<CarUser> selectByAll();
}
