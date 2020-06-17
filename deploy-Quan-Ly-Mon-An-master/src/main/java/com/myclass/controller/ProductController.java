package com.myclass.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.utils.ObjectUtils;
import com.myclass.config.CloudinaryConfig;
import com.myclass.dto.ProductDto;
import com.myclass.entity.Product;
import com.myclass.repository.ProductRepository;
import com.myclass.repository.StatusRepository;

@Controller
@RequestMapping("admin/product")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private StatusRepository statusRepository;

	@GetMapping("")
	public String index(Model model) {
		System.out.println("============================================");
		List<Product> products = productRepository.myFindAll();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<String> rightStrings = authentication.getAuthorities().stream().map(r -> r.getAuthority())
				.collect(Collectors.toSet());

		for (String string : rightStrings) {
			System.out.println(string);
		}
		model.addAttribute("products", products);
		return "product/index";
	}

	@GetMapping("add")
	public String add(Model model) {

		model.addAttribute("product", new ProductDto());
		model.addAttribute("status", statusRepository.findAll());

		return "product/add";
	}

	@PostMapping("add")
	public String add(Model model, @Valid @ModelAttribute("product") ProductDto product, BindingResult errors,
			HttpServletRequest request) throws ParseException {
		// Kiểm tra nhập liệu
		if (errors.hasErrors()) {

			model.addAttribute("status", statusRepository.findAll());
			return "product/add";
		}

		MultipartFile[] fileDatas = product.getFileDatas();
		//
		List<File> uploadedFiles = new ArrayList<File>();
		List<String> failedFiles = new ArrayList<String>();

		for (MultipartFile fileData : fileDatas) {

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

					product.setImageUrl(cloudinaryImgURL);
				} catch (Exception e) {
					System.out.println("upload:" + e.getMessage());
				}
			}
		}

		Date now = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Product productAdd = new Product(product.getId(), product.getName(), product.getQuantity(), product.getPrice(),
				formatter.parse(formatter.format(now)), product.isBeverage(), product.getStatusId(), product.isDelete(),
				product.getImageUrl());

		try {

			productAdd.setDateProduct(formatter.parse(formatter.format(now)));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(productAdd.toString());
		productRepository.save(productAdd);
		// productAdd(product);
		// Chuyển hướng về trang danh sách
		return "redirect:/admin/product";
	}

	private void productAdd(ProductDto product) throws ParseException {
		Date now = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		System.out.println("Url Image: " + product.getImageUrl());

		// Thư mục gốc upload file.
		String uploadRootPath = System.getProperty("user.dir");

		uploadRootPath += "\\src\\main\\resources\\static\\assets" + "\\image_product\\";
		// Sử dụng đối tượng File của java.io để kiểm tra thư mục
		System.out.println("Folder save image: " + uploadRootPath);

		System.out.println("uploadRootPath=" + uploadRootPath);

		File uploadRootDir = new File(uploadRootPath);
		// Tạo thư mục gốc upload nếu nó không tồn tại.
		if (!uploadRootDir.exists()) {
			uploadRootDir.mkdirs();
		}
		MultipartFile[] fileDatas = product.getFileDatas();
		//
		List<File> uploadedFiles = new ArrayList<File>();
		List<String> failedFiles = new ArrayList<String>();

		for (MultipartFile fileData : fileDatas) {

			// Tên file gốc tại Client.
			String name = fileData.getOriginalFilename();
			System.out.println("Client File Name = " + name);

			if (name != null && name.length() > 0) {
				try {
					// Tạo file tại Server.
					File serverFile = new File(uploadRootDir + File.separator + name);

					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(fileData.getBytes());
					stream.close();
					//
					uploadedFiles.add(serverFile);
					System.out.println("Write file: " + serverFile);
					// product.setImageUrl(serverFile.toString());
					product.setImageUrl("/assets/image_product/" + name);
				} catch (Exception e) {
					System.out.println("Error Write file: " + name);
					failedFiles.add(name);
				}
			}
		}

		Product productAdd = new Product(product.getId(), product.getName(), product.getQuantity(), product.getPrice(),
				formatter.parse(formatter.format(now)), product.isBeverage(), product.getStatusId(), product.isDelete(),
				product.getImageUrl());

		try {

			productAdd.setDateProduct(formatter.parse(formatter.format(now)));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(productAdd.toString());
		productRepository.save(productAdd);
	}

	@GetMapping("edit/{id}")
	public String edit(@PathVariable("id") int id, Model model) {
		System.out.println("ID BEFORE: " + id);
		// model.addAttribute("id", id);
		model.addAttribute("product", productRepository.findById(id));
		model.addAttribute("status", statusRepository.findAll());
		return "product/edit";
	}

	@PostMapping("edit")
	public String edit(Model model, @Valid @ModelAttribute("product") Product product, BindingResult errors) {
		// Bắt lỗi nhập liệu
		if (errors.hasErrors()) {
			System.out.println("ERROR");
			model.addAttribute("status", statusRepository.findAll());
			return "product/edit";
		}
		System.out.println("ID AFTER: " + product.getId());
		// Cập nhật
		Date now = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		try {
			product.setDateProduct(formatter.parse(formatter.format(now)));
		} catch (ParseException e) {

			e.printStackTrace();
		}
		product.setQuantity(1);
		product.setBeverage(false);
		// product.setStatus(statusRepository.findById(product.getStatusId()).get());

		// product.setDateProduct(new Date());
		System.out.println(product.toString());
		productRepository.save(product);
		// Chuyển hướng về trang danh sách
		return "redirect:/admin/product";

	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") int id) {
		Optional<Product> product = productRepository.findById(id);
		product.get().setDelete(true);
		productRepository.saveAndFlush(product.get());
		// productRepository.deleteById(id);

		return "redirect:/admin/product";
	}
}
