package com.yjin.mvc.domain;

/**
 * 메뉴 종류 enum
 * @author yjin
 */
public enum MenuType {
	
	community(BoardType.COMMUNITY),
	notice(BoardType.NOTICE),
	faq(BoardType.FAQ),
	inquiry(BoardType.INQUIRY),
	;
	
	private BoardType boardType;
	
	private MenuType(BoardType boardType) {
		this.boardType = boardType;
	}

}
