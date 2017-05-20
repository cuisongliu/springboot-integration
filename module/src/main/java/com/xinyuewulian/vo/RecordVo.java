package com.xinyuewulian.vo;

import java.io.Serializable;

/**
 * Created by cuisongliu on 2016/11/30.
 *
 * @author cuisongliu
 */
public class RecordVo extends QueryVo implements Serializable {

    private Long createUser;
    private Integer recordStore;

    public Integer getRecordStore() {
        return recordStore;
    }

    public void setRecordStore(Integer recordStore) {
        this.recordStore = recordStore;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }
}
