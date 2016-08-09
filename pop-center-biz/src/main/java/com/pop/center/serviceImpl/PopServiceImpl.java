package com.pop.center.serviceImpl;

import com.pop.center.dao.PopDAO;
import com.pop.center.dao.PopInfoDAO;
import com.pop.center.dao.PopMessageDAO;
import com.pop.center.dto.PopDto;
import com.pop.center.dto.PopInfoDto;
import com.pop.center.dto.PopMessageDto;
import com.pop.center.dto.PopNewDto;
import com.pop.center.entity.PopEntity;
import com.pop.center.entity.PopInfoEntity;
import com.pop.center.entity.PopMessageEntity;
import com.pop.center.service.PopService;
import com.pop.center.util.DistanceUtil;
import com.pop.mybatis.entity.Page;
import com.pop.mybatis.entity.Pageable;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xugang on 16/8/6.
 */
@Service
public class PopServiceImpl implements PopService {

    @Autowired
    private PopDAO popDAO;
    @Autowired
    private PopInfoDAO popInfoDAO;
    @Autowired
    private PopMessageDAO popMessageDAO;


    public void save(PopNewDto popNewDto) {
        PopEntity popEntity = new PopEntity();
        BeanUtils.copyProperties(popNewDto, popEntity);
        popDAO.save(popEntity);
        PopInfoEntity popInfoEntity = new PopInfoEntity();
        BeanUtils.copyProperties(popNewDto, popInfoEntity);
        popInfoEntity.setPopId(popEntity.getId());
        popInfoDAO.save(popInfoEntity);
    }

    public Page<PopDto> getPop(double lat, double lon, int range, Pageable pageable) {
        double latlonRange[] = DistanceUtil.boundingCoordinates(lat, lon, range);
        Page<PopEntity> popEntityPage = popDAO.getByLatLon(latlonRange[0], latlonRange[1], latlonRange[2], latlonRange[3], pageable);
        List<PopEntity> popEntityList = popEntityPage.getContent();
        List<PopDto> popDtoList = new ArrayList<PopDto>();
        if (!CollectionUtils.isEmpty(popEntityList)) {
            for (PopEntity popEntity : popEntityList) {
                PopDto popDto = new PopDto();
                BeanUtils.copyProperties(popEntity, popDto);
                popDtoList.add(popDto);
            }
        }
        Page<PopDto> popDtoPage = new Page<PopDto>(popDtoList, popEntityPage.getPageable(), popEntityPage.getTotal());
        return popDtoPage;
    }

    public PopInfoDto getPopInfo(long popId) {
        PopInfoEntity popInfoEntity = popInfoDAO.getByPopId(popId);
        PopInfoDto popInfoDto = new PopInfoDto();
        if (popInfoEntity != null) {
            BeanUtils.copyProperties(popInfoEntity, popInfoDto);
        }
        return popInfoDto;
    }

    public void addMessage(PopMessageDto popMessageDto) {
        PopMessageEntity popMessageEntity = new PopMessageEntity();
        BeanUtils.copyProperties(popMessageDto, popMessageEntity);
        popMessageDAO.save(popMessageEntity);
    }

    public Page<PopMessageDto> getMessage(long popId, Pageable pageable) {
        Page<PopMessageEntity> popMessageEntityPage = popMessageDAO.getByPopId(popId, pageable);
        List<PopMessageEntity> popMessageEntityList = popMessageEntityPage.getContent();
        List<PopMessageDto> popMessageDtoList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(popMessageEntityList)) {
            for (PopMessageEntity popMessageEntity : popMessageEntityList) {
                PopMessageDto popMessageDto = new PopMessageDto();
                BeanUtils.copyProperties(popMessageEntity, popMessageDto);
                popMessageDtoList.add(popMessageDto);
            }
        }
        Page<PopMessageDto> popMessageDtoPage = new Page<>(popMessageDtoList, popMessageEntityPage.getPageable(), popMessageEntityPage.getTotal());
        return popMessageDtoPage;
    }

}
