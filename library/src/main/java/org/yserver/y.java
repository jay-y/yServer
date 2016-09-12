package org.yserver;

import org.yserver.utils.JsonUtil;
import org.yserver.utils.Log;
import org.yserver.utils.TaskManeger;
import org.yserver.utils.json.JacksonImpl;
import org.yserver.utils.task.TaskManagerImpl;

/**
 * ClassName: y <br>
 * Description: Y核心. <br>
 * Date: 2015-12-2 下午3:02:04 <br>
 *
 * @author ysj
 * @since JDK 1.7
 */
public final class y {
    public static JsonUtil json(){
        return JacksonImpl.getInstance();
    }

    public static TaskManeger task() {
        return TaskManagerImpl.getInstance();
    }

    public static Log log() {
        StackTraceElement caller = new Throwable().getStackTrace()[2];
        return Log.getLogger(caller.getClass());
    }

    private y() {
    }
}
