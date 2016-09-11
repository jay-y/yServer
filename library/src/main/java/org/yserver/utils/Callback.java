package org.yserver.utils;

/**
 * ClassName: Callback <br>
 * Description: 通用回调接口. <br>
 * Date: 2015-12-2 下午2:32:52 <br>
 * Author ysj
 */
public interface Callback {

    interface Cancelable {
        void cancel();

        boolean isCancelled();
    }
}
