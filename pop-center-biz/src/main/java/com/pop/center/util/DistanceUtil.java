package com.pop.center.util;

/**
 * Created by xugang on 16/8/7.
 */
public class DistanceUtil {
    public static final double radius = 6371000;
    public static final double pi = 3.1415926;
    public static final double angleToRad = pi / 180;//角度到弧度转换值
    public static final double radToangle = 180 / pi;//弧度到角度转换值

    /**
     * 输入经纬度返回距离内的经纬度范围
     *
     * @param lat
     * @param lon
     * @param distance 单位:m
     * @return lat_min lat_max lon_min lon_max
     */
    public static double[] boundingCoordinates(double lat, double lon, int distance) {
        lat = lat * angleToRad;
        lon = lon * angleToRad;  //先换算成弧度
        //计算纬度范围
        double rad_dist = distance / radius;  //计算X米在地球圆周上的弧度
        double lat_min = lat - rad_dist;
        double lat_max = lat + rad_dist;
        //开始计算经度范围
        double lon_t = Math.asin(Math.sin(rad_dist) / Math.cos(lat));
        double lon_min = lon - lon_t;
        double lon_max = lon + lon_t;
        //最后置换成角度进行输出
        lat_min = lat_min * radToangle;
        lat_max = lat_max * radToangle;
        lon_min = lon_min * radToangle;
        lon_max = lon_max * radToangle;
        double result[] = {lat_min, lat_max, lon_min, lon_max};
        return result;
    }
}
