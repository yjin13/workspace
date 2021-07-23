package com.yjin.mvc.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yjin.mvc.domain.Board;
import com.yjin.mvc.parameter.BoardParameter;

/**
 * 게시판 Repository
 * @author yjin
 */
@Repository
public interface BoardRepository {

	List<Board> getList();
	
	Board get(int boardSeq);
	
	void save(BoardParameter boardParameter);
	
	void update(BoardParameter boardParameter);
	
	void delete(int boardSeq);
	
}
