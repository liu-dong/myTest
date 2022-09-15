package com.dong.javaThink.twentyfirst;

import java.util.concurrent.ThreadFactory;

/**
 * @author liudong 2022/9/15
 */
public class SImpleDaemonFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setDaemon(true);
        return thread;
    }
}
