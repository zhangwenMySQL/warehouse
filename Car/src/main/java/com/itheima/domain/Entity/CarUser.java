package com.itheima.domain.Entity;

import com.itheima.domain.Base.BaseEntity;
import lombok.Data;

/**
 * @author star
 * @date Created on 2021/12/4 18:04
 * @description
 */
@Data
public class CarUser extends BaseEntity {

    private Long carUserId;

    private String username;

    private String password;

}
