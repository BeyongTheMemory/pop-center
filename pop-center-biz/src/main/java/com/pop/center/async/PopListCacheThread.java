package com.pop.center.async;

import com.pop.cache.RedisOperate;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * Created by xugang on 16/9/25.
 */
public class PopListCacheThread implements Runnable{
    @Autowired
    private RedisOperate redis;
    @Autowired
    private PopListCacheQueue popListCacheQueue;

    @PostConstruct
    public void init(){
        Thread thread = new Thread(this, "PopListCacheThread");
        thread.start();
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            PopListCacheDto dto = popListCacheQueue.take();
        }
    }
}
