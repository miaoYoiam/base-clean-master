package com.mine.data.executor;


import com.mine.domain.executor.ThreadExecutor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class JobExecutor implements ThreadExecutor {
    private static final int INITIAL_POOL_SIZE = 3;
    private static final int KEEP_ALIVE_TIME = 10;
    private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
    private static final int MAX_POOL_SIZE = 5;
    private final ThreadPoolExecutor mThreadPoolExecutor = new ThreadPoolExecutor(INITIAL_POOL_SIZE, MAX_POOL_SIZE, 10, KEEP_ALIVE_TIME_UNIT, new LinkedBlockingQueue(), new JobThreadFactory());

    @Inject
    public JobExecutor() {

    }

    private static class JobThreadFactory implements ThreadFactory {
        private static final String THREAD_NAME = "android_";
        private int counter;

        private JobThreadFactory() {
            this.counter = 0;
        }

        public Thread newThread(Runnable runnable) {
            StringBuilder append = new StringBuilder().append(THREAD_NAME);
            int i = this.counter;
            this.counter = i + 1;
            return new Thread(runnable, append.append(i).toString());
        }
    }

    public void execute(Runnable runnable) {
        if (runnable == null) {
            throw new IllegalArgumentException("Runnable to execute cannot be null");
        }
        this.mThreadPoolExecutor.execute(runnable);
    }
}
