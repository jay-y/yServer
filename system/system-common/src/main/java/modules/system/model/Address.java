package modules.system.model;

import org.yserver.core.model.BaseEntity;

import javax.persistence.*;

/**
 * Description: 地址.<br>
 * Date: 2016/9/18 14:12<br>
 * Author: ysj
 */
@MappedSuperclass
public class Address extends BaseEntity {

    @Column(name = "CLIENT_NAME")
    private String clientName;//客户端名称,如:张三,XXX公司,XXX店铺,XXX客服...

    @Column(name = "CONTACT_WAY")
    private String contactWay;//联系方式,如:18866669999

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "NATION_CODE")
    private Area nation;//国家

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "PROVINCE_CODE")
    private Area province;//省份

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "CITY_CODE")
    private Area city;//城市

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "AREA_CODE")
    private Area area;//地区

    @Column(name = "DETAILS")
    private String details;//详细地址

    @Column(name = "ZIPCODE")
    private String zipcode; //邮编

    @Column(name = "LONGITUDE", nullable = false)
    private double longitude = 0;//经度

    @Column(name = "LATITUDE", nullable = false)
    private double latitude = 0;//纬度

    public Address() {
        super();
    }

    public Address(String code) {
        super(code);
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

    public Area getNation() {
        return nation;
    }

    public void setNation(Area nation) {
        this.nation = nation;
    }

    public Area getProvince() {
        return province;
    }

    public void setProvince(Area province) {
        this.province = province;
    }

    public Area getCity() {
        return city;
    }

    public void setCity(Area city) {
        this.city = city;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}