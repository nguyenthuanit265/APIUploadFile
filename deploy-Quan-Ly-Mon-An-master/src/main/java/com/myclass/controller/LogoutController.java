package com.myclass.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller

public class LogoutController {

	@RequestMapping(value = "/admin/logout", method = RequestMethod.GET)
	public String index(HttpServletRequest req) {
		HttpSession session = req.getSession();
		System.out.println("request logout");
		session.removeAttribute("USER_LOGIN");
		
		return "redirect:/admin/login";

	}
}
