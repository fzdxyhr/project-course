package com.yhr.course.course.config;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author yhr
 * @version latest
 * @date 2019/3/11
 */
public class WhiteListCache {

    public static Cache<String, List> cache = CacheBuilder.newBuilder().expireAfterAccess(30, TimeUnit.MINUTES)// 缓存过期时间和redis缓存时长一样
            .maximumSize(1000).build();
}
