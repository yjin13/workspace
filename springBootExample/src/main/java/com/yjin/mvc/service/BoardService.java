package com.yjin.mvc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yjin.mvc.domain.Board;
import com.yjin.mvc.parameter.BoardParameter;
import com.yjin.mvc.parameter.BoardSearchParameter;
import com.yjin.mvc.repository.BoardRepository;

/**
 * 게시판 Service
 * @author yjin
 */
@Service
public class BoardService {
	
	@Autowired
	private BoardRepository repository;

	/**
	 * 목록 리턴
	 * @return
	 */
	public List<Board> getList(BoardSearchParameter parameter) {
		return repository.getList(parameter);
	}
	
	/**
	 * 상세 정보 리턴
	 * @param boardSeq
	 * @return
	 */
	public Board get(int boardSeq) {
		return repository.get(boardSeq);
	}
	
	/**
	 * 등록/수정 처리
	 * @param board
	 */
	public void save(BoardParameter boardParameter) {
		Board board = repository.get(boardParameter.getBoardSeq());
		if(board == null) {
			repository.save(boardParameter);
		} else {
			repository.update(boardParameter);
		}
	}
	
	/**
	 * 삭제 처리
	 * @param boardSeq
	 */
	public void delete(int boardSeq) {
		repository.delete(boardSeq);
	}
	
	/**
	 * 단순 반복문 등록 처리 (개수만큼 connection 호출)
	 * @param list
	 */
	public void saveList_loop(List<BoardParameter> boardList) {
		for(BoardParameter param : boardList) {
			repository.save(param);
		}
	}
	
	/**
	 * Map에 담아 일괄 등록 처리 (한 번의 connection 호출)
	 * @param boardList
	 */
	public void saveList_map(List<BoardParameter> boardList) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("boardList", boardList);
		repository.saveList(paramMap);
	}
	
}
