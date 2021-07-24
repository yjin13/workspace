package com.yjin.configuration.exception;

import com.yjin.configuration.http.BaseResponseCode;

/**
 * 기본적인 예외 처리 클래스
 * @author yjin
 */
public class BaseException extends AbstractBaseException {
	
	private static final long serialVersionUID = 8342235231880246631L;
	
	public BaseException() {}
	
	public BaseException(BaseResponseCode responseCode) {
		this.responseCode = responseCode;
	}

	public BaseException(BaseResponseCode responseCode, String[] args) {
		this.responseCode = responseCode;
		this.args = args;
	}
	
}