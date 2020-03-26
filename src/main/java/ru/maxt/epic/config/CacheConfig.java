package ru.maxt.epic.config;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {
    @Bean
    public CacheManager cacheManager(){
        CacheManager ccm =  new CaffeineCacheManager("elvls");
        return ccm;
    }

    @Bean
    public Cache elvlCache(CacheManager cacheManager){
        return cacheManager.getCache("elvls");
    }
}
