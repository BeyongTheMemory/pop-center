package com.pop.center.dao.pop;

import com.pop.center.entity.pop.PopMessageEntity;
import com.pop.mybatis.entity.Page;
import com.pop.mybatis.entity.Pageable;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by xugang on 16/8/9.
 */
@Repository
public interface PopMessageDAO {
    public void save(PopMessageEntity popMessageEntity);
    public Page<PopMessageEntity> getByPopId(@Param("popId")long popId,Pageable pageable);
}
