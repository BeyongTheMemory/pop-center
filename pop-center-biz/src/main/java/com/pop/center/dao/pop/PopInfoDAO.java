package com.pop.center.dao.pop;

import com.pop.center.entity.pop.PopInfoEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by xugang on 16/8/8.
 */
@Repository
public interface PopInfoDAO {
    public void save(PopInfoEntity popInfoEntity);
    public PopInfoEntity getByPopId(@Param("popId")long popId);
    void addLookNum(@Param("id") long id);

}
