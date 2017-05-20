package com.xinyuewulian.vo;


import com.xinyuewulian.entity.Type;
import com.xinyuewulian.entity.User;

import java.util.List;

public class LoginApiVo extends ApiVo {
    private User user;
    private List<Type> typeList;
    private String qrPrefix;
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Type> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<Type> typeList) {
        this.typeList = typeList;
    }

    public String getQrPrefix() {
        return qrPrefix;
    }

    public void setQrPrefix(String qrPrefix) {
        this.qrPrefix = qrPrefix;
    }
}
