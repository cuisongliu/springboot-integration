package com.xinyuewulian.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "m_param")
public class Param {
    @Id
    private Integer id;

    /**
     * 参数编码
     */
    @Column(name = "param_code")
    private String paramCode;

    /**
     * 参数名称
     */
    @Column(name = "param_name")
    private String paramName;

    /**
     * 参数值
     */
    @Column(name = "param_value")
    private String paramValue;

    /**
     * 类型 0 为可编辑  1为不可编辑
     */
    @Column(name = "param_type")
    private Integer paramType;

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
     * 获取参数编码
     *
     * @return param_code - 参数编码
     */
    public String getParamCode() {
        return paramCode;
    }

    /**
     * 设置参数编码
     *
     * @param paramCode 参数编码
     */
    public void setParamCode(String paramCode) {
        this.paramCode = paramCode == null ? null : paramCode.trim();
    }

    /**
     * 获取参数名称
     *
     * @return param_name - 参数名称
     */
    public String getParamName() {
        return paramName;
    }

    /**
     * 设置参数名称
     *
     * @param paramName 参数名称
     */
    public void setParamName(String paramName) {
        this.paramName = paramName == null ? null : paramName.trim();
    }

    /**
     * 获取参数值
     *
     * @return param_value - 参数值
     */
    public String getParamValue() {
        return paramValue;
    }

    /**
     * 设置参数值
     *
     * @param paramValue 参数值
     */
    public void setParamValue(String paramValue) {
        this.paramValue = paramValue == null ? null : paramValue.trim();
    }

    /**
     * 获取类型 0 为可编辑  1为不可编辑
     *
     * @return param_type - 类型 0 为可编辑  1为不可编辑
     */
    public Integer getParamType() {
        return paramType;
    }

    /**
     * 设置类型 0 为可编辑  1为不可编辑
     *
     * @param paramType 类型 0 为可编辑  1为不可编辑
     */
    public void setParamType(Integer paramType) {
        this.paramType = paramType;
    }
}