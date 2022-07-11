package com.itheima.domain.dto;

import lombok.Data;

@Data
public class CarIssueDto {
    /**
     * 车辆Id
     */
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
    private Integer issueCheck;
    /**
     * 交易状态
     */
    private Integer carCheck;
}
