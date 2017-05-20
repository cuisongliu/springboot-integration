package com.xinyuewulian.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "m_record")
public class Record {
    @Id
    private Integer id;

    /**
     * 记录地址
     */
    @Column(name = "record_address")
    private String recordAddress;

    /**
     * 记录货物Id
     */
    @Column(name = "record_store")
    private Integer recordStore;

    /**
     * 记录时间
     */
    @Column(name = "record_date")
    private Date recordDate;

    /**
     * 操作人
     */
    @Column(name = "record_operator")
    private Integer recordOperator;

    /**
     * 操作类型
     */
    @Column(name = "record_type")
    private Integer recordType;

    @Column(name = "record_company")
    private String recordCompany;

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
     * 获取记录地址
     *
     * @return record_address - 记录地址
     */
    public String getRecordAddress() {
        return recordAddress;
    }

    /**
     * 设置记录地址
     *
     * @param recordAddress 记录地址
     */
    public void setRecordAddress(String recordAddress) {
        this.recordAddress = recordAddress == null ? null : recordAddress.trim();
    }

    /**
     * 获取记录货物Id
     *
     * @return record_store - 记录货物Id
     */
    public Integer getRecordStore() {
        return recordStore;
    }

    /**
     * 设置记录货物Id
     *
     * @param recordStore 记录货物Id
     */
    public void setRecordStore(Integer recordStore) {
        this.recordStore = recordStore;
    }

    /**
     * 获取记录时间
     *
     * @return record_date - 记录时间
     */
    public Date getRecordDate() {
        return recordDate;
    }

    /**
     * 设置记录时间
     *
     * @param recordDate 记录时间
     */
    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    /**
     * 获取操作人
     *
     * @return record_operator - 操作人
     */
    public Integer getRecordOperator() {
        return recordOperator;
    }

    /**
     * 设置操作人
     *
     * @param recordOperator 操作人
     */
    public void setRecordOperator(Integer recordOperator) {
        this.recordOperator = recordOperator;
    }

    /**
     * 获取操作类型
     *
     * @return record_type - 操作类型
     */
    public Integer getRecordType() {
        return recordType;
    }

    /**
     * 设置操作类型
     *
     * @param recordType 操作类型
     */
    public void setRecordType(Integer recordType) {
        this.recordType = recordType;
    }

    /**
     * @return record_company
     */
    public String getRecordCompany() {
        return recordCompany;
    }

    /**
     * @param recordCompany
     */
    public void setRecordCompany(String recordCompany) {
        this.recordCompany = recordCompany == null ? null : recordCompany.trim();
    }
}