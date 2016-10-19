package com.pop.center.dao.pop;

import com.pop.center.entity.pop.PopEntity;
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
    void save(PopEntity popEntity);

    Page<PopEntity> getByLatLon(@Param("latMin") double latMin, @Param("latMax") double latMax, @Param("lonMin") double lonMin, @Param("lonMax") double lonMax, Pageable pageable);

    List<PopEntity> getByGeoHash(@Param("geoHash") String geoHash);

    List<PopEntity> getByGeoHashs(@Param("geoHashs") List<String> geoHashs);

    List<PopEntity> getFloatPop();

    void deleteById(@Param("id") long id);

    PopEntity getById(@Param("id") long id);

}
