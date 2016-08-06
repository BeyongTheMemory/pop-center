package com.pop.center.serviceImpl;

import com.pop.center.dao.PopDAO;
import com.pop.center.dto.PopDto;
import com.pop.center.entity.PopEntity;
import com.pop.center.service.PopService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xugang on 16/8/6.
 */
@Service
public class PopServiceImpl implements PopService {

    @Autowired
    private PopDAO popDAO;


    public void save(PopDto popDto) {
        PopEntity popEntity = new PopEntity();
        BeanUtils.copyProperties(popDto,popEntity);
        popDAO.save(popEntity);
    }
}
