package com.itheima.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.domain.Entity.Car;
import com.itheima.domain.Entity.CarIssue;

/**
 * @author star
 * @date Created on 2021/12/4 18:16
 * @description
 */
public interface CarIssueMapper extends BaseMapper<CarIssue> {
    void updateByCarCheck(Long carId);

    void updateByIssueCheck(CarIssue carIssue);

    void deleteIssue(CarIssue carIssue);
}
