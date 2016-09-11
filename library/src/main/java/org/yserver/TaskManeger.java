package org.yserver;

/**
 * ClassName: TaskController <br> 
 * Description: 任务管理接口. <br> 
 * Date: 2015-12-4 上午10:33:52 <br> 
 * 
 * @author ysj 
 * @version  
 * @since JDK 1.7
 */
public interface TaskManeger {

    /**
     * 在后台线程执行runnable
     *
     * @param runnable
     */
    void run(Runnable runnable);

    /**
     * 移除未执行的runnable
     *
     * @param runnable
     */
    void removeCallbacks(Runnable runnable);
    
    /**
     * 停掉所有启动线程
     */
    void shutdown();
}
