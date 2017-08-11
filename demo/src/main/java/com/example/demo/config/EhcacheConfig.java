package com.example.demo.config;

import net.sf.ehcache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by evan.qi on 2017/7/14.
 */
@Configuration
@EnableCaching
public class EhcacheConfig {


	public static Logger logger = LoggerFactory.getLogger(EhcacheConfig.class);

	@Bean
	public EhCacheManagerFactoryBean ehCacheManagerFactoryBean(){
		logger.info("s%","begin factory");
		EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
		ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("conf/ehcache.xml"));
		ehCacheManagerFactoryBean.setShared(true);
		return ehCacheManagerFactoryBean;
}
	/*@Bean
	public CacheManager ehCacheCacheManager(){
		logger.info("%s构建cache","begin");
		EhCacheManagerFactoryBean bean = ehCacheManagerFactoryBean();
		return bean.getObject();
	}*/


}
