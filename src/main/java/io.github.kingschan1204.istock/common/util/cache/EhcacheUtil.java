package io.github.kingschan1204.istock.common.util.cache;

import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Component;

/**
 *
 * @author chenguoxiang
 * @create 2018-07-10 15:02
 **/
@Slf4j
@Component
public class EhcacheUtil {

    @Autowired
    EhCacheCacheManager ehCacheCacheManager;

    /**
     * 得到一个缓存的值
     * @param cacheName 缓存名字
     * @param key
     * @return
     */
    public Object getKey(String cacheName,String key){
        Element em =ehCacheCacheManager.getCacheManager().getCache(cacheName).get(key);
        return null==em?null:em.getObjectValue();
    }



    /**
     * 查询指定缓存是否包含key
     * @param cacheName
     * @param key
     * @return
     */
    public boolean contrainKey(String cacheName,String key){
        Cache cache= ehCacheCacheManager.getCacheManager().getCache(cacheName);
        return cache.isKeyInCache(key);
    }

    /**
     * 向缓存中写入一个key
     * @param cacheName
     * @param key
     * @param value
     */
    public void addKey(String cacheName,String key,Object value){
        ehCacheCacheManager.getCacheManager().getCache(cacheName).put(new Element(key,value));
        log.info("缓存{}新增{}值{}",cacheName,key,value);
    }

    /**
     * 得到一个缓存里key 的数量
     * @param cacheName
     * @return
     */
    public int getCacheTotalKeys(String cacheName){
        Cache cache=ehCacheCacheManager.getCacheManager().getCache(cacheName);
        return null==cache?0:cache.getKeys().size();
    }




}
