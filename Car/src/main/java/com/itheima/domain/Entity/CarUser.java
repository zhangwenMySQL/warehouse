package com.itheima.domain.Entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.itheima.domain.util.BasePojo;
import lombok.Data;


@TableName(value = "CAR_USER")
@Data
public class CarUser extends BasePojo {
    /**
     * 用户列表的id
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 用户的姓名
     */
    private String userName;

    /**
     * 用户的密码
     */
    private String userPassword;

    /**
     * 用户的电话号码
     */
    private Long userPhone;

    /**
     * 用户的邮箱
     */
    private String userEmail;

    /**
     * 用户的地址
     */
    private String userAddress;

    /**
     * 0:普通用户 1：系统管理员
     */
    private Integer roleId;

    /**
     * 用户的审核状态：1：待审核 0 ：通过 -1：不通过
     */
    private Integer userAuditStatus;


}