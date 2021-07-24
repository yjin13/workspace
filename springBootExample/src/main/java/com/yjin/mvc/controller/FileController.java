package com.yjin.mvc.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yjin.configuration.GlobalConfig;
import com.yjin.configuration.exception.BaseException;
import com.yjin.configuration.http.BaseResponse;
import com.yjin.configuration.http.BaseResponseCode;
import com.yjin.mvc.parameter.UploadFileParameter;
import com.yjin.mvc.service.UploadFileService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/file")
@Api(tags = "파일 API")
public class FileController {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private GlobalConfig config;
	
	@Autowired
	private UploadFileService uploadFileService;
	
	/**
	 * 파일 업로드
	 * @return
	 */
	@PostMapping("/save")
	@ApiOperation(value = "파일 업로드", notes = "파일 업로드")
	public BaseResponse<Boolean> save(@RequestParam("uploadFile") MultipartFile multipartFile) {
		logger.info("multipartFile: {}", multipartFile);
		if(multipartFile == null || multipartFile.isEmpty()) {
			throw new BaseException(BaseResponseCode.DATA_IS_NULL, new String[] {"파일"});
		}
		
		String curDate = new SimpleDateFormat("yyyyMM").format(Calendar.getInstance().getTime());
		String uploadFilePath = config.getUploadFilePath() + curDate + "/";
		logger.info("uploadFilePath: {}", uploadFilePath);
		
		String ext = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1, multipartFile.getOriginalFilename().length());
		String fileName = UUID.randomUUID().toString() + "." + ext;
		String pathName = uploadFilePath + fileName;
		logger.info("pathName: {}", pathName);
		
		// 폴더가 없으면 생성
		File folder = new File(uploadFilePath);
		if(!folder.isDirectory()) {
			folder.mkdirs();
		}
		
		String resourcePath = config.getUploadResourcePath() + curDate + "/" + fileName;
		File dest = new File(pathName);
		try {
			multipartFile.transferTo(dest);
			
			UploadFileParameter parameter = new UploadFileParameter();
			parameter.setContentType(multipartFile.getContentType());
			parameter.setOriginalFilename(multipartFile.getOriginalFilename());
			parameter.setFilename(fileName);
			parameter.setPathname(pathName);
			parameter.setSize((int) multipartFile.getSize());
			parameter.setResourcePathname(resourcePath);
			
			uploadFileService.save(parameter);
		} catch(IllegalStateException | IOException e) {
			logger.error("e", e);
		}
		
		return new BaseResponse<Boolean>(true);
	}
}
