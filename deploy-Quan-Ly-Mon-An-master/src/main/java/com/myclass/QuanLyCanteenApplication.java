package com.myclass;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.ModelMap;

@SpringBootApplication
public class QuanLyCanteenApplication {

	private static final ModelMap model = null;
	private static final HttpServletRequest req = null;

	public static void main(String[] args) {
		// displayUsernameNavbar(model, req);
		SpringApplication.run(QuanLyCanteenApplication.class, args);

	}
//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//		// TODO Auto-generated method stub
//		chain.doFilter(request, response);
//	}

//	public static void displayUsernameNavbar(ModelMap model, HttpServletRequest req) {
//		CustomUserDetails employee = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
//				.getPrincipal();
//		System.out.println("name log in home page: " + employee.getUsername());
//		HttpSession session = req.getSession();
//		session.setAttribute("USER_LOGIN", employee.getUsername());
//		model.addAttribute("USER_LOGIN", employee.getUsername());
//	}

//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//		HttpServletRequest req = (HttpServletRequest) request;
//		HttpServletResponse resp = (HttpServletResponse) response;
//		HttpSession session = req.getSession();
//
//		String action = req.getServletPath();
//
//		System.out.println(action);
//		// Trường hợp request yêu cầu vào trang login
//		if (action.equals("/admin/login") || action.startsWith("/api") || action.startsWith("/oauth")||action.startsWith("/login")) {
//
//			chain.doFilter(request, response);
//			return;
//		}
//
//		// Trường hợp client (chưa đăng nhập lần nào) gửi request muốn vào các trang
//		// khác mà không thông qua login thì chuyển hướng qua login
//		// check login
//
//		if (session.getAttribute("USER_LOGIN") == null) {
//			// chuyển hướng về trang đăng nhập
//			resp.sendRedirect(req.getContextPath() + "/admin/login?error=123");
//			return;
//		} else {
//			// Trường hợp client (đã đăng nhập) muốn vào các trang khác thì accept
//			chain.doFilter(request, response);
//		}
//
//	}
}
