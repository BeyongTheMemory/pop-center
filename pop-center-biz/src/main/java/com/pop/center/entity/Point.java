package com.pop.center.entity;

import com.pop.center.dto.pop.PopDto;
import com.pop.center.util.DistanceUtil;

import java.util.Comparator;

/**
 * Created by xugang on 16/9/24.
 */
public class Point implements Comparator {
    private double longitude;
    private double latitude;

    public Point(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public int compare(Object o1, Object o2) {
        PopDto popDto1 = (PopDto)o1;
        PopDto popDto2 = (PopDto)o2;
        double dis1 = DistanceUtil.GetDistance(latitude,longitude,popDto1.getLatitude(),popDto1.getLongitude());
        double dis2 = DistanceUtil.GetDistance(latitude,longitude,popDto2.getLatitude(),popDto2.getLongitude());
        return dis1 > dis2 ? -1:1;//距离倒叙
    }
}
