package com.pop.center.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.LinkedTransferQueue;


@Component
public class PopAddLookNumQueue {
    private LinkedTransferQueue<Long> ids;
    private static final Logger logger = LoggerFactory.getLogger(PopAddLookNumQueue.class);
    @PostConstruct
    public void init(){
        this.ids = new LinkedTransferQueue<>();
    }

    public void put(Long id){
        this.ids.put(id);
    }

    public Long take(){
        try {
            return this.ids.take();
        } catch (InterruptedException e) {
            logger.error("PopAddLookNumQueue take erro",e);
        }
        return null;
    }
}
