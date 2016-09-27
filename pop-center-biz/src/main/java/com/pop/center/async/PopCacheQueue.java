package com.pop.center.async;

import com.pop.center.dto.PopDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.LinkedTransferQueue;

/**
 * Created by xugang on 16/9/26.
 */
@Component
public class PopCacheQueue {
    private LinkedTransferQueue<PopDto> dtos;
    private static final Logger logger = LoggerFactory.getLogger(PopCacheQueue.class);
    @PostConstruct
    public void init(){
        this.dtos = new LinkedTransferQueue<>();
    }

    public void put(PopDto popCacheDto){
        this.dtos.put(popCacheDto);
    }

    public PopDto take(){
        try {
            return this.dtos.take();
        } catch (InterruptedException e) {
            logger.error("PopCacheDto take erro",e);
        }
        return null;
    }
}
