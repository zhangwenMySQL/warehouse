package com.itheima.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.domain.Entity.Car;
import com.itheima.domain.Entity.CarUser;
import com.itheima.domain.dto.CarIssueDto;

import java.util.List;

/**
 * @author star
 * @date Created on 2021/12/4 18:16
 * @description
 */
public interface CarMapper extends BaseMapper<Car> {
    List<CarIssueDto> selectByList(CarIssueDto carIssueDto);
}
