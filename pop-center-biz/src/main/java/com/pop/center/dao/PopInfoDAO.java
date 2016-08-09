package com.pop.center.dao;

import com.pop.center.entity.PopInfoEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xugang on 16/8/8.
 */
@Repository
public interface PopInfoDAO {
    public void save(PopInfoEntity popInfoEntity);
    public PopInfoEntity getByPopId(@Param("popId")long popId);
}
