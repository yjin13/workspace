package com.yjin.mvc.domain;

/**
 * 메뉴 종류 enum
 * @author yjin
 */
public enum MenuType {
	
	community(BoardType.COMMUNITY, "menu.community", "/community"),
	notice(BoardType.NOTICE, "menu.notice", "notice"),
	faq(BoardType.FAQ, "menu.faq", "/faq"),
	inquiry(BoardType.INQUIRY, "menu.inquiry", "/inquiry"),
	;
	
	private BoardType boardType;
	private String menuCode;
	private String url;
	
	private MenuType(BoardType boardType, String menuCode, String url) {
		this.boardType = boardType;
		this.menuCode = menuCode;
		this.url = url;
	}

	public BoardType getBoardType() {
		return boardType;
	}
	
	public String getMenuCode() {
		return menuCode;
	}
	
	public String getUrl() {
		return url;
	}

}
