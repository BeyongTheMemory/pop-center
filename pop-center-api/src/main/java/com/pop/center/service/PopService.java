package com.pop.center.service;

import com.pop.center.dto.PopDto;
import com.pop.center.dto.PopInfoDto;
import com.pop.center.dto.PopMessageDto;
import com.pop.center.dto.PopNewDto;
import com.pop.mybatis.entity.Page;
import com.pop.mybatis.entity.Pageable;

import java.util.List;

/**
 * Created by xugang on 16/8/6.
 */
public interface PopService {
    public void save(PopNewDto popNewDto);
    //public Page<PopDto> getPop(double lat,double lon,int range,Pageable pageable);
    public List<PopDto> getPop(double lat, double lon);
    public PopInfoDto getPopInfo(long popId);
    public void addMessage(PopMessageDto popMessageDto);
    public Page<PopMessageDto> getMessage(long popId,Pageable pageable);
}
