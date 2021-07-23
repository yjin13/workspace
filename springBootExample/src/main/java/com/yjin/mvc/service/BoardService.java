package com.yjin.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yjin.mvc.domain.Board;
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
	public List<Board> getList() {
		return repository.getList();
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
	public void save(Board param) {
		Board board = repository.get(param.getBoardSeq());
		if(board == null) {
			repository.save(param);
		} else {
			repository.update(param);
		}
	}
	
	/**
	 * 삭제 처리
	 * @param boardSeq
	 */
	public void delete(int boardSeq) {
		repository.delete(boardSeq);
	}
	
}
