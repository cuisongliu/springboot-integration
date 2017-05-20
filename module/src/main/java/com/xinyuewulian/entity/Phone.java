package com.xinyuewulian.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "m_phone")
public class Phone {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 手持机序号
     */
    @Column(name = "phone_serial")
    private String phoneSerial;

    /**
     * 手持机名称
     */
    @Column(name = "phone_name")
    private String phoneName;

    /**
     * 用户Id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 手持机型号
     */
    @Column(name = "phone_type")
    private String phoneType;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取手持机序号
     *
     * @return phone_serial - 手持机序号
     */
    public String getPhoneSerial() {
        return phoneSerial;
    }

    /**
     * 设置手持机序号
     *
     * @param phoneSerial 手持机序号
     */
    public void setPhoneSerial(String phoneSerial) {
        this.phoneSerial = phoneSerial == null ? null : phoneSerial.trim();
    }

    /**
     * 获取手持机名称
     *
     * @return phone_name - 手持机名称
     */
    public String getPhoneName() {
        return phoneName;
    }

    /**
     * 设置手持机名称
     *
     * @param phoneName 手持机名称
     */
    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName == null ? null : phoneName.trim();
    }

    /**
     * 获取用户Id
     *
     * @return user_id - 用户Id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户Id
     *
     * @param userId 用户Id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取手持机型号
     *
     * @return phone_type - 手持机型号
     */
    public String getPhoneType() {
        return phoneType;
    }

    /**
     * 设置手持机型号
     *
     * @param phoneType 手持机型号
     */
    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType == null ? null : phoneType.trim();
    }
}