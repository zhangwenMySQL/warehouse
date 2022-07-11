package com.itheima.service;

import com.itheima.domain.Entity.Car;
import com.itheima.domain.Entity.CarIssue;

import java.util.List;

/**
 * @author star
 * @date Created on 2021/12/4 18:08
 * @description
 */
public interface CarIssueService {

    void insert(CarIssue carIssue);

    void updateByCarCheck(Long carId);

    void updateByIssueCheck(CarIssue carIssue);

    void deleteIssue(CarIssue carIssue);

}
