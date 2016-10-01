package br.com.fexco.address;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.client.RestTemplate;

import br.com.fexco.address.model.Address;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableCaching
@ImportResource("classpath:/spring-bean.config.xml")
public class AddressApplication {

	public static void main(String[] args) {
		SpringApplication.run(AddressApplication.class, args);
	}

	@Bean
	public JedisConnectionFactory redisConnectionFactory() {
		JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
		redisConnectionFactory.setHostName("redis-14138.c9.us-east-1-4.ec2.cloud.redislabs.com");
		redisConnectionFactory.setPort(14138);
		redisConnectionFactory.setPassword("123456"); // change later for helm
		return redisConnectionFactory;
	}

	@Bean
	public RedisTemplate<String, Address> redisTemplate(RedisConnectionFactory cf) {
		RedisTemplate<String, Address> redisTemplate = new RedisTemplate<String, Address>();
		redisTemplate.setConnectionFactory(cf);
		return redisTemplate;
	}

	@Bean
	public CacheManager cacheManager(RedisTemplate<String, Address> redisTemplate) {
		redisTemplate.setEnableDefaultSerializer(Boolean.TRUE);
		RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
		cacheManager.setDefaultExpiration(300);
		cacheManager.initializeCaches();
		cacheManager.setLoadRemoteCachesOnStartup(Boolean.TRUE);
		return cacheManager;
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		
		return builder.build();
	}

}
