package com.yjin.mvc.parameter;

import java.util.List;

import com.yjin.mvc.domain.BoardType;

import lombok.Data;

/**
 * 게시물 검색 파라미터
 * @author yjin
 */
@Data
public class BoardSearchParameter {
	
	private String keyword;
	private List<BoardType> boardTypes;
	
	public BoardSearchParameter() {}
}
