package modules.system.model;

import org.yserver.core.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * Description: Payment.<br>
 * Date: 2016/10/10 18:54<br>
 * Author: ysj
 */
@Entity
@Table(name = "sys_payment")
public class Payment extends BaseEntity
{

    @Column(name = "APP_ID", nullable = false)
    private String appId;

    @Column(name = "BUSINESS", nullable = false)
    private String business;//分配商户ID、接收付款或其他

    @Lob
    @Column(name = "PARAMS", nullable = false)
    private String params;//参数集合(key:val,key:val,...)

    @Column(name = "API_URL", nullable = false)
    private String apiUrl;//请求接口URL

    @Column(name = "NOTIFY_URL", nullable = false)
    private String notifyUrl;//通知处理URL

    @Column(name = "CANCEL_URL", nullable = false)
    private String cancelUrl;//取消交易调用URL

    @Column(name = "RETURN_URL", nullable = false)
    private String returnUrl;//交易结果返回调用URL

    @Column(name = "CATEGORY", nullable = false)
    private String category;//支付类别,如:UnionPay、AliPay、WeChat、PayPal等

    public Payment()
    {
        super();
    }

    public Payment(String code)
    {
        super(code);
    }

    public String getAppId()
    {
        return appId;
    }

    public void setAppId(String appId)
    {
        this.appId = appId;
    }

    public String getParams()
    {
        return params;
    }

    public void setParams(String params)
    {
        this.params = params;
    }

    public String getBusiness()
    {
        return business;
    }

    public void setBusiness(String business)
    {
        this.business = business;
    }

    public String getApiUrl()
    {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl)
    {
        this.apiUrl = apiUrl;
    }

    public String getNotifyUrl()
    {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl)
    {
        this.notifyUrl = notifyUrl;
    }

    public String getCancelUrl()
    {
        return cancelUrl;
    }

    public void setCancelUrl(String cancelUrl)
    {
        this.cancelUrl = cancelUrl;
    }

    public String getReturnUrl()
    {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl)
    {
        this.returnUrl = returnUrl;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }
}
