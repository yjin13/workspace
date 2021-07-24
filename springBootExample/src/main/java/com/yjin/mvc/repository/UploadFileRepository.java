package com.yjin.mvc.repository;

import org.springframework.stereotype.Repository;

import com.yjin.mvc.domain.UploadFile;
import com.yjin.mvc.parameter.UploadFileParameter;

/**
 * 파일 Repository
 * @author yjin
 */
@Repository
public interface UploadFileRepository {

	void save(UploadFileParameter parameter);

	UploadFile get(int uploadFileSeq);
	
}
