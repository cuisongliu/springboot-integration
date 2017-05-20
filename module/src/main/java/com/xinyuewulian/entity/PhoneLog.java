package com.xinyuewulian.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "m_phone_log")
public class PhoneLog {
    @Id
    private Integer id;

    @Column(name = "phone_serial_id")
    private String phoneSerialId;

    @Column(name = "phone_system_id")
    private String phoneSystemId;

    @Column(name = "create_date")
    private Date createDate;

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
     * @return phone_serial_id
     */
    public String getPhoneSerialId() {
        return phoneSerialId;
    }

    /**
     * @param phoneSerialId
     */
    public void setPhoneSerialId(String phoneSerialId) {
        this.phoneSerialId = phoneSerialId == null ? null : phoneSerialId.trim();
    }

    /**
     * @return phone_system_id
     */
    public String getPhoneSystemId() {
        return phoneSystemId;
    }

    /**
     * @param phoneSystemId
     */
    public void setPhoneSystemId(String phoneSystemId) {
        this.phoneSystemId = phoneSystemId == null ? null : phoneSystemId.trim();
    }

    /**
     * @return create_date
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}