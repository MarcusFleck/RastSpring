package com.demorest.config;

import com.demorest.tools.KeyGeneratorByMethodName;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.MemoryUnit;
import net.sf.ehcache.config.SizeOfPolicyConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.interceptor.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableCaching
public class EhCacheConfig implements CachingConfigurer {

    private CacheConfiguration defaultEhCacheProfileConfig() {
        return new CacheConfiguration("Artists", 0)
                .maxBytesLocalHeap(1000, MemoryUnit.MEGABYTES)
                .eternal(false)
                .timeToIdleSeconds(3600)
                .timeToLiveSeconds(3600)
                .memoryStoreEvictionPolicy("LRU")
                .sizeOfPolicy(new SizeOfPolicyConfiguration().maxDepth(5000))
                .statistics(true);
    }

    @Bean(destroyMethod = "shutdown")
    public net.sf.ehcache.CacheManager ehCacheManager() {
        net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();
        config.addCache(defaultEhCacheProfileConfig());

        return net.sf.ehcache.CacheManager.newInstance(config);
    }

    @Bean
    @Override
    public CacheManager cacheManager() {
        System.out.println("oi");
        return new EhCacheCacheManager(ehCacheManager());
    }

    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        System.out.println("sddss");
        return new KeyGeneratorByMethodName();
    }

    @Bean
    @Override
    public CacheResolver cacheResolver() {
        System.out.println("jirombetis");
        SimpleCacheResolver simpleCacheResolver = new SimpleCacheResolver();
        simpleCacheResolver.setCacheManager(cacheManager());
        return simpleCacheResolver;
    }

    @Bean
    @Override
    public CacheErrorHandler errorHandler() {
        System.out.println("rtyu");
        return new SimpleCacheErrorHandler();
    }
}