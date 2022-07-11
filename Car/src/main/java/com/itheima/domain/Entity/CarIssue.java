package com.itheima.domain.Entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.itheima.domain.util.BasePojo;
import lombok.Data;


@TableName(value = "CAR_ISSUE")
@Data
public class CarIssue extends BasePojo {
    /**
     * 发布表的主键id
     */
    @TableId(value = "issue_id", type = IdType.AUTO)
    private Long issueId;

    /**
     * 用户表的主键id
     */
    private Long userId;

    /**
     * 汽车表的主键id
     */
    private Long carId;

    /**
     * 1：待审核 0：审核通过 -1审核未通过
     */
    private Integer issueCheck;
    /**
     * 交易状态
     */
    private Integer carCheck;
}
