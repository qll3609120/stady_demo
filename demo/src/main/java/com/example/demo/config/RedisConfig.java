package com.example.demo.config;

import com.example.demo.util.RedisObjectSerializer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.lang.reflect.Method;

/**
 * Created by evan.qi on 2017/6/16.
 * redis 配置
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport{

	@Override
	public KeyGenerator keyGenerator() {

		System.out.println("RedisCacheConfig.keyGenerator()");

		return new KeyGenerator(){

			@Override
			public Object generate(Object o, Method method, Object... objects) {

				// This willgenerate a unique key of the class name, the method name

				//and allmethod parameters appended.

				StringBuilder sb = new StringBuilder();

				sb.append(o.getClass().getName());

				sb.append(method.getName());

				for (Object obj : objects) {

					sb.append(obj.toString());

				}

				System.out.println("keyGenerator=" + sb.toString());

				return sb.toString();

			}
		};

	}

	@Bean
	public CacheManager cacheManager(RedisTemplate redisTemplate){
		return new RedisCacheManager(redisTemplate);
	}

	@Bean
	public RedisTemplate redisTemplate(RedisConnectionFactory factory){
		StringRedisTemplate redisTemplate = new StringRedisTemplate();
		redisTemplate.setConnectionFactory(factory);
		//序列化方式
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

		jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
		RedisObjectSerializer redisObjectSerializer = new RedisObjectSerializer();
		redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
		redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}
}
