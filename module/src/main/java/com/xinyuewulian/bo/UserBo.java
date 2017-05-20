package com.xinyuewulian.bo;

import com.xinyuewulian.entity.User;

/**
 * Created by cuisongliu on 2016/11/30.
 *
 * @author cuisongliu
 */
public class UserBo extends User {

    private Integer phoneNum;
    private Integer storeNum;
    private Integer typeNum;

    private Integer[] phone;
    private Integer[] store;
    private Integer[] type;

    private Integer[] phoneArr;
    private Integer[] storeArr;
    private Integer[] typeArr;

    public Integer[] getPhoneArr() {
        return phoneArr;
    }

    public void setPhoneArr(Integer[] phoneArr) {
        this.phoneArr = phoneArr;
    }

    public Integer[] getStoreArr() {
        return storeArr;
    }

    public void setStoreArr(Integer[] storeArr) {
        this.storeArr = storeArr;
    }

    public Integer[] getTypeArr() {
        return typeArr;
    }

    public void setTypeArr(Integer[] typeArr) {
        this.typeArr = typeArr;
    }

    public Integer getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(Integer phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Integer getStoreNum() {
        return storeNum;
    }

    public void setStoreNum(Integer storeNum) {
        this.storeNum = storeNum;
    }

    public Integer getTypeNum() {
        return typeNum;
    }

    public void setTypeNum(Integer typeNum) {
        this.typeNum = typeNum;
    }

    public Integer[] getPhone() {
        return phone;
    }

    public void setPhone(Integer[] phone) {
        this.phone = phone;
    }

    public Integer[] getStore() {
        return store;
    }

    public void setStore(Integer[] store) {
        this.store = store;
    }

    public Integer[] getType() {
        return type;
    }

    public void setType(Integer[] type) {
        this.type = type;
    }
}
