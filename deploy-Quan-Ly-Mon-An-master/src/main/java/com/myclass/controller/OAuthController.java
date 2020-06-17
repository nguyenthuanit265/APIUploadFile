package com.myclass.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.dto.CustomUserDetails;


@RestController
public class OAuthController {

	@RequestMapping("/oauth/user")
	public Principal user(Principal principal) {
		
		return principal;
	}
	
	@RequestMapping("/admin/infor-user")
	public static void displayUsernameNavbar(ModelMap model, HttpServletRequest req) {
		CustomUserDetails employee = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		System.out.println("name log in home page: " + employee.getUsername());
		HttpSession session = req.getSession();
		session.setAttribute("USER_LOGIN", employee.getUsername());
		model.addAttribute("USER_LOGIN", employee.getUsername());
	}
	
//	@PostMapping("logout")
//	public void logout() {
//		
//	}
}
