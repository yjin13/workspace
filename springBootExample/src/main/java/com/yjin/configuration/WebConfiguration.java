package com.yjin.configuration;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.yjin.configuration.servlet.handler.BaseHandlerInterceptor;
import com.yjin.framework.data.web.MySQLPageRequestHandlerMethodArgumentResolver;
import com.yjin.mvc.domain.BaseCodeLabelEnum;

/**
 * @author yjin
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

	@Autowired
	private GlobalConfig config;
	
	/**
	 * 다국어 설정
	 * @return
	 */
	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
		source.setBasename("classpath:/messages/message");
		source.setDefaultEncoding("UTF-8");
		source.setCacheSeconds(60);
		source.setDefaultLocale(Locale.KOREAN);
		source.setUseCodeAsDefaultMessage(true);
		return source;
	}
	
	/**
	 * 인터셉터 빈 등록
	 * @return
	 */
	@Bean
	public BaseHandlerInterceptor baseHandlerInterceptor() {
		return new BaseHandlerInterceptor();
	}
	
	/**
	 * 인터셉터 추가
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(baseHandlerInterceptor());
	}
	
	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		SimpleModule simpleModule = new SimpleModule();
		simpleModule.addSerializer(BaseCodeLabelEnum.class, new BaseCodeLabelEnumJsonSerializer());
		objectMapper.registerModule(simpleModule);
		return objectMapper;
	}
	
	/**
	 * json 빈 등록
	 * @return
	 */
	@Bean
	public MappingJackson2JsonView mappingJackson2JsonView() {
		MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
		jsonView.setContentType(MediaType.APPLICATION_JSON_VALUE);
		jsonView.setObjectMapper(objectMapper());
		return jsonView;
	}
	
	/**
	 * 페이지 리졸버 등록
	 */
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(new MySQLPageRequestHandlerMethodArgumentResolver());
	}
	
	/**
	 * 프로퍼티 환경 관리 빈 등록
	 * @return
	 */
	@Bean
	public GlobalConfig config() {
		return new GlobalConfig();
	}
	
	/**
	 * 브라우저에서 리소스 접근
	 * (url로 업로드 파일 보기)
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String resourcePattern = config.getUploadResourcePath() + "**";
		
		// window 환경
		if(config.isLocal()) {
			registry.addResourceHandler(resourcePattern).addResourceLocations("file:///"+config.getUploadFilePath());
			
		// linux 또는 unix 환경
		} else {
			registry.addResourceHandler(resourcePattern).addResourceLocations("file:"+config.getUploadFilePath());
		}
	}
	
	/**
	 * sitemesh 빈 등록
	 * @return
	 */
	@Bean
	public FilterRegistrationBean<SitemeshConfiguration> sitemeshBean() {
		FilterRegistrationBean<SitemeshConfiguration> bean = new FilterRegistrationBean<SitemeshConfiguration>();
		bean.setFilter(new SitemeshConfiguration());
		return bean;
	}
	
}
