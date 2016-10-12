package com.pop.center.async;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pop.cache.RedisOperate;
import com.pop.center.dao.pop.PopDAO;
import com.pop.center.dto.pop.PopDto;
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
            popDAO.addLookNum(id);
        }

    }
}
