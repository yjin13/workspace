package com.yjin.configuration;

import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * 로컬/개발/운영 환경에서 설정 및 프로퍼티 파일 관리 (클래스 활용)
 * (이 외에도 @value 또는 properties, yml 방법 존재)
 * 
 * [Run Configurations] > [Profile] > local / dev / prod 변경
 * @author yjin
 */
public class GlobalConfig {
	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	private String uploadFilePath;
	private String uploadResourcePath;
	private String schedulerCronExample;
	
	private boolean local;
	private boolean dev;
	private boolean prod;
	
	/**
	 * 의존성 주입이 이루어진 후 초기화
	 */
	@PostConstruct
	public void init() {
		logger.info("init");
		String[] activeProfiles = context.getEnvironment().getActiveProfiles();
		String activeProfile = "local";
		if(ObjectUtils.isNotEmpty(activeProfiles)) {
			activeProfile = activeProfiles[0];
		}
		// 프로퍼티 파일 읽기
		String resourcePath = String.format("classpath:/globals/global-%s.properties", activeProfile);
		
		try {
			Resource resource = resourceLoader.getResource(resourcePath);
			Properties properties = PropertiesLoaderUtils.loadProperties(resource);
			
			// 프로퍼티의 속성 저장
			this.uploadFilePath = properties.getProperty("uploadFile.path");
			this.uploadResourcePath = properties.getProperty("uploadFile.resourcePath");
			this.schedulerCronExample = properties.getProperty("scheduler.cron.example");
			this.local = activeProfile.equals("local");
			this.dev = activeProfile.equals("dev");
			this.prod = activeProfile.equals("prod");
		} catch(Exception e) {
			logger.error("e", e);
		}
	}
	
	/**
	 * 파일 업로드 경로 가져오기
	 * @return
	 */
	public String getUploadFilePath() {
		return uploadFilePath;
	}
	
	/**
	 * 리소스 경로 가져오기
	 * @return
	 */
	public String getUploadResourcePath() {
		return uploadResourcePath;
	}
	
	/**
	 * 스케줄러 가져오기
	 * @return
	 */
	public String getSchedulerCronExample() {
		return schedulerCronExample;
	}
	
	/**
	 * 로컬서버인지 확인
	 * @return
	 */
	public boolean isLocal() {
		return local;
	}

	/**
	 * 개발서버인지 확인
	 * @return
	 */
	public boolean isDev() {
		return dev;
	}

	/**
	 * 운영서버인지 확인
	 * @return
	 */
	public boolean isProd() {
		return prod;
	}
	
}
