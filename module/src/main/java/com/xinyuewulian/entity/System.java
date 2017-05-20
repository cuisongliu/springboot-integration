package com.xinyuewulian.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "m_system")
public class System {
    @Id
    private Integer id;

    /**
     * 访问应用
     */
    @Column(name = "system_id")
    private String systemId;

    /**
     * 应用编号
     */
    @Column(name = "system_code")
    private String systemCode;

    /**
     * 应用名称
     */
    @Column(name = "system_name")
    private String systemName;

    /**
     * 应用类型  0 为android 1为 IOS
     */
    @Column(name = "system_type")
    private Integer systemType;

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
     * 获取访问应用
     *
     * @return system_id - 访问应用
     */
    public String getSystemId() {
        return systemId;
    }

    /**
     * 设置访问应用
     *
     * @param systemId 访问应用
     */
    public void setSystemId(String systemId) {
        this.systemId = systemId == null ? null : systemId.trim();
    }

    /**
     * 获取应用编号
     *
     * @return system_code - 应用编号
     */
    public String getSystemCode() {
        return systemCode;
    }

    /**
     * 设置应用编号
     *
     * @param systemCode 应用编号
     */
    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode == null ? null : systemCode.trim();
    }

    /**
     * 获取应用名称
     *
     * @return system_name - 应用名称
     */
    public String getSystemName() {
        return systemName;
    }

    /**
     * 设置应用名称
     *
     * @param systemName 应用名称
     */
    public void setSystemName(String systemName) {
        this.systemName = systemName == null ? null : systemName.trim();
    }

    /**
     * 获取应用类型  0 为android 1为 IOS
     *
     * @return system_type - 应用类型  0 为android 1为 IOS
     */
    public Integer getSystemType() {
        return systemType;
    }

    /**
     * 设置应用类型  0 为android 1为 IOS
     *
     * @param systemType 应用类型  0 为android 1为 IOS
     */
    public void setSystemType(Integer systemType) {
        this.systemType = systemType;
    }
}