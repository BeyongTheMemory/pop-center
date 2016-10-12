package com.pop.center.async;

import com.alibaba.fastjson.JSONObject;
import com.pop.cache.RedisOperate;
import com.pop.center.dto.pop.PopInfoDto;
import com.pop.center.enums.RedisKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by xugang on 16/9/27.
 */
@Component
public class PopInfoCacheThread implements Runnable{
    @Autowired
    private PopInfoCacheQueue popInfoCacheQueue;
    @Autowired
    private RedisOperate redis;
    @PostConstruct
    public void init(){
        Thread thread = new Thread(this, "popInfoCacheThread");
        thread.start();
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            PopInfoDto dto = popInfoCacheQueue.take();
            redis.set(String.format(RedisKey.popInfo,dto.getPopId()), JSONObject.toJSONString(dto),RedisKey.POPINFO_TTL);
        }
    }
}
