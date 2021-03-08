package com.haui.demo.utils;

import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class TaskPool {

    private static final int MAX_POOL_SIZE = 100;
    private static final int CORE_POOL_SIZE = 100;
    private static final int QUEUE_CAPACITY = 100;

    public static ThreadPoolExecutor executor = new ThreadPoolExecutor(CORE_POOL_SIZE,
            MAX_POOL_SIZE,
            100,
            TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<>(QUEUE_CAPACITY));



}