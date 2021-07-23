package com.yjin.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yjin.mvc.domain.Board;
import com.yjin.mvc.parameter.BoardParameter;
import com.yjin.mvc.service.BoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 게시판 Controller
 * @author yjin
 */
@RestController
@RequestMapping("/board")
@Api(tags = "게시판 API")
public class BoardController {
	
	@Autowired
	private BoardService boardService;

	/**
	 * 목록 리턴
	 * @return
	 */
	@GetMapping
	@ApiOperation(value = "목록 조회", notes = "게시물 전체 목록을 조회할 수 있습니다.")
	public List<Board> getList() {
		return boardService.getList();
	}
	
	/**
	 * 상세 정보 리턴
	 * @param boardSeq
	 * @return
	 */
	@GetMapping("/{boardSeq}")
	@ApiOperation(value = "상세 조회", notes = "게시물 번호에 해당하는 상세 정보를 조회할 수 있습니다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "boardSeq", value = "게시물 번호", example = "1")
	})
	public Board get(@PathVariable int boardSeq) {
		return boardService.get(boardSeq);
	}
	
	/**
	 * 등록/수정 처리
	 * @param board
	 */
	@PutMapping
	@ApiOperation(value = "등록/수정 처리", notes = "신규 게시물 저장 및 기존 게시물 업데이트가 가능합니다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "boardSeq", value = "게시물 번호", example = "1"),
		@ApiImplicitParam(name = "title", value = "제목", example = "제목 없음"),
		@ApiImplicitParam(name = "contents", value = "내용", example = "내용을 입력하세요.")
	})
	public int save(BoardParameter boardParameter) {
		boardService.save(boardParameter);
		return boardParameter.getBoardSeq();
	}
	
	/**
	 * 삭제 처리
	 * @param boardSeq
	 */
	@DeleteMapping("/{boardSeq}")
	@ApiOperation(value = "삭제 처리", notes = "게시물 번호에 해당하는 정보를 삭제합니다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "boardSeq", value = "게시물 번호", example = "1"),
	})
	public boolean delete(@PathVariable int boardSeq) {
		Board board = boardService.get(boardSeq);
		if(board == null) {
			return false;
		}
		boardService.delete(boardSeq);
		return true;
	}
	
}
