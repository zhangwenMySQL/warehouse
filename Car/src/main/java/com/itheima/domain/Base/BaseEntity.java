package com.itheima.domain.Base;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author star
 * @date Created on 2021/12/4 18:29
 * @description
 */
@Data
public class BaseEntity implements Serializable {
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime insertTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime updateTime;

    private String insertPeople;

    private String updatePeople;

    private Integer isDelete;

}
