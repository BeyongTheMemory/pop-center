package com.pop.center.async;

import com.pop.center.dto.PopDto;

import java.util.List;

/**
 * Created by xugang on 16/9/25.
 * 批量缓存popEntity使用的实体类
 */
public class PopListCacheDto {
    private String geoHash;
    private List<PopDto> popDtoList;

    public PopListCacheDto(String geoHash, List<PopDto> popDtoList) {
        this.geoHash = geoHash;
        this.popDtoList = popDtoList;
    }

    public String getGeoHash() {
        return geoHash;
    }

    public void setGeoHash(String geoHash) {
        this.geoHash = geoHash;
    }

    public List<PopDto> getPopDtoList() {
        return popDtoList;
    }

    public void setPopDtoList(List<PopDto> popDtoList) {
        this.popDtoList = popDtoList;
    }
}
