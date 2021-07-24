package com.yjin.configuration.http;

/**
 * 응답 상태 코드
 * @author yjin
 */
public enum BaseResponseCode {

	SUCCESS, // 성공
	ERROR, // 에러
	DATA_IS_NULL, // NULL
	VALIDATE_REQUIRED, // 필수 입력
	LOGIN_REQUIRED, // 로그인 필수
	UPLOAD_FILE_IS_NULL, // 파일 NULL
	;

}
