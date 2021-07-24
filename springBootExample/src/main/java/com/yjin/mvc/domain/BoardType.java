package com.yjin.mvc.domain;

/**
 * 게시판 종류 enum
 * @author yjin
 */
public enum BoardType implements BaseCodeLabelEnum {
	
	NOTICE("공지사항"),
	FAQ("자주묻는질문"),
	INQUIRY("1:1문의"),
	;
	
	private String code;
	private String label;
	
	private BoardType(String label) {
		this.code = name(); // 기본 제공 메소드
		this.label = label;
	}

	@Override
	public String code() {
		return code;
	}

	@Override
	public String label() {
		return label;
	}

}
