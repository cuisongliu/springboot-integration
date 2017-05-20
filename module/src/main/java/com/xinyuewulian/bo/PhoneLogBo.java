package com.xinyuewulian.bo;

import com.xinyuewulian.entity.PhoneLog;

/**
 * Created by cuisongliu on 2016/11/30.
 *
 * @author cuisongliu
 */
public class PhoneLogBo extends PhoneLog {

    private Integer phoneNum;
    private Integer systemNum;
    private String tips;
    public Integer getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(Integer phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Integer getSystemNum() {
        return systemNum;
    }

    public void setSystemNum(Integer systemNum) {
        this.systemNum = systemNum;
    }

    public String getTips() {
        if(phoneNum > 0 ){
            return "手持机已经被维护,无效数据.";
        }
        if(systemNum != 0 ){
            return "系统对接Key不存在,无效数据.";
        }
        return "有效数据.";
    }
}
