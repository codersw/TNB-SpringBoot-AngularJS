package com.example.project.service;


import com.example.project.mapper.UploadFileMapper;
import com.example.project.model.UploadFile;
import com.example.project.util.UploadUtil;
import com.example.project.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.util.Date;

@Service("uploadFileService")
public class UploadFileService {

	@Autowired
	private UploadFileMapper uploadFileMapper;

    /**
     * 上传
     * @param file
	 * @param path
     * @return
     * @throws Exception
     */
	@Transactional
	public UploadFile addUploadFile(MultipartFile file,String path) throws Exception {
		UploadFile uploadFile = new UploadFile();
		uploadFile.setFileName(file.getOriginalFilename());
		uploadFile.setFilePath(UploadUtil.save(file,path));
		uploadFile.setFileSize(Integer.valueOf(file.getSize()+""));
		uploadFile.setFileType(file.getContentType());
		uploadFile.setCreateDate(new Date());
		uploadFile.setId(Util.UUID());
		uploadFileMapper.insert(uploadFile);
		return uploadFile;
	}

	/**
	 * 删除文件
	 * @param fileId
	 * @throws Exception
	 */
	@Transactional
	public void delFileByKey(String fileId) throws Exception {
		uploadFileMapper.deleteByPrimaryKey(fileId);
	}
 
}
