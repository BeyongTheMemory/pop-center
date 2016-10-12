package com.pop.center.async;

import com.pop.center.dto.pop.PopDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.LinkedTransferQueue;

/**
 * Created by xugang on 16/9/25.
 */
@Component
public class PopListCacheQueue {
    private LinkedTransferQueue<List<PopDto>> dtos;
    private static final Logger logger = LoggerFactory.getLogger(PopListCacheQueue.class);

    @PostConstruct
    public void init() {
        this.dtos = new LinkedTransferQueue<>();
    }

    public void put(List<PopDto> popDtos) {
        this.dtos.put(popDtos);
    }

    public List<PopDto> take() {
        try {
            return this.dtos.take();
        } catch (InterruptedException e) {
            logger.error("popListCacheQueue take erro", e);
        }
        return null;
    }
}
