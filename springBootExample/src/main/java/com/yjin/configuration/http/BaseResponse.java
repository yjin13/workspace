package com.yjin.configuration.http;

import lombok.Data;

/**
 * 공통 응답 클래스
 * @author yjin
 * @param <T>
 */
@Data
public class BaseResponse<T> {

	private BaseResponseCode code;
	private String message;
	private T data;
	
	/**
	 * 성공
	 * @param data
	 */
	public BaseResponse(T data) {
		this.code = BaseResponseCode.SUCCESS;
		this.data = data;
	}

	/**
	 * 예외 처리
	 * @param code
	 * @param message
	 */
	public BaseResponse(BaseResponseCode code, String message) {
		this.code = code;
		this.message = message;
	}
	
}
