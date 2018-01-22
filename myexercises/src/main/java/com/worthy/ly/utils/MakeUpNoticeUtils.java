package com.worthy.ly.utils;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class MakeUpNoticeUtils {
    private ThreadPoolExecutor executor;

    public MakeUpNoticeUtils() {
        ThreadFactory threadFactory = new BasicThreadFactory.Builder().namingPattern("MakeUpNoticeUtils-%d").build();
        LinkedBlockingDeque<Runnable> queue = new LinkedBlockingDeque<>(10000);
        executor = new ThreadPoolExecutor(4, 20, 1000 * 60, TimeUnit.MICROSECONDS, queue, threadFactory);
    }

    public ThreadPoolExecutor exec(){
        return executor;
    }

    @PreDestroy
    public void shutdown() {
        executor.shutdown();
    }
}
