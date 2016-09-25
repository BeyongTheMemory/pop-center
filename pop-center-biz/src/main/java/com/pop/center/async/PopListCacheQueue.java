package com.pop.center.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.LinkedTransferQueue;

/**
 * Created by xugang on 16/9/25.
 */
@Component
public class PopListCacheQueue {
    private LinkedTransferQueue<PopListCacheDto> dtos;
    private static final Logger logger = LoggerFactory.getLogger(PopListCacheQueue.class);

    @PostConstruct
    public void init(){
        this.dtos = new LinkedTransferQueue<>();
    }

    public void put(PopListCacheDto popListCacheDto){
        this.dtos.put(popListCacheDto);
    }

    public PopListCacheDto take(){
        try {
            return this.dtos.take();
        } catch (InterruptedException e) {
            logger.error("popListCacheQueue take erro",e);
        }
        return null;
    }
}
