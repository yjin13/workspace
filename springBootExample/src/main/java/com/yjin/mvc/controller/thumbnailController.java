package com.yjin.mvc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yjin.configuration.exception.BaseException;
import com.yjin.configuration.http.BaseResponseCode;
import com.yjin.mvc.domain.ThumbnailType;
import com.yjin.mvc.domain.UploadFile;
import com.yjin.mvc.service.UploadFileService;

import net.coobird.thumbnailator.Thumbnails;

/**
 * 썸네일 컨트롤러
 * @author yjin
 */
@Controller
@RequestMapping("/thumbnail")
public class thumbnailController {
	final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UploadFileService uploadFileService;

	/**
	 * 썸네일 생성 (크기 변환)
	 * @param uploadFileSeq
	 * @param thumbnailType
	 * @param response
	 */
	@GetMapping("/make/{uploadFileSeq}/{thumbnailType}")
	public void make(@PathVariable int uploadFileSeq,
			@PathVariable ThumbnailType thumbnailType,
			HttpServletResponse response) {
		UploadFile uploadFile = uploadFileService.get(uploadFileSeq);
		if(uploadFile == null) {
			throw new BaseException(BaseResponseCode.UPLOAD_FILE_IS_NULL);
		}
		
		String pathname = uploadFile.getPathname();
		File file = new File(pathname);
		if(!file.isFile()) {
			throw new BaseException(BaseResponseCode.UPLOAD_FILE_IS_NULL);
		}
		try {
			String thumbnailPathname = uploadFile.getPathname().replace(".", "_" + thumbnailType.width() + "_" +thumbnailType.height() + ".");
			File thumbnailFile = new File(thumbnailPathname);
			if(!thumbnailFile.isFile()) {
				Thumbnails.of(pathname)
				.size(thumbnailType.width(), thumbnailType.height())
				.toFile(thumbnailPathname);
			}
			
			response.setContentType(MediaType.IMAGE_PNG_VALUE);
			FileCopyUtils.copy(new FileInputStream(thumbnailFile), response.getOutputStream());
			logger.info("thumbnailFile: {}", thumbnailPathname);
		} catch (IOException e) {
			logger.error("e", e);
		}
	}
	
}
