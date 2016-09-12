package org.yserver.core.controller;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.yserver.core.config.Constant;
import org.yserver.utils.exception.SystemException;
import org.yserver.utils.Log;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyEditorSupport;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: BaseController.<br>
 * Date: 2016/9/6 0:17<br>
 * Author: ysj
 */
@Controller
public class BaseController implements ControllerApi {
    protected Log logger = Log.getLogger(this.getClass());

    /**
     * HttpServletRequest对象
     */
    @Autowired
    private HttpServletRequest request;

    public HttpServletRequest getRequest() {
        return request;
    }

    /**
     * 初始化数据绑定
     * 1. 将所有传递进来的String进行HTML编码，防止XSS攻击
     * @param binder
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        // String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
        binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
            }
            @Override
            public String getAsText() {
                Object value = getValue();
                return value != null ? value.toString() : "";
            }
        });
    }


    /**
     * 异常捕获
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleException(Exception ex, HttpServletRequest request) {
        // 业务异常
        logger.error(ex.getMessage(), ex);
        if (ex instanceof SystemException) {
            return new ModelAndView()
                    .addObject(Constant.ServerConfig.RESPONSE_STATUS, Constant.ServerConfig.RESPONSE_STATUS_ERROR)
                    .addObject(Constant.ServerConfig.RESPONSE_CODE, Constant.ServerConfig.RESPONSE_SUCCESS_CODE_200)
                    .addObject(Constant.ServerConfig.RESPONSE_MSG, ex.getMessage());
        } else {
            return new ModelAndView()
                    .addObject(Constant.ServerConfig.RESPONSE_STATUS, Constant.ServerConfig.RESPONSE_STATUS_FAIL)
                    .addObject(Constant.ServerConfig.RESPONSE_CODE, Constant.ServerConfig.RESPONSE_ERROR_CODE_500)
                    .addObject(Constant.ServerConfig.RESPONSE_MSG, ex.getMessage());
        }
    }

    /**
     * error:(请求错误处理). <br>
     *
     * @param msg
     * @return
     * @author ysj
     * @since JDK 1.7 date: 2016年7月22日 上午10:02:07 <br>
     */
    public Map<String, Object> error(String msg) {
        Map<String, Object> result = new HashMap<>();
        result.put(Constant.ServerConfig.RESPONSE_STATUS, Constant.ServerConfig.RESPONSE_STATUS_ERROR);
        result.put(Constant.ServerConfig.RESPONSE_CODE, Constant.ServerConfig.RESPONSE_SUCCESS_CODE_200);
        result.put(Constant.ServerConfig.RESPONSE_MSG, msg);
        return result;
    }

    /**
     * fail:(请求失败处理). <br>
     *
     * @param msg
     * @return
     * @author ysj
     * @since JDK 1.7 date: 2016年7月22日 上午10:02:07 <br>
     */
    public Map<String, Object> fail(String msg) {
        Map<String, Object> result = new HashMap<>();
        result.put(Constant.ServerConfig.RESPONSE_STATUS, Constant.ServerConfig.RESPONSE_STATUS_FAIL);
        result.put(Constant.ServerConfig.RESPONSE_CODE, Constant.ServerConfig.RESPONSE_ERROR_CODE_500);
        result.put(Constant.ServerConfig.RESPONSE_MSG, msg);
        return result;
    }

    /**
     * timeout:(请求超时处理). <br>
     *
     * @return
     * @author ysj
     * @since JDK 1.7 date: 2016年7月22日 上午10:13:27 <br>
     */
    public Map<String, Object> timeout() {
        Map<String, Object> result = new HashMap<>();
        result.put(Constant.ServerConfig.RESPONSE_STATUS, Constant.ServerConfig.RESPONSE_STATUS_TIMEOUT);
        result.put(Constant.ServerConfig.RESPONSE_CODE, Constant.ServerConfig.RESPONSE_ERROR_CODE_300);
        result.put(Constant.ServerConfig.RESPONSE_MSG, "会话超时，请重新登录");
        return result;
    }

    /**
     * success:(请求成功处理). <br>
     *
     * @param data
     * @return
     * @author ysj
     * @since JDK 1.7 date: 2016年7月22日 上午10:02:07 <br>
     */
    public Map<String, Object> success(Object data) {
        Map<String, Object> result = new HashMap<>();
        result.put(Constant.ServerConfig.RESPONSE_STATUS, Constant.ServerConfig.RESPONSE_STATUS_SUCCESS);
        result.put(Constant.ServerConfig.RESPONSE_CODE, Constant.ServerConfig.RESPONSE_SUCCESS_CODE_200);
        result.put(Constant.ServerConfig.RESPONSE_MSG, "");
        if (null != data) {
            result.put(Constant.ServerConfig.RESPONSE_DATA, data);
        }
        return result;
    }
}