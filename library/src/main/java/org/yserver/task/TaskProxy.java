package org.yserver.task;

import java.util.concurrent.Executor;

/** 
 * ClassName: TaskProxy <br> 
 * Description: 异步任务的代理类(仅在task包内可用). <br> 
 * Date: 2015-12-4 上午10:34:46 <br> 
 * 
 * @author 
 * @version  
 * @since JDK 1.7
 */
public class TaskProxy{
	public static final TaskExecutor defaultExecutor = new TaskExecutor();
	
	private final Executor executor;
	
	protected TaskProxy() {
        this.executor = defaultExecutor;
    }

    public final Executor getExecutor() {
        return this.executor;
    }
}
