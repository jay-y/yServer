package modules.system.model;

import org.yserver.core.model.BaseEntity;

import javax.persistence.*;

/**
 * Description: Logistics.<br>
 * Date: 2016/10/20 14:22<br>
 * Author: ysj
 */
@Entity
@Table(name = "sys_logistics")
public class Logistics extends BaseEntity {
    @Column(name = "IC")
    private String ic;//识别码

    @Column(name = "CLIENT_NAME", nullable = false)
    private String clientName;//用户名称,如:张三,XXX公司,XXX店铺,XXX客服...

    @Column(name = "CONTACT_WAY", nullable = false)
    private String contactWay;//联系方式,如:18866669999

    @Column(name = "COMPANY", nullable = false)
    private String company;//运输公司

    @Column(name = "PRICE", nullable = false)
    private double price = 0;//运输价格

    @Column(name = "CATEGORY", nullable = false)
    private String category;//marine|land|aviation

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "PHOTO_CODE")
    private File photo;//单图

    public Logistics() {
        super();
    }

    public Logistics(String code) {
        super(code);
    }

    public String getIc() {
        return ic;
    }

    public void setIc(String ic) {
        this.ic = ic;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getContactWay() {
        return contactWay;
    }

    public void setContactWay(String contactWay) {
        this.contactWay = contactWay;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public File getPhoto() {
        return photo;
    }

    public void setPhoto(File photo) {
        this.photo = photo;
    }
}
