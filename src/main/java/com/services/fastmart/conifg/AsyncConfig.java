package com.services.fastmart.conifg;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

//@Configuration
//@EnableAsync
public class AsyncConfig {
	
//	@Bean(name="taskExecutor")
//	public Executor taskExecutor() {
//		
//		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//		executor.setCorePoolSize(50);
//		executor.setMaxPoolSize(200);
//		executor.setQueueCapacity(100);
//		executor.setThreadNamePrefix("taskExecutor-");
//		//executor.setKeepAliveSeconds(40);
//		executor.initialize();
//		return executor; 
//	}

}
