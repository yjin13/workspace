package com.yjin.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yjin.mvc.domain.BoardType;
import com.yjin.mvc.parameter.BoardSearchParameter;

/**
 * 
 * @author yjin
 */
@Controller
@RequestMapping("/example/taglib")
public class ExampleTaglibController {

	/**
	 * 태그 라이브러리 사용 jsp
	 * @param parameter
	 * @param model
	 */
	@RequestMapping("/search")
	public void search(BoardSearchParameter parameter, Model model) {
		model.addAttribute("boardTypes", BoardType.values());
		model.addAttribute("parameter", parameter);
	}
	
}
