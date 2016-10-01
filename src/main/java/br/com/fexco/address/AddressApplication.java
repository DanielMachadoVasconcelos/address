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
@EnableCaching
@ComponentScan
@EnableAutoConfiguration
@ImportResource("classpath:/spring-bean.config.xml")
public class AddressApplication {

	public static void main(String[] args) {
		SpringApplication.run(AddressApplication.class, args);
	}

	/**
	 * <p>
	 * Caching data with redis server.
	 * 
	 * @TODO: Change this to connect to a private distributed redis server. Used
	 *        a free remote cloud redis server for explanation propurse.
	 * 
	 * @return {@link JedisConnectionFactory}
	 */
	@Bean
	public JedisConnectionFactory redisConnectionFactory() {
		JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
		redisConnectionFactory.setHostName("redis-14138.c9.us-east-1-4.ec2.cloud.redislabs.com");
		redisConnectionFactory.setPort(14138);
		redisConnectionFactory.setPassword("123456"); // change later for helm
		return redisConnectionFactory;
	}

	/**
	 * Simple redis template for configurations propurse.
	 * 
	 * @param cf
	 * @return {@link RedisTemplate}
	 */
	@Bean
	public RedisTemplate<String, Address> redisTemplate(RedisConnectionFactory cf) {
		RedisTemplate<String, Address> redisTemplate = new RedisTemplate<String, Address>();
		redisTemplate.setConnectionFactory(cf);
		return redisTemplate;
	}

	/**
	 * <p>
	 * Chache manager with redis to avoid db consume and rest application
	 * requests.
	 * 
	 * <p>
	 * Required: "Avoid repeated requests to hit the third party API. A proposed
	 * solution is to use an in- memory cache."
	 * 
	 * <p>
	 * 
	 * @TODO: change this mechanism to bulk load on startup for: <br>
	 *        <p>
	 *        
	 *        Make sure the previous requests survive on service restarts (e.g.
	 *        after a new version of your service is deployed).
	 *        A proposed solution involves a long term persistent mechanism,
	 *        that preloads the in-memory cache on startup.
	 * 
	 * @param redisTemplate
	 * @return {@link CacheManager}
	 */
	@Bean
	public CacheManager cacheManager(RedisTemplate<String, Address> redisTemplate) {
		redisTemplate.setEnableDefaultSerializer(Boolean.TRUE);
		RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
		cacheManager.setDefaultExpiration(300);
		cacheManager.initializeCaches();
		cacheManager.setLoadRemoteCachesOnStartup(Boolean.TRUE);
		return cacheManager;
	}

	/**
	 * RestTemplate to consume third party API
	 * 
	 * @param builder
	 * @return {@link RedisTemplate}
	 */
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

}
