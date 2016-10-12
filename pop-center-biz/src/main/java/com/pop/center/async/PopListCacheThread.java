package com.pop.center.async;

import com.alibaba.fastjson.JSONObject;
import com.pop.cache.RedisOperate;
import com.pop.center.dto.pop.PopDto;
import com.pop.center.enums.RedisKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * Created by xugang on 16/9/25.
 */
@Component
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
            List<PopDto> dtos = popListCacheQueue.take();
            HashMap<String,List<PopDto>> popHash = new HashMap<>();
            //映射到各个hash
            for(PopDto popDto:dtos){
                List<PopDto> popDtoList = popHash.get(popDto.getGeoHash());
                if (popDtoList != null){
                    popDtoList.add(popDto);
                }else {
                    popDtoList = new ArrayList<>();
                    popDtoList.add(popDto);
                    popHash.put(popDto.getGeoHash(),popDtoList);
                }
            }
            //遍历hash缓存
            Iterator iterator = popHash.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry entry = (Map.Entry)iterator.next();
                String geo = (String)entry.getKey();
                List<PopDto> popDtoList = (List<PopDto>) entry.getValue();
                redis.set(String.format(RedisKey.popList,geo), JSONObject.toJSONString(popDtoList),RedisKey.POPLIST_TTL);
            }
        }
    }
}
