package com.itheima.service.impl;

import com.itheima.dao.CarIssueMapper;
import com.itheima.domain.Entity.CarIssue;
import com.itheima.service.CarIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author star
 * @date Created on 2021/12/4 18:08
 * @description
 */
@Service("carIssueService")
public class CarIssueServiceImpl implements CarIssueService {
    @Autowired
    private CarIssueMapper carIssueMapper;
    @Override
    public void insert(CarIssue carIssue) {
        carIssueMapper.insert(carIssue);
    }

    @Override
    public void updateByCarCheck(Long carId) {
        carIssueMapper.updateByCarCheck(carId);
    }

    @Override
    public void updateByIssueCheck(CarIssue carIssue) {
        carIssueMapper.updateByIssueCheck(carIssue);
    }

    @Override
    public void deleteIssue(CarIssue carIssue) {
        carIssueMapper.deleteIssue(carIssue);
    }
}
