package com.itheima.domain.Entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.itheima.domain.util.BasePojo;
import lombok.Data;


@TableName(value = "CAR")
@Data
public class Car extends BasePojo {
    /**
     * 汽车表的主键id
     */
    @TableId(value = "car_id", type = IdType.AUTO)
    private Long carId;

    /**
     * 用户的id
     */
    private Long userId;

    /**
     * 车的品牌名称
     */
    private String carBrand;

    /**
     * 车的系列
     */
    private String carSeries;

    /**
     * 车的年限
     */
    private String carAgeLimit;

    /**
     * 汽车的价格 单位：万
     */
    private String carPrice;

    /**
     * 1：待审核 0：审核通过 -1审核未通过
     */
    private Integer carCheck;


}
