package com.yjin.scheduler;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ExampleScheduler {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	/*
	 * cron에 5초마다 동작
	 * @Scheduled(cron = "*\/5 * * * * *")
	 */
	/**
	 * bean에 등록된 cron 맵핑 (서버마다 다르게 구성)
	 */
	@Scheduled(cron = "#{@schedulerCronExample}")
	public void schedule() {
		logger.info("schedule 동작 중: {}", Calendar.getInstance().getTime());
	}
	
}
