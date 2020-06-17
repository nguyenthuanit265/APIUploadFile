package com.myclass.api.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.cloudinary.utils.ObjectUtils;
import com.myclass.config.CloudinaryConfig;
import com.myclass.dto.ProductDto;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.myclass.entity.Product;
import com.myclass.repository.ProductRepository;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("api-test/v01/product")
@CrossOrigin("*")
public class ApiProductController {

	@Autowired
	private ProductRepository productRepository;
	@GetMapping("get")
	public Object getProduct() {
		try {
			List<Product> products = productRepository.findAll();
			return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Exception>(e,HttpStatus.BAD_REQUEST);
		}
		
		
	}

	@RequestMapping(value=("/add"), method=RequestMethod.POST)
	public Object addProduct(@RequestParam MultipartFile fileData) throws ParseException {
		//MultipartFile[] fileDatas = product.getFileDatas();
		//
		List<File> uploadedFiles = new ArrayList<File>();
		List<String> failedFiles = new ArrayList<String>();



			// Tên file gốc tại Client.
			String fileNameWithExt = fileData.getOriginalFilename();
			System.out.println("Client File Name = " + fileNameWithExt);

			String cloudinaryImgURL = null;
			if (fileNameWithExt != null && fileNameWithExt.length() > 0) {
				try {

					File fileDir = new File("rowFiles");
					if (!fileDir.exists()) {
						fileDir.mkdir();
					}

					// remove extension
					String fileName = FilenameUtils.removeExtension(fileNameWithExt);

					ProductDto productDto = productRepository.findFileByName(fileName);
					if (productDto != null) {
						String random = String.valueOf((System.currentTimeMillis() / 1000l));
						fileName += "_" + random;
					}

					System.out.println("name file: " + fileName);
					File physicalFile = new File(fileData.getOriginalFilename());
					FileOutputStream fout = new FileOutputStream(fileDir.getName() + "/" + physicalFile);
					fout.write(fileData.getBytes());
					fout.close();
					File toUpload = new File("rowFiles/" + fileNameWithExt);
					System.out.println("to Upload: " + toUpload.toString());
					CloudinaryConfig cloudinary = new CloudinaryConfig("383512482675879", "dYs2mK0Lz58eW40zSn7k6DiBB8E",
							"diahxy476");
					// System.out.println("API Key:"+cloudinary.config.apiKey);
					Map params = ObjectUtils.asMap("public_id", "SRWRestImageBase/" + fileName);
//				        Map uploadResult = cloudinary.uploader().upload(toUpload, params);
					Map uploadResult = cloudinary.upload(toUpload, params);
					toUpload.delete();
					System.out.println("==============>>" + uploadResult.get("url"));
					cloudinaryImgURL = uploadResult.get("url").toString();

					System.out.println("link image: " + cloudinaryImgURL);
					return new ResponseEntity<Object>(HttpStatus.CREATED);
				} catch (Exception e) {
					System.out.println("upload:" + e.getMessage());
				}
			}




		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST	);
	}
}
