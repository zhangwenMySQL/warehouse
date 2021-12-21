package com.itheima.domain.Entity;

import java.util.Date;

/**
 * @author star
 * @date Created on 2021/12/16 21:34
 * @description
 */
public class User {
    /**
     * 用户列表的id
     */
    private Long userId;

    /**
     * 用户的账号
     */
    private String userUsername;

    /**
     * 用户的密码
     */
    private String userPassword;

    /**
     * 用户的邮箱
     */
    private String userEmail;

    /**
     * 用户的电话号码
     */
    private Long userPhone;

    /**
     * 用户性别
     */
    private Integer userSex;

    /**
     * 用户的出生年月
     */
    private Date userBirthday;

    /**
     * 1:普通用户 2：管理员 3：系统管理员
     */
    private Integer roleId;

    /**
     * 1:已实名认证 0：未实名认证
     */
    private Integer certification;

    /**
     * 创建时间
     */
    private Date insertTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 创建人
     */
    private String insertPeople;

    /**
     * 修改人
     */
    private String updatePeople;

    /**
     * 1:正常 -1已删除
     */
    private Integer isDelete;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(Long userPhone) {
        this.userPhone = userPhone;
    }

    public Integer getUserSex() {
        return userSex;
    }

    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    public Date getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getCertification() {
        return certification;
    }

    public void setCertification(Integer certification) {
        this.certification = certification;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getInsertPeople() {
        return insertPeople;
    }

    public void setInsertPeople(String insertPeople) {
        this.insertPeople = insertPeople;
    }

    public String getUpdatePeople() {
        return updatePeople;
    }

    public void setUpdatePeople(String updatePeople) {
        this.updatePeople = updatePeople;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}