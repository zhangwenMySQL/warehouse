package com.itheima.domain.dto;

import lombok.Data;

/**
 * @author star
 * @date Created on 2021/12/4 18:34
 * @description
 */
@Data
public class CarUserDto {
    private Long carUserId;

    private String username;

    private String password;
}
