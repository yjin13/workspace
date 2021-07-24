package com.yjin.configuration.servlet.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.yjin.configuration.exception.BaseException;
import com.yjin.configuration.http.BaseResponseCode;
import com.yjin.framework.web.bind.annotation.RequestConfig;

/**
 * 인터셉터 클래스 (컨트롤러 전 후 작업)
 * @author yjin
 */
public class BaseHandlerInterceptor implements HandlerInterceptor {
	Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 컨트롤러 메소드 호출 전
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.info("preHandle requestURI : {}", request.getRequestURI());
		
		// 로그인 체크
		if(handler instanceof HandlerMethod) { 
			HandlerMethod handlerMethod = (HandlerMethod) handler; // 요청 url이 컨트롤러 메소드에 맵핑된 경우 가져오기
			logger.info("handlerMethod: {}", handlerMethod);
			// 맵핑된 메소드에 선언된 어노테이션 가져오기
			RequestConfig requestConfig = handlerMethod.getMethodAnnotation(RequestConfig.class);
			if(requestConfig != null) {
				// 로그인 필수
				if(requestConfig.loginCheck()) {
					throw new BaseException(BaseResponseCode.LOGIN_REQUIRED, new String[] {request.getRequestURI()});
				}
			}
		}
		return true;
	}

	/**
	 * 컨트롤러 메소드 호출 후
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		logger.info("postHandle requestURI : {}", request.getRequestURI());
	}

}
