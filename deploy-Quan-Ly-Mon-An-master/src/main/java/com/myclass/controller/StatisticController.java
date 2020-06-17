package com.myclass.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myclass.dto.DetailStatisticDto;
import com.myclass.dto.StatisticDto;
import com.myclass.repository.BillRepository;

@Controller
@RequestMapping("admin/statistic")
public class StatisticController {
	
	@Autowired
	@Qualifier("billRepository")
	private BillRepository billRepository;
	
	@GetMapping("")
	public String index(Model model) {
	
		List<StatisticDto> list = billRepository.statisticAllByYear();
		for (StatisticDto statisticDto : list) {
			System.out.println(statisticDto.toString());
		}
		

		model.addAttribute("bills", list);
		return "statistic/index";
	}
	
	@GetMapping("detail/{year}")
	public String edit(Model model, @PathVariable("year") int year) {
		System.out.println("Year detail: " + year);
		
		
		List<DetailStatisticDto> list = billRepository.statisticByYear(year);
		for (DetailStatisticDto statisticDto : list) {
			System.out.println(statisticDto.toString());
		}
		

		model.addAttribute("bills", list);
		return "statistic/detail";
	}

}
