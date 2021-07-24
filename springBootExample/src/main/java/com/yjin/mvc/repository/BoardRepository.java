package com.yjin.mvc.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yjin.framework.data.domain.PageRequestParameter;
import com.yjin.mvc.domain.Board;
import com.yjin.mvc.parameter.BoardParameter;
import com.yjin.mvc.parameter.BoardSearchParameter;

/**
 * 게시판 Repository
 * @author yjin
 */
@Repository
public interface BoardRepository {

	List<Board> getList(PageRequestParameter<BoardSearchParameter> pageRequestParameter);
	
	Board get(int boardSeq);
	
	void save(BoardParameter boardParameter);
	
	void saveList(Map<String, Object> paramMap);
	
	void update(BoardParameter boardParameter);
	
	void delete(int boardSeq);
	
}
