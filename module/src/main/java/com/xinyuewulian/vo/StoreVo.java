package com.xinyuewulian.vo;

import java.io.Serializable;

/**
 * Created by cuisongliu on 2016/11/30.
 *
 * @author cuisongliu
 */
public class StoreVo extends QueryVo implements Serializable {

    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
