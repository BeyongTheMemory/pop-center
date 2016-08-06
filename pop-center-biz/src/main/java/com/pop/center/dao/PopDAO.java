package com.pop.center.dao;

import com.pop.center.entity.PopEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by xugang on 16/8/6.
 */
@Repository
public interface PopDAO {
    public void save(PopEntity popEntity);

}
