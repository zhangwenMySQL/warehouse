package com.itheima.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itheima.dao.CarIndentMapper;
import com.itheima.domain.Entity.Car;
import com.itheima.domain.Entity.CarIndent;
import com.itheima.domain.Entity.CarUser;
import com.itheima.service.CarIndentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author star
 * @date Created on 2021/12/4 18:08
 * @description
 */
@Service("carIndentService")
public class CarIndentServiceImpl implements CarIndentService {
    @Autowired
    private CarIndentMapper carIndentMapper;


    @Override
    public List<CarIndent> selectList(CarIndent carIndent, CarUser user) {
        QueryWrapper<CarIndent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_delete", 1);
        queryWrapper.orderByDesc("is_deal");
        if (carIndent.getIndentNo() != null) {
            queryWrapper.likeRight("indent_no", carIndent.getIndentNo());
        }
        if (user.getRoleId() == 0) {
            queryWrapper.eq("buyer_user_id", user.getUserPhone()).or().eq("seller_user_id", user.getUserPhone());
        }
        queryWrapper.eq("is_delete", 1);
        if (carIndent.getIndentNo() != null) {
            queryWrapper.likeRight("indent_no", carIndent.getIndentNo());
        }
        queryWrapper.orderByDesc("is_deal");
        List<CarIndent> carIndentList = carIndentMapper.selectList(queryWrapper);
        return carIndentList;
    }

    @Override
    public CarIndent selectByOne(CarIndent carIndent) {
        QueryWrapper<CarIndent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("indent_id", carIndent.getIndentId());
        CarIndent carIndent1 = carIndentMapper.selectOne(queryWrapper);
        return carIndent1;
    }


    @Override
    public CarIndent selectByIndentNo(CarIndent carIndent) {
        QueryWrapper<CarIndent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("indent_no", carIndent.getIndentNo());
        carIndent = carIndentMapper.selectOne(queryWrapper);
        return carIndent;
    }

    @Override
    public void insert(CarIndent carIndent) {
        carIndentMapper.insert(carIndent);
    }

    @Override
    public int delete(CarIndent carIndent) {
        carIndent.setIsDelete(-1);
        return carIndentMapper.updateById(carIndent);
    }

    @Override
    public void updateIndentModify(CarIndent carIndent) {
        carIndentMapper.updateById(carIndent);
    }

    @Override
    public CarIndent selectByUserPhone(CarIndent carIndent) {
        QueryWrapper<CarIndent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("car_id", carIndent.getCarId());
        queryWrapper.eq("buyer_user_id", carIndent.getBuyerUserId());
        queryWrapper.eq("is_delete", 1);
        return carIndentMapper.selectOne(queryWrapper);
    }
}
