package org.yserver.core.config;

/**
 * ClassName: Constant <br>
 * Reason: Constant. <br>
 * date: 2016年7月27日 下午2:10:46 <br>
 *
 * @author ysj
 * @version 1.0
 * @since JDK 1.7
 */
public class Constant {
    public static final String _NULL = "";
    public static final String _Y = "y";
    public static final String _N = "n";
    public static final String _YES = "yes";
    public static final String _NO = "no";
    public static final String _ID = "id";
    public static final String _LOG = "log";
    public static final String _STATUS = "status";
    public static final String _DATA = "data";
    public static final String _CODE = "code";
    public static final String _MSG = "msg";
    public static final String _ERROR = "error";
    public static final String _SUCCESS = "success";
    public static final String _UTF_8 = "UTF-8";
    public static final String _TRUE = "true";
    public static final String _FALSE = "false";

    public static class ServerConfig {
        /**
         * Response Constants
         */
        // key and msg
        public static final String RESPONSE_STATUS = _STATUS;
        public static final String RESPONSE_DATA = _DATA;
        public static final String RESPONSE_MSG = _MSG;
        public static final String RESPONSE_CODE = _CODE;
        // status code
        public static final char RESPONSE_STATUS_SUCCESS = '0';
        public static final char RESPONSE_STATUS_ERROR = '1';
        public static final char RESPONSE_STATUS_FAIL = '2';
        public static final char RESPONSE_STATUS_TIMEOUT = '9';
        public static final String RESPONSE_SUCCESS_CODE_200 = "200"; // 请求成功
        public static final String RESPONSE_ERROR_CODE_300 = "300"; // 重定向请求
        public static final String RESPONSE_ERROR_CODE_400 = "400"; // 请求错误
        public static final String RESPONSE_ERROR_CODE_500 = "500"; // 系统错误
    }
}
