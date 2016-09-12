package org.yserver.core.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.yserver.y;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

/**
 * 基于HandlerExceptionResolver的异常处理类.<br>
 * Date: 2016/09/04<br>
 * Author: ysj
 */
public class CustomHandlerExceptionResolver extends ExceptionHandlerExceptionResolver {
    /**
     * 缺省的错误页面
     */
    private String defaultErrorView;

    /**
     * 设置执行顺序为最先执行
     */
    public CustomHandlerExceptionResolver() {
        super.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    /**
     * 获取缺省的错误页面
     * @return 返回异常视图
     */
    public String getDefaultErrorView() {
        return defaultErrorView;
    }

    /**
     * 设置缺省的错误页面
     * @param defaultErrorView 错误页面
     */
    public void setDefaultErrorView(String defaultErrorView) {
        this.defaultErrorView = defaultErrorView;
    }

    /**
     * 处理拦截到的方法异常
     * @param request       请求
     * @param response      响应
     * @param handlerMethod 被拦截方法
     * @param exception     针对不同类型的异常进行处理
     * @return 返回异常视图
     */
    protected ModelAndView doResolveHandlerMethodException(
            HttpServletRequest request, HttpServletResponse response,
            HandlerMethod handlerMethod, Exception exception) {

        y.log().error(exception.getMessage());

        // 若干reponse输出流被使用，需要重置
        response.reset();

        // 没有指定异常处理方法，直接返回
        if (handlerMethod == null) {
            return null;
        }

        // 获取异常处理方法
        Method method = handlerMethod.getMethod();

        if (method == null) {
            return null;
        }

        // 调用异常处理方法，返回ModelAndView
        ModelAndView returnValue = super.doResolveHandlerMethodException(
                request, response, handlerMethod, exception);

        // 获取异步调用注解
        ResponseBody responseBodyAnn = AnnotationUtils.findAnnotation(method,
                ResponseBody.class);

        // 使用了异步调用的注解
        if (responseBodyAnn != null) {
            return handlerExceptionModelAndView(request, response, method,
                    returnValue);
        }

        if (returnValue.getViewName() == null) {
            returnValue.setViewName(defaultErrorView);
        }

        return returnValue;

    }

    /**
     * 异常视图处理
     * @param returnValue 异常视图
     * @param request     请求
     * @param response    响应
     * @param method      模型
     * @return 返回异常视图
     */
    private ModelAndView handlerExceptionModelAndView(
            HttpServletRequest request, HttpServletResponse response,
            Method method, ModelAndView returnValue) {
        try {
            ResponseStatus responseStatusAnn = AnnotationUtils.findAnnotation(
                    method, ResponseStatus.class);
            if (responseStatusAnn != null) {
                HttpStatus responseStatus = responseStatusAnn.value();
                String reason = responseStatusAnn.reason();
                if (!StringUtils.hasText(reason)) {
                    response.setStatus(responseStatus.value());
                } else {
                    try {
                        response.sendError(responseStatus.value(), reason);
                    } catch (IOException e) {
                        y.log().error(e.getMessage());
                    }
                }
            }
            // 使用json方式返回异常视图处理信息
            return handleResponseBody(returnValue, request, response);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 异常视图处理
     * @param returnValue 异常视图
     * @param request     请求
     * @param response    响应
     * @return 返回异常视图
     */
    private ModelAndView handleResponseBody(ModelAndView returnValue,
                                            HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        ModelMap value = returnValue.getModelMap();
        HttpInputMessage inputMessage = new ServletServerHttpRequest(request);
        List<MediaType> acceptedMediaTypes = inputMessage.getHeaders()
                .getAccept();
        if (acceptedMediaTypes.isEmpty()) {
            acceptedMediaTypes = Collections.singletonList(MediaType.ALL);
        }
        MediaType.sortByQualityValue(acceptedMediaTypes);
        ServletServerHttpResponse outputMessage = new ServletServerHttpResponse(
                response);
        try {
            Class<?> returnValueType = value.getClass();
            List<HttpMessageConverter<?>> messageConverters = super
                    .getMessageConverters();
            if (messageConverters != null) {
                for (MediaType acceptedMediaType : acceptedMediaTypes) {
                    for (
                            HttpMessageConverter messageConverter : messageConverters) {
                        if (messageConverter.canWrite(returnValueType,
                                acceptedMediaType)) {
                            messageConverter.write(value, acceptedMediaType,
                                    outputMessage);
                            return new ModelAndView();
                        }
                    }
                }
            }
            y.log().warn("Could not find HttpMessageConverter that supports return type ["
                    + returnValueType + "] and " + acceptedMediaTypes);
            return null;
        } finally {
            outputMessage.close();
        }
    }
}
