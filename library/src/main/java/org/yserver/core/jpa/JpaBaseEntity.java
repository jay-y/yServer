package org.yserver.core.jpa;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;

/**
 * Description: Abstract Entity.<br>
 * Date: 2016/9/7 23:43<br>
 * Author: ysj
 */
@MappedSuperclass
public abstract class JpaBaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Version
    private Integer version = 0;//乐观锁

    public JpaBaseEntity() {
    }

    public Integer getVersion() {
        return this.version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

}