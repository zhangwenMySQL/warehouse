package com.itheima.domain.dto;


import com.itheima.domain.util.BasePojo;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
public class CarDto extends BasePojo {
    /**
     * 汽车表的主键id
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
    private Integer carCheck;

    private String image;

    private MultipartFile file;
}
