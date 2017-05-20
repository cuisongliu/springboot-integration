package com.xinyuewulian.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "m_type")
public class Type {
    @Id
    private Integer id;

    /**
     * 类型编码
     */
    @Column(name = "type_code")
    private String typeCode;

    /**
     * 类型名称
     */
    @Column(name = "type_name")
    private String typeName;

    /**
     * 排序
     */
    @Column(name = "type_order")
    private Integer typeOrder;

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
     * 获取类型编码
     *
     * @return type_code - 类型编码
     */
    public String getTypeCode() {
        return typeCode;
    }

    /**
     * 设置类型编码
     *
     * @param typeCode 类型编码
     */
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode == null ? null : typeCode.trim();
    }

    /**
     * 获取类型名称
     *
     * @return type_name - 类型名称
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * 设置类型名称
     *
     * @param typeName 类型名称
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    /**
     * 获取排序
     *
     * @return type_order - 排序
     */
    public Integer getTypeOrder() {
        return typeOrder;
    }

    /**
     * 设置排序
     *
     * @param typeOrder 排序
     */
    public void setTypeOrder(Integer typeOrder) {
        this.typeOrder = typeOrder;
    }
}