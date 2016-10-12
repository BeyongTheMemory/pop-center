package com.pop.center.async;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pop.cache.RedisOperate;
import com.pop.center.dao.pop.PopDAO;
import com.pop.center.dto.pop.PopDto;
import com.pop.center.entity.pop.PopEntity;
import com.pop.center.enums.RedisKey;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by xugang on 16/10/12.
 */
@Component
public class PopDeleteThread implements Runnable {
    @Autowired
    private RedisOperate redis;
    @Autowired
    private PopDAO popDAO;
    @Autowired
    private PopDeleteQueue popDeleteQueue;


    @PostConstruct
    public void init() {
        Thread thread = new Thread(this, "PopDeleteThread");
        thread.start();
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            long id = popDeleteQueue.take();
            //首先更新数据库
            popDAO.deleteById(id);
            //更新所有缓存
            //1.漂浮泡泡缓存
            String popFloatListString = redis.getStringByKey(String.format(RedisKey.floatPopList));
            if (!StringUtils.isEmpty(popFloatListString)) {
                List<PopDto> popDtos = JSON.parseArray(popFloatListString, PopDto.class);
                //移除
                Iterator iterator = popDtos.iterator();
                while (iterator.hasNext()) {
                    PopDto dto = (PopDto) iterator.next();
                    if (dto.getId() == id) {
                        iterator.remove();
                        break;
                    }
                }
                redis.set(String.format(RedisKey.floatPopList), JSONObject.toJSONString(popDtos), RedisKey.POPLIST_TTL);
            }
            //2.固定泡泡缓存
            //查询数据库获得编码
            PopEntity popEntity = popDAO.getById(id);
            String popCacheListString = redis.getStringByKey(String.format(RedisKey.popList, popEntity.getGeoHash()));
            if (!StringUtils.isEmpty(popCacheListString)){
                List<PopDto> popDtos = JSON.parseArray(popCacheListString, PopDto.class);
                //移除
                Iterator iterator = popDtos.iterator();
                while (iterator.hasNext()) {
                    PopDto dto = (PopDto) iterator.next();
                    if (dto.getId() == id) {
                        iterator.remove();
                        break;
                    }
                }
                redis.set(String.format(RedisKey.popList,popEntity.getGeoHash()), JSONObject.toJSONString(popDtos),RedisKey.POPLIST_TTL);
            }

        }
    }
}
