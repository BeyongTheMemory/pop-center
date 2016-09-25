package com.pop.center.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pop.cache.RedisOperate;
import com.pop.center.dao.PopDAO;
import com.pop.center.dao.PopInfoDAO;
import com.pop.center.dao.PopMessageDAO;
import com.pop.center.dto.PopDto;
import com.pop.center.dto.PopInfoDto;
import com.pop.center.dto.PopMessageDto;
import com.pop.center.dto.PopNewDto;
import com.pop.center.entity.Point;
import com.pop.center.entity.PopEntity;
import com.pop.center.entity.PopInfoEntity;
import com.pop.center.entity.PopMessageEntity;
import com.pop.center.enums.RedisKey;
import com.pop.center.service.PopService;
import com.pop.center.util.DistanceUtil;
import com.pop.center.util.Geohash;
import com.pop.mybatis.entity.Page;
import com.pop.mybatis.entity.Pageable;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private RedisOperate redis;

    /*获取的geohash多少位，位数越长，精度越准*/
    private static final int geoHashLenth = 6;

    public void save(PopNewDto popNewDto) {
        PopEntity popEntity = new PopEntity();
        BeanUtils.copyProperties(popNewDto, popEntity);
		/*获取geohash*/
        String geohash = Geohash.encode(popNewDto.getLatitude(), popNewDto.getLongitude()).substring(0, geoHashLenth);
        popEntity.setGeoHash(geohash);
        popDAO.save(popEntity);
        PopInfoEntity popInfoEntity = new PopInfoEntity();
        BeanUtils.copyProperties(popNewDto, popInfoEntity);
        popInfoEntity.setPopId(popEntity.getId());
        popInfoDAO.save(popInfoEntity);
        //todo:异步刷新缓存
        PopDto popDto = new PopDto();
        BeanUtils.copyProperties(popEntity,popDto);
        addPopInCache(geohash,popDto);
        //todo:漂浮泡泡异步刷新缓存,漂浮泡泡只缓存10个
        if(popDto.getType() == 1){

        }
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

    /**
     * 根据经纬度获取pop
     * @param lat
     * @param lon
     * @return
     */
    public List<PopDto> getPop(double lat,double lon){
        List<PopDto> popDtos = new ArrayList<>();
        String geoHash = Geohash.encode(lat,lon);
        //搜索所有的框
        List<String> nearGeoHashs = Geohash.getGeoHashExpand(geoHash);
        for(int i = 0;i<9;i++){//遍历所有框
            String nearGeoHash = nearGeoHashs.get(i);
            String popListString = redis.getStringByKey(String.format(RedisKey.popList, nearGeoHash));
            Point point = new Point(lat,lon);
            if(!StringUtils.isEmpty(popListString)){
                popDtos.addAll(JSON.parseArray(popListString,PopDto.class));//同一个框里距离在0.6km以内
                //排序
                Collections.sort(popDtos,point);
                if (popDtos.size() > 10){
                    return popDtos.subList(0,9);
                }
                if (popDtos.size() > 5){
                    return popDtos;
                }
            }
        }
        //搜索缓存不足5个则查询数据库
        popDtos.clear();
        List<PopEntity> popEntityList = popDAO.getByGeoHashs(nearGeoHashs);
        if(!CollectionUtils.isEmpty(popEntityList)) {
           for (PopEntity popEntity : popEntityList) {
               PopDto popDto = new PopDto();
               BeanUtils.copyProperties(popEntity,popDto);
               popDtos.add(popDto);
           }
            //异步更新缓存
             if(popDtos.size() > 10){
                 popDtos = popDtos.subList(0,9);
             }
            return popDtos;
       }
       else{
            //查询失败从redis查询漂浮泡泡
            //从数据库查询漂浮泡泡
        }

        return popDtos;
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


    public void addPopInCache(String geoHash,PopDto popDto){
        String popListString = redis.getStringByKey(String.format(RedisKey.popList,geoHash));
        if(StringUtils.isEmpty(popListString)){
            //先查出来
            List<PopDto> popDtos = new ArrayList<>();
            List<PopEntity> popEntities = popDAO.getByGeoHash(geoHash);
            if(!CollectionUtils.isEmpty(popEntities)){
                for (PopEntity popEntity:popEntities){
                    PopDto popDtoTemp = new PopDto();
                    BeanUtils.copyProperties(popEntity,popDtoTemp);
                    popDtos.add(popDtoTemp);
                }
            }
            popDtos.add(popDto);
            redis.set(String.format(RedisKey.popList,geoHash), JSONObject.toJSONString(popDtos),RedisKey.POPLIST_TTL);
        }else {
            List<PopDto> popDtos = JSON.parseArray(popListString, PopDto.class);
            popDtos.add(popDto);
            redis.set(String.format(RedisKey.popList,geoHash), JSONObject.toJSONString(popDtos),RedisKey.POPLIST_TTL);
        }
    }

}
