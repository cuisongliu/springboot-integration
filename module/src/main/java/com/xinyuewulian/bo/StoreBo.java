package com.xinyuewulian.bo;

import com.xinyuewulian.entity.Store;

/**
 * Created by cuisongliu on 2016/11/30.
 *
 * @author cuisongliu
 */
public class StoreBo extends Store {

    private String userName;
    private Integer recordNum;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getRecordNum() {
        return recordNum;
    }

    public void setRecordNum(Integer recordNum) {
        this.recordNum = recordNum;
    }
}
