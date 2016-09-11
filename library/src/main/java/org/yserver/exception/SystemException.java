package org.yserver.exception;

/**
 * Description: 自定义系统异常处理.<br>
 * Date: 2016/4/7 18:05 <br>
 * Author: ysj
 */
public class SystemException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public SystemException(String detailMessage) {
        super(detailMessage);
    }

    public SystemException(Throwable throwable) {
        super(throwable.getMessage(), throwable);
    }

    public SystemException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }
}