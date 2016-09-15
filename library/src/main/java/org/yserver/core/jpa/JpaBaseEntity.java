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
    private String code;

    @Column(name = "IS_DEL", nullable = false)
    private char isDel;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_TIME", nullable = false)
    private Date createdTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED_TIME")
    private Date updatedTime;

    @Column(name = "VERSION")
    @Version
    private Integer version = 0;//乐观锁

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