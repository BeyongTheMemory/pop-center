package com.pop.center.async;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pop.cache.RedisOperate;
import com.pop.center.dao.pop.PopDAO;
import com.pop.center.dao.pop.PopInfoDAO;
import com.pop.center.dto.pop.PopDto;
import com.pop.center.dto.pop.PopInfoDto;
import com.pop.center.entity.pop.PopEntity;
import com.pop.center.enums.RedisKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.Iterator;
import java.util.List;

/**
 * Created by xugang on 16/10/12.
 */
@Component
public class PopAddLookNumThread implements Runnable {
    @Autowired
    private RedisOperate redis;
    @Autowired
    private PopInfoDAO popInfoDAO;
    @Autowired
    private PopDeleteQueue popDeleteQueue;
    @Autowired
    private PopInfoCacheQueue popInfoCacheQueue;


    @PostConstruct
    public void init() {
        Thread thread = new Thread(this, "PopDeleteThread");
        thread.start();
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            long id = popDeleteQueue.take();
            popInfoDAO.addLookNum(id);
            String popInfoDtoString = redis.getStringByKey(String.format(RedisKey.popInfo,id));
            if(!StringUtils.isEmpty(popInfoDtoString)){
                PopInfoDto popInfoDto = JSON.parseObject(popInfoDtoString,PopInfoDto.class);
                popInfoDto.setLookNum(popInfoDto.getLookNum() + 1);
                //刷新缓存
                popInfoCacheQueue.put(popInfoDto);
            }
        }

    }
}
