package com.myclass.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myclass.dto.StatisticDto;
import com.myclass.repository.BillRepository;

@Controller
@RequestMapping("admin")
public class HomeController {
	@Autowired
	private BillRepository billRepository;

	@GetMapping("home")
	public String index(Model model, HttpServletRequest req) {

		List<StatisticDto> list = billRepository.statisticAllByYear();
		for (StatisticDto statisticDto : list) {
			System.out.println(statisticDto.toString());
		}
		

		model.addAttribute("bills", list);
		return "home/index";
	}
	
	
	
	@GetMapping("")
	public String index1(Model model, HttpServletRequest req) {

		
		return "redirect:/admin/home";
	}
}
