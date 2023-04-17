package com.app.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.pojo.Products;
import com.app.service.IProductsService;

@RestController
@CrossOrigin
public class FileUploadController {
	
	@Autowired
	private IProductsService service;

	@RequestMapping(value = "/upload",method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String fileUpload(@RequestParam("file") MultipartFile file,@RequestParam String fileName,
			@RequestParam Integer productId)throws IOException
	{
		Products product=service.getProductsDetailsById(productId);
		
		File convertFile=new File("F:/Project Git/react-frontend/tms/public/images\\"+fileName);
		convertFile.createNewFile();
		
		try(FileOutputStream fout=new FileOutputStream(convertFile)) {
			fout.write(file.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		product.setImages("\\images\\"+fileName);
		service.saveProductsDetails(product);
		return "\\images\\"+fileName;
	}
}