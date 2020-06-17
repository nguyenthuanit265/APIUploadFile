package com.myclass.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myclass.entity.Status;
import com.myclass.repository.StatusRepository;

@Controller
@RequestMapping("admin/status")
public class StatusController {
	@Autowired
	private StatusRepository statusRepository;
	@GetMapping("")
	public String index(Model model) {
		
		model.addAttribute("status", statusRepository.findAll());
		return "status/index";
	}
	@GetMapping("add")
	public String add(ModelMap model) {
		model.addAttribute("status", new Status());
		return "status/add";
	}
	
	@PostMapping("add")
	public String add(Model model, 
			@Valid @ModelAttribute("status") Status status, 
			BindingResult errors) {
		// Kiểm tra nhập liệu
		if(errors.hasErrors()) {
			return "status/add";
		}
		
		// Thêm mới role và danh sách
		statusRepository.save(status);
		
		// Chuyển hướng về trang danh sách
		return "redirect:/admin/status";
	}
	@GetMapping("edit/{id}")
	public String edit(Model model, @PathVariable("id") int id) {
		System.out.println("ID EDIT: " + id);
		Optional<Status> status = statusRepository.findById(id);
		
		System.out.println("Name Role edit: " + status.get().getName());
		
		
		model.addAttribute("statusEdit", status);
		
		return "status/edit";
	}
	
	@PostMapping("edit")
	public String edit(@Valid @ModelAttribute("statusEdit") Status status, BindingResult errors) {
		if (errors.hasErrors()) {
			return "status/edit";
		}
		
		statusRepository.saveAndFlush(status);
		return "redirect:/admin/role";
	}
	
	@GetMapping("delete/{id}")
	public String delete(Model model, @PathVariable("id") int id) {
		System.out.println("ID DELETE: " + id);
	//	Optional<Status> status = statusRepository.findById(id);
		
	//	System.out.println("Name Role delete: " + status.get().getName());
		
		
		statusRepository.deleteById(id);
		
		return "redirect:/admin/role";
	}
}
