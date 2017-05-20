package com.xinyuewulian.admin.extra;

import java.io.Serializable;

/**
 * Created by cuisongliu on 2016/11/22.
 *
 * @author cuisongliu
 */
public class LoginInfo implements Serializable {
    private Integer id ;
    private String userName;
    private Integer userType;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "LoginInfo{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userType=" + userType +
                '}';
    }
}
