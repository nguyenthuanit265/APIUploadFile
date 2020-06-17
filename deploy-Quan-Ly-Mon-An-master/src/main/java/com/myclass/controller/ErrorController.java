package com.myclass.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("error")
public class ErrorController {

	@GetMapping("403")
	public String forbiden() {
		return "error/403";
	}

//	@GetMapping("/admin/error")
//	public String index1() {
//		return "error/403";
//	}
}
