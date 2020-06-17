package com.myclass.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.dto.LoginUserDto;
import com.myclass.repository.EmployeeRepository;

@Controller
@RequestMapping("/admin")
public class LoginController {
	@Autowired
	EmployeeRepository employeeRepository;

	@GetMapping("login")
	public String loginPage(@RequestParam(defaultValue = "") String error, Model model) {

		if (!error.equals("")) {
			model.addAttribute("error", "Sai tên đăng nhập mật khẩu");

		}
		model.addAttribute("loginUserDto", new LoginUserDto());
		return "login/index";
	}


//	@PostMapping("login")
//	public String post(@Valid @ModelAttribute("loginUserDto") LoginUserDto loginUserDto, BindingResult errors,
//			Model model, HttpServletRequest req) {
//		System.out.println("Email login: " + loginUserDto.getEmail());
//		System.out.println("Password login: " + loginUserDto.getPassword());
//
//		if (loginUserDto.getPassword().equals("123456") && loginUserDto.getEmail().equals("admin@gmail.com")) {
//			HttpSession session = req.getSession();
//			session.setAttribute("USER_LOGIN", loginUserDto);
//			System.out.println("FullName Login: " + loginUserDto.getEmail());
//			return "redirect:/admin/home";
//		}
//
//		
//		Employee employee = employeeRepository.findByEmail(loginUserDto.getEmail());
//
//		if (employee == null) {
//			model.addAttribute("error", "Email not in database");
//			return "login/index";
//		}
//		System.out.println("password in database: " + employee.getPassword());
////		if (BCrypt.checkpw(loginUserDto.getPassword(), employee.getPassword())) {
////			HttpSession session = req.getSession();
////			session.setAttribute("USER_LOGIN", employee);
////			System.out.println("FullName Login: " + employee.getName());
////			return "redirect:/admin/home";
////		}
//
//		if (loginUserDto.getPassword().equals(employee.getPassword())) {
//			HttpSession session = req.getSession();
//			session.setAttribute("USER_LOGIN", employee);
//			System.out.println("FullName Login: " + employee.getName());
//			return "redirect:/admin/home";
//		}
//
//		else {
//			model.addAttribute("error", "Check account");
//			return "login/index";
//		}
//
//	}

}
