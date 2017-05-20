package com.xinyuewulian.bo;

import com.xinyuewulian.entity.Record;

/**
 * Created by cuisongliu on 2016/11/30.
 *
 * @author cuisongliu
 */
public class RecordBo extends Record {

    private String createUser;
    private String modifyUser;
    private String storeName;
    private String typeName;

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
