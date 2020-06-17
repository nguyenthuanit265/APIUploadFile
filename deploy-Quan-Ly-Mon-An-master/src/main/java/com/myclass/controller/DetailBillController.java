package com.myclass.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myclass.entity.DetailBill;
import com.myclass.repository.DetailBillRepository;

@Controller
@RequestMapping("/admin/detail")
public class DetailBillController {
	@Autowired
	private DetailBillRepository detailBillRepository;

	@GetMapping("")
	public String index(Model model) {
		System.out.println("get detail bill");
		List<DetailBill> details= detailBillRepository.findAll();
		
		model.addAttribute("details", details);
		return "detail-bill/index";
	}
}
