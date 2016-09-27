package com.pop.center.async;

import com.pop.center.dto.PopInfoDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.LinkedTransferQueue;

/**
 * Created by xugang on 16/9/27.
 */
@Component
public class PopInfoCacheQueue {
    private LinkedTransferQueue<PopInfoDto> dtos;
    private static final Logger logger = LoggerFactory.getLogger(PopInfoCacheQueue.class);
    @PostConstruct
    public void init(){
        this.dtos = new LinkedTransferQueue<>();
    }

    public void put(PopInfoDto popInfoDto){
        this.dtos.put(popInfoDto);
    }

    public PopInfoDto take(){
        try {
            return this.dtos.take();
        } catch (InterruptedException e) {
            logger.error("PopInfoQueue take erro",e);
        }
        return null;
    }
}
