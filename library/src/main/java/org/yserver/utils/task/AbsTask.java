package org.yserver.utils.task;

import org.yserver.utils.Callback;

import java.util.concurrent.Executor;

/**
 * 异步任务基类
 *
 * @param <ResultType>
 */
public abstract class AbsTask<ResultType> implements Callback.Cancelable
{

    public TaskPriority getPriority()
    {
        return null;
    }

    public Executor getExecutor()
    {
        return null;
    }
}
