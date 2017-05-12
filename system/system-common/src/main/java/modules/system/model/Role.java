package modules.system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.yserver.core.model.BaseEntity;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Description: Role.<br>
 * Date: 2016/9/18 14:12<br>
 * Author: ysj
 */
@Entity
@Table(name = "sys_role")
public class Role extends BaseEntity
{

    @Column(name = "NAME", nullable = false)
    private String name;    //角色名称

    @Column(name = "ENNAME")
    private String enname;    //英文名称

    @Column(name = "ROLE_TYPE")
    private String roeType;    //角色类型

//    @Column(name = "DATA_SCOPE")
//    private char dataScope = '0';   //数据范围
//
//    @Column(name = "IS_SYS")
//    private char isSys = 'n';    //是否系统数据

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "sys_role_menu_relational", joinColumns = {@JoinColumn(name = "ROLE_CODE")}, inverseJoinColumns = {@JoinColumn(name = "MENU_CODE")})
    @JsonIgnore
    private Set<Menu> menus = new LinkedHashSet<>();

    @Column(name = "IS_AVAILABLE", nullable = false)
    private char isAvailable = 'y';   //是否可用

    public Role()
    {
        super();
    }

    public Role(String code)
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

    public String getEnname()
    {
        return enname;
    }

    public void setEnname(String enname)
    {
        this.enname = enname;
    }

    public String getRoeType()
    {
        return roeType;
    }

    public void setRoeType(String roeType)
    {
        this.roeType = roeType;
    }

    public Set<Menu> getMenus()
    {
        return menus;
    }

    public void setMenus(Set<Menu> menus)
    {
        this.menus = menus;
    }

    public char getIsAvailable()
    {
        return isAvailable;
    }

    public void setIsAvailable(char isAvailable)
    {
        this.isAvailable = isAvailable;
    }
}
