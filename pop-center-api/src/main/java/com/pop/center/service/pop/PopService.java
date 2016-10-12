package com.pop.center.service.pop;

import com.pop.center.dto.pop.PopDto;
import com.pop.center.dto.pop.PopInfoDto;
import com.pop.center.dto.pop.PopMessageDto;
import com.pop.center.dto.pop.PopNewDto;
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
