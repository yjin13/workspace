package com.yjin.framework.data.domain;

import lombok.Data;

/**
 * 페이지 요청 정보와 파라미터 정보
 * @author yjin
 * @param <T>
 */
@Data
public class PageRequestParameter<T> {
	
	// 페이징 정보
	private MySQLPageRequest pageRequest;
	// 부가적인 검색 조건을 Generic으로 선언
	private T parameter;
	
	public PageRequestParameter(MySQLPageRequest pageRequest, T parameter) {
		this.pageRequest = pageRequest;
		this.parameter = parameter;
	}
	
}
