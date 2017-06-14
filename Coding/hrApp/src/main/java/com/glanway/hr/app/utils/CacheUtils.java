package com.glanway.hr.app.utils;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

/**
 * 缓存工具类
 */
public abstract class CacheUtils {
    private static CacheManager cacheManager;

    /**
     * 在给定缓存中获取键为 Key 的对象
     *
     * @param cacheName 缓存名称
     * @param key       Key
     * @param <V>       缓存对象类型
     * @return 缓存的值
     */
    @SuppressWarnings("unchecked")
    public static <V> V get(String cacheName, String key) {
        Cache cache = getRequiredCache(cacheName);
        Cache.ValueWrapper wrapper = cache.get(key);
        return (V) (null != wrapper ? wrapper.get() : null);
    }

    /**
     * 将给定的 value 使用 key 缓存到给定缓存
     *
     * @param cacheName 缓存名称
     * @param key       Key
     * @param value     要缓存的对象
     */
    public static void put(String cacheName, String key, Object value) {
        Cache cache = getRequiredCache(cacheName);
        if (null == key) {
            throw new NullPointerException("key is null");
        }
        if (null == value) {
            cache.evict(key);
        } else {
            cache.put(key, value);
        }
    }

    /**
     * 从缓存 cacheName 中移除键为 Key 的缓存值
     *
     * @param cacheName 缓存名称
     * @param key       Key
     */
    public static void evict(String cacheName, String key) {
        Cache cache = getRequiredCache(cacheName);
        cache.evict(key);
    }

    /**
     * 清空给定名称的缓存
     *
     * @param cacheName 缓存名称
     */
    public static void clear(String cacheName) {
        Cache cache = getRequiredCache(cacheName);
        cache.clear();
    }

    /**
     * 获取必须的 Cache, 如果不存在则跑出 {@link IllegalStateException}
     *
     * @return {@link Cache}
     */
    public static Cache getRequiredCache(String cacheName) {
        CacheManager mgr = getRequiredCacheManager();
        Cache cache = mgr.getCache(cacheName);
        if (null == cache) {
            throw new IllegalStateException("No Cache found: no Cache(" + cacheName + ") configure ?");
        }
        return cache;
    }

    /**
     * 获取必须的 CacheManager, 如果不存在则跑出 {@link IllegalStateException}
     *
     * @return {@link CacheManager}
     */
    public static CacheManager getRequiredCacheManager() {
        CacheManager mgr = getCacheManager();
        if (null == mgr) {
            throw new IllegalStateException("No CacheManager found: no CacheManager registered?");
        }
        return mgr;
    }

    public static CacheManager getCacheManager() {
        return cacheManager;
    }

    public static void setCacheManager(CacheManager cacheManager) {
        CacheUtils.cacheManager = cacheManager;
    }

    private CacheUtils() {
    }
}
