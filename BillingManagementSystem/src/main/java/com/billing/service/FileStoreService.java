package com.billing.service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStoreService {
	
	public GridFsResource getDownloadFile(String fileName);
	
	public String singleUploadFile(MultipartFile file) throws IOException;
	
	public List<String> multipleUploadFile(MultipartFile[] file) throws IOException;
	
	public String deleteMultipleFile(List<String> fileNames);
	
	public String deleteSingleFile(String fileName);
	
}
