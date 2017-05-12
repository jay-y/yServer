package modules.system.model;

import org.yserver.core.model.BaseEntity;

import javax.persistence.*;
import java.util.List;

/**
 * Description: Area.<br>
 * Date: 2016/9/23 15:09<br>
 * Author: ysj
 */
@Entity
@Table(name = "sys_area")
public class Area extends BaseEntity
{
    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "NUMBER", nullable = false)
    private String number;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "PCODE")
    private Area parent;

    @Column(name = "PCODES")
    private String pcodes;

    @Transient
    private List<Area> children;

    public Area()
    {
        super();
    }

    public Area(String code)
    {
        super(code);
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public Area getParent()
    {
        return parent;
    }

    public void setParent(Area parent)
    {
        this.parent = parent;
    }

    public String getCreatedBy()
    {
        return createdBy;
    }

    public void setCreatedBy(String createdBy)
    {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy()
    {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy)
    {
        this.updatedBy = updatedBy;
    }

    public String getPcodes()
    {
        return pcodes;
    }

    public void setPcodes(String pcodes)
    {
        this.pcodes = pcodes;
    }

    public List<Area> getChildren()
    {
        return children;
    }

    public void setChildren(List<Area> children)
    {
        this.children = children;
    }
}
