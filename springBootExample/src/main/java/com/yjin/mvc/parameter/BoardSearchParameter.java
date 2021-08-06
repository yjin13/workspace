package com.yjin.mvc.parameter;

import com.yjin.mvc.domain.BoardType;

import lombok.Data;

/**
 * 게시물 검색 파라미터
 * @author yjin
 */
@Data
public class BoardSearchParameter {
	
	private String keyword;
	private BoardType boardType;
	private BoardType[] boardTypes;
	
	public BoardSearchParameter() {}
}
