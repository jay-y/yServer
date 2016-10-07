package org.yserver.core.jpa;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Description: JpaBaseEntity.<br>
 * Date: 2016/9/7 23:43<br>
 * Author: ysj
 */
@MappedSuperclass
public abstract class JpaBaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "CODE")
    protected String code;

    @Lob
    @Column(name = "REMARKS")
    protected String remarks;

    @Column(name = "CREATED_TIME", nullable = false)
    protected Date createdTime;

    @Column(name = "UPDATED_TIME")
    protected Date updatedTime;

    @Column(name = "VERSION")
    @Version
    protected Integer version = 0;//乐观锁

    @Column(name = "IS_DEL", nullable = false)
    protected char isDel = 'n';

    public JpaBaseEntity() {
    }

    public JpaBaseEntity(String code) {
        this.code = code;
    }

    public Integer getVersion() {
        return this.version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public char getIsDel() {
        return isDel;
    }

    public void setIsDel(char isDel) {
        this.isDel = isDel;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}