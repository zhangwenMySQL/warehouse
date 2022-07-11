package com.itheima.service;

import com.itheima.domain.Entity.CarIndent;
import com.itheima.domain.Entity.CarUser;

import java.util.List;

/**
 * @author star
 * @date Created on 2021/12/4 18:08
 * @description
 */
public interface CarIndentService {


    List<CarIndent> selectList(CarIndent carIndent, CarUser user);

    CarIndent selectByOne(CarIndent carIndent);

    void updateIndentModify(CarIndent carIndent);

    CarIndent selectByUserPhone(CarIndent carIndent);

    CarIndent selectByIndentNo(CarIndent carIndent);


    void insert(CarIndent carIndent);

    int delete(CarIndent carIndent);
}
