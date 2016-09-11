package org.yserver.task;

/**
 * ClassName: PriorityRunnable <br> 
 * Description: 带有优先级的Runnable类型(仅在task包内可用). <br> 
 * Date: 2015-12-2 下午3:42:01 <br> 
 * 
 * @author user 
 * @version  
 * @since JDK 1.7
 */
public class TaskRunnable implements Runnable {
    public final TaskPriority priority;
    private final Runnable runnable;

    public TaskRunnable(TaskPriority priority, Runnable runnable) {
        this.priority = priority == null ? TaskPriority.DEFAULT : priority;
        this.runnable = runnable;
    }

    @Override
    public final void run() {
        this.runnable.run();
    }
}
