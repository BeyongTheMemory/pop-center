package com.pop.center.dao;

import com.pop.center.entity.PopEntity;
import com.pop.mybatis.entity.Page;
import com.pop.mybatis.entity.Pageable;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xugang on 16/8/6.
 */
@Repository
public interface PopDAO {
    public void save(PopEntity popEntity);
    public Page<PopEntity> getByLatLon(@Param("latMin")double latMin,@Param("latMax")double latMax,@Param("lonMin")double lonMin,@Param("lonMax")double lonMax,Pageable pageable);
    public List<PopEntity> getByGeoHash(@Param("geoHash")String geoHash);
}
