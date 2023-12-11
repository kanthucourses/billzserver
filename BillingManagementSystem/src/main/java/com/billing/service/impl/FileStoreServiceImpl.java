package com.billing.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.billing.service.FileStoreService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;

@Service
@Transactional
public class FileStoreServiceImpl implements FileStoreService{

	@Autowired
	private GridFsOperations operations;

	//Downloading Single File
	public GridFsResource getDownloadFile(String fileName) {
		GridFSFile file = operations.findOne(new Query(Criteria.where("filename").is(fileName)));
		GridFsResource resource =null;
		if(file!=null) {
			resource = operations.getResource(file.getFilename());  
		}
		return resource;
	}

	//Uploading Single File 
	public String singleUploadFile(MultipartFile file) throws IOException {
		String filename = null;
		if(!file.isEmpty()) {
			DBObject metaData = new BasicDBObject();
			metaData.put("organization", "dli");
			metaData.put("type", "pdf");
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYYMMddhhmmss");
			String dateAsString = simpleDateFormat.format(new Date());
			filename=dateAsString+"-"+file.getOriginalFilename();
			InputStream inputStream = file.getInputStream();
			operations.store(inputStream, filename, file.getContentType());
		}
		return filename;
	}

	//Uploading Multiple files 
	public List<String> multipleUploadFile(MultipartFile[] file) throws IOException {
		List<String> fileList = new ArrayList<>();
		for (MultipartFile multipartFile : file) {						
			if (!multipartFile.isEmpty()) { 
				DBObject metaData = new BasicDBObject();
				metaData.put("organization", "dli");
				metaData.put("type", "pdf");
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYYMMddhhmmss");
				String dateAsString = simpleDateFormat.format(new Date());
				String filename=dateAsString+"-"+multipartFile.getOriginalFilename();
				InputStream inputStream = multipartFile.getInputStream();
				operations.store(inputStream, filename, multipartFile.getContentType());
				fileList.add(filename);
			}
		}
		return fileList; 
	}

	//View image Files
	/*@Override
	public byte[] viewFile(String fileName) {
		GridFSFile file = operations.findOne(new Query(Criteria.where("filename").is(fileName)));
		GridFsResource gridFsResource = operations.getResource(file);
		if (gridFsResource != null) {
			try {
				return IOUtils.toByteArray(gridFsResource.getInputStream());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return file.getFilename().getBytes();
	}*/

	//Deleting Multiple Files
	public String deleteMultipleFile(List<String> fileNames){
		for(String file:fileNames) {
			operations.delete(new Query(Criteria.where("filename").is(file)));
		}
		return "Done";
	}

	//Deleting Single File
	public String deleteSingleFile(String fileName){		
		operations.delete(new Query(Criteria.where("filename").is(fileName)));
		return "Done";
	}

}
