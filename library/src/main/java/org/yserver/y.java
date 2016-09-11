package org.yserver;

import org.yserver.task.TaskManagerImpl;
import org.yserver.utils.Log;

/**
 * ClassName: y <br>
 * Description: Y中心. <br>
 * Date: 2015-12-2 下午3:02:04 <br>
 *
 * @author ysj
 * @since JDK 1.7
 */
public final class y {

    public static Log log() {
        StackTraceElement caller = new Throwable().getStackTrace()[2];
        return Log.getLogger(caller.getClass());
    }

    public static TaskManeger task() {
        if (Core.taskManeger == null) {
            TaskManagerImpl.registerInstance();
        }
        return Core.taskManeger;
    }

    private y() {
    }

    public static class Core {
        private static TaskManeger taskManeger;
        private static Log logger;

        public static void setTaskController(TaskManeger taskManeger) {
            Core.taskManeger = taskManeger;
        }

        private Core() {
        }
    }
}
