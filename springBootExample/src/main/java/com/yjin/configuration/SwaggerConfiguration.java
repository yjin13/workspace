package com.yjin.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger 설정
 * http://localhost:8080/swagger-ui.html
 * @author yjin
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	@Bean
	public Docket docket() {
		ApiInfoBuilder apiInfo = new ApiInfoBuilder();
		apiInfo.title("API 서버 문서");
		apiInfo.description("API 서버 문서입니다.");
		
		Docket doctet = new Docket(DocumentationType.SWAGGER_2);
		doctet.apiInfo(apiInfo.build());
		
		ApiSelectorBuilder apis = doctet.select().apis(RequestHandlerSelectors.basePackage("com.yjin.mvc.controller"));
		apis.paths(PathSelectors.ant("/**"));
		
		return apis.build();
	}
	
}
