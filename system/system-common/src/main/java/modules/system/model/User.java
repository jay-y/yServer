package modules.system.model;

import org.yserver.core.model.BaseEntity;

import javax.persistence.*;

/**
 * Description: User.<br>
 * Date: 2016/9/18 14:12<br>
 * Author: ysj
 */
@Entity
@Table(name = "sys_user")
public class User extends BaseEntity {
    @Column(name = "USERNAME", nullable = false)
    private String username;//用户名
    @Column(name = "PASSWORD", nullable = false)
    private String password;//密码
    @Column(name = "NAME")
    private String name;//姓名
    @Column(name = "EMAIL")
    private String email;//邮箱
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "STATUS")
    private String status;//状态
    @Column(name = "IP")
    private String ip;//IP
    @Column(name = "SKIN")
    private String skin;//皮肤
    @Column(name = "PHOTO")
    private String photo;//头像
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ROLE_CODE", nullable = false)
    private Role role;//单角色

//    @ManyToMany(cascade = CascadeType.PERSIST)
//    @JoinTable(name = "sys_user_role_relational",
//            joinColumns = {@JoinColumn(name = "USER_CODE")},
//            inverseJoinColumns = {@JoinColumn(name = "ROLE_CODE")})
//    private Set<Role> roles = new LinkedHashSet<>();//多角色

    public User() {
        super();
    }

    public User(String code) {
        super(code);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    //    public Set<Role> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(Set<Role> roles) {
//        this.roles = roles;
//    }
}