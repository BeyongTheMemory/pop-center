package com.pop.center.async;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pop.cache.RedisOperate;
import com.pop.center.dao.PopDAO;
import com.pop.center.dto.PopDto;
import com.pop.center.entity.PopEntity;
import com.pop.center.enums.RedisKey;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xugang on 16/9/26.
 */
@Component
public class PopCacheThread implements Runnable{
    @Autowired
    private RedisOperate redis;
    @Autowired
    private PopCacheQueue popCacheQueue;
    @Autowired
    private PopDAO popDAO;

    @PostConstruct
    public void init(){
        Thread thread = new Thread(this, "PopCacheThread");
        thread.start();
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            PopDto dto = popCacheQueue.take();
            addPopInCache(dto.getGeoHash(),dto);
        }
    }

    private void addPopInCache(String geoHash,PopDto popDto){
        String popListString = redis.getStringByKey(String.format(RedisKey.popList,geoHash));
        if(StringUtils.isEmpty(popListString)){
            //先查出来
            List<PopDto> popDtos = new ArrayList<>();
            List<PopEntity> popEntities = popDAO.getByGeoHash(geoHash);
            if(!CollectionUtils.isEmpty(popEntities)){
                for (PopEntity popEntity:popEntities){
                    PopDto popDtoTemp = new PopDto();
                    BeanUtils.copyProperties(popEntity, popDtoTemp);
                    popDtos.add(popDtoTemp);
                }
            }
            popDtos.add(popDto);
            redis.set(String.format(RedisKey.popList,geoHash), JSONObject.toJSONString(popDtos),RedisKey.POPLIST_TTL);
        }else {
            List<PopDto> popDtos = JSON.parseArray(popListString, PopDto.class);
            popDtos.add(popDto);
            redis.set(String.format(RedisKey.popList,geoHash), JSONObject.toJSONString(popDtos),RedisKey.POPLIST_TTL);
        }
    }
}
