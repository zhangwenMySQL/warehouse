package com.itheima.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itheima.dao.CarUserMapper;
import com.itheima.domain.Entity.Car;
import com.itheima.domain.Entity.CarUser;
import com.itheima.service.CarUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author star
 * @date Created on 2021/12/4 18:08
 * @description
 */
@Service("carUserService")
public class CarUserServiceImpl implements CarUserService {
    @Autowired
    private CarUserMapper carUserMapper;
    @Override
    public CarUser findByUser(CarUser carUser) {
        QueryWrapper<CarUser> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_phone",carUser.getUserPhone());
        queryWrapper.eq("user_password",carUser.getUserPassword());
        queryWrapper.eq("user_audit_status",0);
        queryWrapper.eq("is_delete",1);
        CarUser carUser1 = carUserMapper.selectOne(queryWrapper);
        if (carUser1!=null){
            return carUser1;
        }
        return null;
    }

    @Override
    public List<CarUser> selectList(CarUser carUser) {
        QueryWrapper<CarUser> queryWrapper=new QueryWrapper<>();
        if (carUser.getUserPhone()!=null){
            queryWrapper.eq("user_phone",carUser.getUserPhone());
        }
        if (carUser.getUserAuditStatus()!=null){
            queryWrapper.eq("user_audit_status",carUser.getUserAuditStatus());
        }
        queryWrapper.eq("is_delete",1);
        queryWrapper.eq("role_id",0);
        queryWrapper.orderByDesc("user_audit_status");
        List<CarUser> userList = carUserMapper.selectList(queryWrapper);
        return userList;
    }

    @Override
    public CarUser selectByOne(CarUser carUser) {
        QueryWrapper<CarUser> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",carUser.getUserId());
        carUser=carUserMapper.selectOne(queryWrapper);
        return carUser;
    }

    @Override
    public void updateCheck(CarUser carUser) {
        carUserMapper.updateById(carUser);
    }

    @Override
    public void deleteUser(CarUser carUser) {
        carUser.setIsDelete(-1);
        carUserMapper.updateById(carUser);
    }

    @Override
    public int updatePassword(CarUser user) {
        int i = carUserMapper.updateById(user);
        return i;
    }

    @Override
    public void insert(CarUser carUser) {
        carUserMapper.insert(carUser);
    }

    @Override
    public CarUser selectByUserPhone(CarUser carUser) {
        QueryWrapper<CarUser> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("user_phone",carUser.getUserPhone());
        queryWrapper.eq("is_delete",1);
        return carUserMapper.selectOne(queryWrapper);
    }


}
