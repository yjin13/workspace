package com.yjin.mvc.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yjin.mvc.domain.ExampleRequestBodyUser;
import com.yjin.mvc.parameter.ExampleParameter;

/**
 * 파라미터 전달 정리 (jsp 페이지 확인)
 * @author yjin
 */
@Controller
@RequestMapping("/example")
public class ExampleController {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * get방법1. @RequestParam
	 * http://localhost:8080/example/ex1?id=test&code=1234
	 * @param id
	 * @param code
	 * @param model
	 */
	@GetMapping("/ex1")
	public void example1(@RequestParam("id") String id, @RequestParam String code, Model model) {
		model.addAttribute("id", id);
		model.addAttribute("code", code);
	}
	
	/**
	 * get방법2. Map
	 * http://localhost:8080/example/ex2?id=test&code=1234
	 * @param paramMap
	 * @param model
	 */
	@GetMapping("/ex2")
	public void example2(@RequestParam Map<String, Object> paramMap, Model model) {
		model.addAttribute("paramMap", paramMap);
	}
	
	/**
	 * get방법3. class
	 * http://localhost:8080/example/ex3?id=test&code=1234
	 * @param paramMap
	 * @param model
	 */
	@GetMapping("/ex3")
	public void example3(ExampleParameter parameter, Model model) {
		model.addAttribute("parameter", parameter);
	}
	
	/**
	 * get방법4. @PathVariable (return view 지정)
	 * http://localhost:8080/example/ex4/test/1234
	 * @param id
	 * @param code
	 * @param model
	 */
	@GetMapping("/ex4/{id}/{code}")
	public String example4(@PathVariable String id, @PathVariable String code, Model model) {
		model.addAttribute("id", id);
		model.addAttribute("code", code);
		return "/example/ex4";
	}
	
	/**
	 * get방법5. String[]
	 * http://localhost:8080/example/ex5?ids=12&ids=34
	 * @param ids
	 * @param model
	 */
	@GetMapping("/ex5")
	public void example5(@RequestParam String[] ids, Model model) {
		model.addAttribute("ids", ids);
	}
	
	/**
	 * post방법. json (화면 jquery)
	 * http://localhost:8080/example/ex6
	 */
	@GetMapping("/ex6")
	public void example6() {}
	
	/**
	 * post방법1. json
	 * @param requestBody
	 */
	@PostMapping("/ex6/json")
	@ResponseBody
	public Map<String, Object> example6(@RequestBody Map<String, Object> requestBody) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", requestBody);
		logger.info("requestBody: {}", requestBody);
		return resultMap;
	}
	
	/**
	 * post방법2. class
	 * @param requestBody
	 */
	@PostMapping("/ex6/class")
	@ResponseBody
	public Map<String, Object> example6(@RequestBody ExampleRequestBodyUser requestBody) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", requestBody);
		logger.info("requestBody: {}", requestBody);
		return resultMap;
	}
	
}
