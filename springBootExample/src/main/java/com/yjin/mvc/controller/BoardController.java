package com.yjin.mvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yjin.configuration.exception.BaseException;
import com.yjin.configuration.http.BaseResponse;
import com.yjin.configuration.http.BaseResponseCode;
import com.yjin.framework.data.domain.MySQLPageRequest;
import com.yjin.framework.data.domain.PageRequestParameter;
import com.yjin.framework.web.bind.annotation.RequestConfig;
import com.yjin.mvc.domain.Board;
import com.yjin.mvc.domain.MenuType;
import com.yjin.mvc.parameter.BoardParameter;
import com.yjin.mvc.parameter.BoardSearchParameter;
import com.yjin.mvc.service.BoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 게시판 Controller
 * @author yjin
 */
@Controller
@Api(tags = "게시판 API")
public class BoardController {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private FileController fileController;

	/**
	 * 목록 리턴
	 * @param parameter
	 * @param pageRequest
	 * @return
	 */
	@GetMapping("/{menuType}")
	@ApiOperation(value = "목록 조회", notes = "게시물 전체 목록을 조회할 수 있습니다.")
	public String list(@PathVariable MenuType menuType, BoardSearchParameter parameter, MySQLPageRequest pageRequest, Model model) {
		logger.info("pageRequest: {}", pageRequest);
		parameter.setBoardType(menuType.getBoardType());
		PageRequestParameter<BoardSearchParameter> pageRequestParameter = new PageRequestParameter<BoardSearchParameter>(pageRequest, parameter);
		
		List<Board> boardList = boardService.getList(pageRequestParameter);
		model.addAttribute("boardList", boardList);
		model.addAttribute("menuType", menuType);
		
		return "/board/list";
	}
	
	/**
	 * 목록 리턴 (json)
	 * @param parameter
	 * @param pageRequest
	 * @return
	 */
	@GetMapping("/board/{menuType}")
	@ResponseBody
	@ApiOperation(value = "목록 조회", notes = "게시물 전체 목록을 조회할 수 있습니다.")
	public BaseResponse<List<Board>> getList(@PathVariable MenuType menuType, @ApiParam BoardSearchParameter parameter, @ApiParam MySQLPageRequest pageRequest) {
		logger.info("pageRequest: {}", pageRequest);

		parameter.setBoardType(menuType.getBoardType());
		PageRequestParameter<BoardSearchParameter> pageRequestParameter = new PageRequestParameter<BoardSearchParameter>(pageRequest, parameter);
		return new BaseResponse<List<Board>>(boardService.getList(pageRequestParameter));
	}
	
