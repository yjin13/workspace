package com.yjin.mvc.parameter;

import com.yjin.mvc.domain.BoardType;

import lombok.Data;

@Data
public class BoardParameter {

	private int boardSeq;
	private BoardType boardType;
	private String title;
	private String contents;
	private boolean delYn;
	
	public BoardParameter() {}
	
	public BoardParameter(String title, String contents) {
		this.title = title;
		this.contents = contents;
	}
	
	public BoardParameter(BoardType boardType, String title, String contents, boolean delYn) {
		this.boardType = boardType;
		this.title = title;
		this.contents = contents;
		this.delYn = delYn;
	}
}
