package com.itheima.domain.Entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.itheima.domain.util.BasePojo;
import lombok.Data;


@TableName(value = "CAR_INDENT")
@Data
public class CarIndent extends BasePojo {
    /**
     * 订单表的主键id
     */
    @TableId(value = "indent_id", type = IdType.AUTO)
    private Long indentId;

    /**
     * 订单的编码
     */
    private String indentNo;

    /**
     * 车辆的主键id
     */
    private Long carId;

    /**
     * 买家用户的主键id
     */
    private Long buyerUserId;

    /**
     * 卖家用户的主键id
     */
    private Long sellerUserId;

    /**
     * 1:交易中 0：交易成功 -1：交易失败
     */
    private Integer isDeal;


}
