package com.pop.center.enums;

/**
 * Created by xugang on 16/9/23.
 */
public class RedisKey {
    public static final String popList = "pop:list:geohash:%s";
    public static final String popInfo = "pop:info:id:%s";
    public static final String floatPopList = "pop:float";
    public static final int POPLIST_TTL = 24 * 60 * 60;
    public static final int POPINFO_TTL = 60 * 60;
}
