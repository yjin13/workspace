package com.yjin.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulerCronConfiguration {
	
	@Autowired
	private GlobalConfig config;
	
	/**
	 * 사용할 cron 빈 등록 (변수 값이 등록)
	 * @return
	 */
	@Bean
	public String schedulerCronExample() {
		return config.getSchedulerCronExample();
	}

}
