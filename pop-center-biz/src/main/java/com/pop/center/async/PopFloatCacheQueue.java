package com.pop.center.async;

import com.pop.center.dto.pop.PopDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.LinkedTransferQueue;

/**
 * Created by xugang on 16/9/26.
 */
@Component
public class PopFloatCacheQueue {
    private LinkedTransferQueue<PopDto> dtos;
    private static final Logger logger = LoggerFactory.getLogger(PopFloatCacheQueue.class);
    @PostConstruct
    public void init(){
        this.dtos = new LinkedTransferQueue<>();
    }

    public void put(PopDto popDto){
        this.dtos.put(popDto);
    }

    public PopDto take(){
        try {
            return this.dtos.take();
        } catch (InterruptedException e) {
            logger.error("PopFloatCacheQueue take erro",e);
        }
        return null;
    }
}
