package com.xinyuewulian.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "m_user_type")
public class UserType {
    @Id
    private Integer id;

    /**
     * 用户主键
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 类型主键
     */
    @Column(name = "type_id")
    private Integer typeId;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户主键
     *
     * @return user_id - 用户主键
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户主键
     *
     * @param userId 用户主键
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取类型主键
     *
     * @return type_id - 类型主键
     */
    public Integer getTypeId() {
        return typeId;
    }

    /**
     * 设置类型主键
     *
     * @param typeId 类型主键
     */
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
}