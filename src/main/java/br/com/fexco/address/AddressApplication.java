package br.com.fexco.address;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;

import br.com.fexco.address.model.Address;
import br.com.fexco.address.repo.AddressRepository;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableCaching
@ImportResource("classpath:/spring-bean.config.xml")
public class AddressApplication {

	public static void main(String[] args) {
		SpringApplication.run(AddressApplication.class, args);
	}

	
	@Autowired
	private AddressRepository addressApplication;
	
	@Bean
	public CacheManager cacheManager() {
		GuavaCacheManager cacheManager = new GuavaCacheManager("address");
		CacheBuilder<Object, Object> cacheBuilder = CacheBuilder.newBuilder().maximumSize(3_000).expireAfterWrite(1,
				TimeUnit.DAYS);
		
		cacheManager.setCacheBuilder(cacheBuilder);
		return cacheManager;
	}

}
