package org.yserver.core.controller;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.support.BindingAwareModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.yserver.core.config.Constant;
import org.yserver.utils.DateUtil;
import org.yserver.utils.Log;
import org.yserver.utils.StringUtils;
import org.yserver.utils.ex.SystemException;
import org.yserver.y;

import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * Description: BaseController.<br>
 * Date: 2016/9/6 0:17<br>
 * Author: ysj
 */
@Controller
public class BaseController implements ControllerApi {
    protected Log logger = Log.getLogger(this.getClass());

    public ResponseBuilder getRespBuilder(HttpServletResponse response) {
        return new ResponseBuilder().with(response);
    }

    /**
     * 初始化数据绑定
     * 1. 将所有传递进来的String进行HTML编码，防止XSS攻击
     *
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
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtil.parseDate(text));
            }
        });
    }

    /**
     * 异常捕获
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleException(HttpServletResponse response, Model model, Exception ex) {
        // 业务异常
        logger.error(ex.getMessage(), ex);
        if (ex instanceof SystemException) {
            return getRespBuilder(response)
                    .setModel(model)
                    .setMsg("请求错误")
                    .fail();
        } else {
            return getRespBuilder(response)
                    .setModel(model)
                    .setMsg("系统异常")
                    .error();
        }
    }

    /**
     * 响应枚举
     */
    public enum ResponseEnum {
        SUCCESS(Constant.ServerConfig.RESPONSE_STATUS_SUCCESS,
                Constant.ServerConfig.RESPONSE_SUCCESS_CODE_200,
                "请求成功"),
        FAIL(Constant.ServerConfig.RESPONSE_STATUS_FAIL,
                Constant.ServerConfig.RESPONSE_ERROR_CODE_400,
                "请求失败"),
        ERROR(Constant.ServerConfig.RESPONSE_STATUS_ERROR,
                Constant.ServerConfig.RESPONSE_ERROR_CODE_500,
                "系统错误"),
        TIMEOUT(Constant.ServerConfig.RESPONSE_STATUS_TIMEOUT,
                Constant.ServerConfig.RESPONSE_ERROR_CODE_300,
                "请求超时");

        private Object status;
        private Object code;
        private String msg;

        ResponseEnum(Object status, Object code, String msg) {
            this.status = status;
            this.code = code;
            this.msg = msg;
        }

        public Object getStatus() {
            return status;
        }

        public void setStatus(Object status) {
            this.status = status;
        }

        public Object getCode() {
            return code;
        }

        public void setCode(Object code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

    public static class ResponseBuilder {
        private HttpServletResponse response;
        private Model model;
        private Object data;
        private String msg;
        private String index;

        public ResponseBuilder with(HttpServletResponse response) {
            this.response = response;
            return this;
        }

        public ResponseBuilder setLoad(String index) {
            this.index = index;
            return this;
        }

        public ResponseBuilder setModel(Model model) {
            this.model = model;
            return this;
        }

        public ResponseBuilder setData(Object data) {
            this.data = data;
            return this;
        }

        public ResponseBuilder setMsg(String... messages) {
            StringBuilder sb = new StringBuilder();
            for (String message : messages) {
                sb.append(message).append(messages.length > 1 ? "<br/>" : "");
            }
            this.msg = sb.toString();
            return this;
        }

        public <T extends Object> T success() {
            return response(ResponseEnum.SUCCESS);
        }

        public <T extends Object> T error() {
            return response(ResponseEnum.ERROR);
        }

        public <T extends Object> T fail() {
            return response(ResponseEnum.FAIL);
        }

        public <T extends Object> T timeout() {
            return response(ResponseEnum.TIMEOUT);
        }

        public <T extends Object> T response(ResponseEnum responseEnum) {
            if (StringUtils.isEmpty(msg)) {
                this.msg = responseEnum.getMsg();
            }
            return doResponse(responseEnum, response, model, data, msg);
        }

        /**
         * 响应处理(自定义message)
         *
         * @param responseEnum
         * @param response
         * @param model
         * @param data
         * @param msg
         * @param <T>
         * @return
         */
        private <T extends Object> T doResponse(ResponseEnum responseEnum, HttpServletResponse response, Model model, Object data, String msg) {
            if (null == responseEnum) {
                throw new SystemException("ResponseEnum can not be empty.");
            }
            if (null == response) {
                throw new SystemException("HttpServletResponse can not be empty.");
            }
            return renderString(response,
                    wrapModel(model, responseEnum.getStatus(), responseEnum.getCode(), msg, data));
        }

        /**
         * 组装Model
         *
         * @param model
         * @param status
         * @param code
         * @param msg
         * @param data
         * @return
         */
        private Model wrapModel(Model model, Object status, Object code, Object msg, Object data) {
            if (null == model) model = new BindingAwareModelMap();
            model.addAttribute(Constant.ServerConfig.RESPONSE_STATUS, status);
            model.addAttribute(Constant.ServerConfig.RESPONSE_CODE, code);
            model.addAttribute(Constant.ServerConfig.RESPONSE_MSG, msg);
            if (null != data) {
                model.addAttribute(Constant.ServerConfig.RESPONSE_DATA, data);
            }
            return model;
        }

        /**
         * 客户端返回JSON字符串
         *
         * @param response
         * @param object
         * @return
         */
        private <T extends Object> T renderString(HttpServletResponse response, Object object) {
            preToJsonString(object);
            return renderString(response, y.json().toJson(object), "application/json");
        }

        /**
         * 客户端返回字符串
         *
         * @param response
         * @param string
         * @return
         */
        private <T extends Object> T renderString(HttpServletResponse response, String string, String type) {
            //注释掉，否则cookie等信息都不能保存
//        response.reset();
            response.setContentType(type);
            response.setCharacterEncoding(Constant._UTF_8);
            try {
                response.getWriter().print(string);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (StringUtils.isEmpty(index)) {
                return null;
            }
            return (T) index;
        }

        /**
         * 转json前预处理
         * 去掉model中的不可序列化的BindingResult对象，避免转json时出现异常
         * key值为"org.springframework.validation.BindingResult.XXX"
         *
         * @param model
         */
        private void preToJsonString(Object model) {
            if (model instanceof BindingAwareModelMap) {
                for (Map.Entry<String, Object> entry : ((BindingAwareModelMap) model).entrySet()) {
                    String attributeName = entry.getKey();
                    if (attributeName.startsWith(BindingResult.MODEL_KEY_PREFIX)) {
                        ((BindingAwareModelMap) model).remove(attributeName);
                        return;
                    }
                }
            }
        }

        private ResponseBuilder() {
        }
    }
}