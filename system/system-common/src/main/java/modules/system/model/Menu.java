package modules.system.model;

import org.yserver.core.model.BaseEntity;

import javax.persistence.*;

/**
 * Description: User.<br>
 * Date: 2016/9/18 14:12<br>
 * Author: ysj
 */
@Entity
@Table(name = "sys_menu")
public class Menu extends BaseEntity
{
    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "URL", nullable = false)
    private String url;

    @Column(name = "SORT", nullable = false)
    private int sort = 0;

    @Column(name = "ICON")
    private String icon;

    @Column(name = "TYPE")
    private String type;

    /**
     * 内联外键
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "PCODE")
    private Menu parent;

    @Transient
    private String pcode;

    public Menu()
    {
        super();
    }

    public Menu(String code)
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

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public Menu getParent()
    {
        return parent;
    }

    public void setParent(Menu parent)
    {
        this.parent = parent;
    }

    public int getSort()
    {
        return sort;
    }

    public void setSort(int sort)
    {
        this.sort = sort;
    }

    public String getIcon()
    {
        return icon;
    }

    public void setIcon(String icon)
    {
        this.icon = icon;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getPcode()
    {
        return pcode;
    }

    public void setPcode(String pcode)
    {
        this.pcode = pcode;
    }
}
