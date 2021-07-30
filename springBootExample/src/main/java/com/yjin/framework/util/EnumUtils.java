package com.yjin.framework.util;

import org.apache.commons.lang3.ObjectUtils;

import com.yjin.mvc.domain.BaseCodeLabelEnum;

public class EnumUtils {

	/**
	 * 선택된 값이면 true 반환
	 * @param values 파라미터로 넘어온 선택된 값
	 * @param codeEnum 현재 출력하는 code
	 * @return
	 */
	public static boolean isSelected(BaseCodeLabelEnum[] values, BaseCodeLabelEnum codeEnum) {
		if(ObjectUtils.isEmpty(values)) {
			return false;
		}
		for(BaseCodeLabelEnum value : values) {
			if(value.code().equals(codeEnum.code())) {
				return true;
			}
		}
		return false;
	}
	
}
