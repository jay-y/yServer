package org.yserver.utils.task;

import java.util.Comparator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ClassName: PriorityExecutor <br> 
 * Description: 支持优先级的线程池管理类. <br> 
 * Date: 2015-12-2 下午3:41:42 <br> 
 * 
 * @author ysj 
 * @version  
 * @since JDK 1.7
 */
public class TaskExecutor implements Executor {
    private static final int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors()*2; // 核心线程数
    private static final int MAXIMUM_POOL_SIZE = 256; // 最大线程数
    private static final int KEEP_ALIVE = 1; // 线程池维护线程所允许的空闲时间

    private static final ThreadFactory sThreadFactory = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1); // 安全计数

        @Override
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "PriorityExecutor #" + mCount.getAndIncrement());
        }
    };

    private static final Comparator<Runnable> sRunnableComparator = new Comparator<Runnable>() {

        @Override
        public int compare(Runnable lhs, Runnable rhs) {
        	// 根据优先级排序
            if (lhs instanceof TaskRunnable && rhs instanceof TaskRunnable) {
                return ((TaskRunnable) lhs).priority.ordinal() - ((TaskRunnable) rhs).priority.ordinal();
            } else {
                return 0;
            }
        }
    };
    
    private final ThreadPoolExecutor mThreadPoolExecutor;

    public TaskExecutor() {
        this(CORE_POOL_SIZE);
    }

    public TaskExecutor(int poolSize) {
        BlockingQueue<Runnable> mPoolWorkQueue =
                new PriorityBlockingQueue<Runnable>(MAXIMUM_POOL_SIZE, sRunnableComparator);
        mThreadPoolExecutor = new ThreadPoolExecutor(
                poolSize,
                MAXIMUM_POOL_SIZE,
                KEEP_ALIVE,
                TimeUnit.SECONDS, // 线程池维护线程所允许的空闲时间的单位
                mPoolWorkQueue, // 线程池所使用的缓冲队列
                sThreadFactory);
    }

    public int getPoolSize() {
        return mThreadPoolExecutor.getCorePoolSize();
    }

    public void setPoolSize(int poolSize) {
        if (poolSize > 0) {
            mThreadPoolExecutor.setCorePoolSize(poolSize);
        }
    }

    public ThreadPoolExecutor getThreadPoolExecutor() {
        return mThreadPoolExecutor;
    }

    public boolean isBusy() {
        return mThreadPoolExecutor.getActiveCount() >= mThreadPoolExecutor.getCorePoolSize();
    }

    @Override
    public void execute(final Runnable runnable) {
        mThreadPoolExecutor.execute(runnable);
    }
}
