package com.yjin.configuration.exception;

import com.yjin.configuration.http.BaseResponseCode;

/**
 * 커스텀 예외 처리 클래스
 * (상황에 맞게 구현하기 위해 추상화 처리)
 * @author yjin
 */
public abstract class AbstractBaseException extends RuntimeException {
	
	private static final long serialVersionUID = 8342235231880246631L;
	
	protected BaseResponseCode responseCode;
	protected Object[] args;
	
	public AbstractBaseException() {}
	
	public AbstractBaseException(BaseResponseCode responseCode) {
		this.responseCode = responseCode;
	}
	
	public BaseResponseCode getResponseCode() {
		return responseCode;
	}
	
	public Object[] getArgs() {
		return args;
	}
	
}