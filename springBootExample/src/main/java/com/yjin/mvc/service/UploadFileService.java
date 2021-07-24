package com.yjin.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yjin.mvc.domain.UploadFile;
import com.yjin.mvc.parameter.UploadFileParameter;
import com.yjin.mvc.repository.UploadFileRepository;

/**
 * 파일 Service
 * @author yjin
 */
@Service
public class UploadFileService {

	@Autowired
	private UploadFileRepository repository;
	
	/**
	 * 파일 업로드 처리
	 * @param parameter
	 */
	public void save(UploadFileParameter parameter) {
		repository.save(parameter);
	}

	public UploadFile get(int uploadFileSeq) {
		return repository.get(uploadFileSeq);
	}
	
}
