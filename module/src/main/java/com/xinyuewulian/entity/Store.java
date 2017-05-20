package com.xinyuewulian.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "m_store")
public class Store {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 编码
     */
    @Column(name = "store_code")
    private String storeCode;

    /**
     * 货物名称
     */
    @Column(name = "store_name")
    private String storeName;

    /**
     * 货物地址
     */
    @Column(name = "store_address")
    private String storeAddress;

    /**
     * 用户Id
     */
    @Column(name = "user_id")
    private Integer userId;

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
     * 获取编码
     *
     * @return store_code - 编码
     */
    public String getStoreCode() {
        return storeCode;
    }

    /**
     * 设置编码
     *
     * @param storeCode 编码
     */
    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode == null ? null : storeCode.trim();
    }

    /**
     * 获取货物名称
     *
     * @return store_name - 货物名称
     */
    public String getStoreName() {
        return storeName;
    }

    /**
     * 设置货物名称
     *
     * @param storeName 货物名称
     */
    public void setStoreName(String storeName) {
        this.storeName = storeName == null ? null : storeName.trim();
    }

    /**
     * 获取货物地址
     *
     * @return store_address - 货物地址
     */
    public String getStoreAddress() {
        return storeAddress;
    }

    /**
     * 设置货物地址
     *
     * @param storeAddress 货物地址
     */
    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress == null ? null : storeAddress.trim();
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
}