	/**
	 * 상세 정보 리턴
	 * @param boardSeq
	 * @return
	 */
	@GetMapping("/{menuType}/{boardSeq}")
	@ApiOperation(value = "상세 조회", notes = "게시물 번호에 해당하는 상세 정보를 조회할 수 있습니다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "boardSeq", value = "게시물 번호", example = "1")
	})
	public String detail(@PathVariable MenuType menuType, @PathVariable int boardSeq, Model model) {
		Board board = boardService.get(boardSeq);
		if(board == null) {
			throw new BaseException(BaseResponseCode.DATA_IS_NULL, new String[] {"게시물"});
		}
		model.addAttribute("board", board);
		model.addAttribute("menuType", menuType);
		return "/board/detail";
	}
	
	/**
	 * 등록 화면  
	 * @param parameter
	 * @param model
	 */
	@GetMapping("/{menuType}/form")
	@RequestConfig(loginCheck = false)
	public String form(@PathVariable MenuType menuType, BoardParameter parameter, Model model) {
		if(parameter.getBoardSeq() > 0) {
			Board board = boardService.get(parameter.getBoardSeq());
			model.addAttribute("board", board);
		}
		model.addAttribute("parameter", parameter);
		model.addAttribute("menuType", menuType);
		
		return "/board/form";
	}
	
	/**
	 * 수정 화면  
	 * @param parameter
	 * @param model
	 */
	@GetMapping("/{menuType}/edit/{boardSeq}")
	@RequestConfig(loginCheck = false)
	public String edit(@PathVariable MenuType menuType, @PathVariable(required = true) int boardSeq, BoardParameter parameter, Model model) {
		Board board = boardService.get(boardSeq);
		if(board == null) {
			throw new BaseException(BaseResponseCode.DATA_IS_NULL, new String[] {"게시물"});
		}
		model.addAttribute("board", board);
		model.addAttribute("parameter", parameter);
		
		return "/board/form";
	}
	
	/**
	 * 등록/수정 처리
	 * @param board
	 */
	@PostMapping("/{menuType}/save")
	@ResponseBody
	@RequestConfig(loginCheck = false)
	@ApiOperation(value = "등록/수정 처리", notes = "신규 게시물 저장 및 기존 게시물 업데이트가 가능합니다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "boardSeq", value = "게시물 번호", example = "1"),
		@ApiImplicitParam(name = "title", value = "제목", example = "제목 없음"),
		@ApiImplicitParam(name = "contents", value = "내용", example = "내용을 입력하세요.")
	})
	public BaseResponse<Integer> save(@PathVariable MenuType menuType, BoardParameter boardParameter) {
		// 제목 필수 체크
		if(!StringUtils.hasText(boardParameter.getTitle())) {
			throw new BaseException(BaseResponseCode.VALIDATE_REQUIRED, new String[] {"title", "제목"});
		}
		
		// 내용 필수 체크
		if(!StringUtils.hasText(boardParameter.getContents())) {
			throw new BaseException(BaseResponseCode.VALIDATE_REQUIRED, new String[] {"contents", "내용"});
		}
		boardParameter.setBoardType(menuType.getBoardType());
		boardService.save(boardParameter);
		return new BaseResponse<Integer>(boardParameter.getBoardSeq());
	}
	
	/**
	 * vue에서 파일 업로드
	 * @param board
	 */
	@PostMapping("/save")
	@ResponseBody
	public BaseResponse<Integer> saveVue(BoardParameter boardParameter, @RequestParam(required = false) MultipartFile uploadFile) {
		logger.info("uploadFile: {}", uploadFile);
		
		// 제목 필수 체크
		if(!StringUtils.hasText(boardParameter.getTitle())) {
			throw new BaseException(BaseResponseCode.VALIDATE_REQUIRED, new String[] {"title", "제목"});
		}
		
		// 내용 필수 체크
		if(!StringUtils.hasText(boardParameter.getContents())) {
			throw new BaseException(BaseResponseCode.VALIDATE_REQUIRED, new String[] {"contents", "내용"});
		}
		
		boardService.save(boardParameter);
		fileController.save(uploadFile);
		return new BaseResponse<Integer>(boardParameter.getBoardSeq());
	}
	
	/**
	 * 삭제 처리
	 * @param boardSeq
	 */
	@DeleteMapping("/{menuType}/{boardSeq}")
	@ResponseBody
	@RequestConfig(loginCheck = true)
	@ApiOperation(value = "삭제 처리", notes = "게시물 번호에 해당하는 정보를 삭제합니다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "boardSeq", value = "게시물 번호", example = "1"),
	})
	public BaseResponse<Boolean> delete(@PathVariable MenuType menuType, @PathVariable int boardSeq) {
		Board board = boardService.get(boardSeq);
		if(board == null) {
			return new BaseResponse<Boolean>(false);
		}
		boardService.delete(boardSeq);
		return new BaseResponse<Boolean>(true);
	}
	
	/**
	 * 10,000건 대용량 등록 테스트 (loop)
	 * 실행 시간: 39.018
	 * @return
	 */
	@ApiOperation(value = "대용량 등록 테스트(loop)", notes = "대용량 등록 테스트(loop)")
	@PutMapping("/saveListLoop")
	public BaseResponse<Boolean> saveList_loop() {
		int count = 0;
		// 랜덤으로 10000건의 데이터 생성
		List<BoardParameter> list = new ArrayList<BoardParameter>();
		while(true) {
			count++;
			String title = RandomStringUtils.randomAlphabetic(10);
			String contents = RandomStringUtils.randomAlphabetic(10);
			list.add(new BoardParameter(title, contents));
			if(count >= 10000) {
				break;
			}
		}
		long start = System.currentTimeMillis();
		boardService.saveList_loop(list);
		long end = System.currentTimeMillis();
		logger.info("실행 시간: {}초", (end - start) / 1000.0);
		return new BaseResponse<Boolean>(true);
	}
	
	/**
	 * 10,000건 대용량 등록 테스트 (map)
	 * 실행 시간: 0.701
	 * @return
	 */
	@ApiOperation(value = "대용량 등록 테스트(map)", notes = "대용량 등록 테스트(map)")
	@PutMapping("/saveListMap")
	public BaseResponse<Boolean> saveList_map() {
		int count = 0;
		// 랜덤으로 10000건의 데이터 생성
		List<BoardParameter> list = new ArrayList<BoardParameter>();
		while(true) {
			count++;
			String title = RandomStringUtils.randomAlphabetic(10);
			String contents = RandomStringUtils.randomAlphabetic(10);
			list.add(new BoardParameter(title, contents));
			if(count >= 10000) {
				break;
			}
		}
		long start = System.currentTimeMillis();
		boardService.saveList_map(list);
		long end = System.currentTimeMillis();
		logger.info("실행 시간: {}초", (end - start) / 1000.0);
		return new BaseResponse<Boolean>(true);
	}
	
}
