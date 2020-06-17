package com.myclass.security;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.myclass.dto.CustomUserDetails;
import com.myclass.entity.Employee;
import com.myclass.repository.EmployeeRepository;
@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler{

    @Autowired HttpSession session; //autowiring session

    @Autowired EmployeeRepository employeeRepository; //autowire the user repo


    private static final Logger logger = LoggerFactory.getLogger(AuthenticationSuccessHandlerImpl.class);
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        // TODO Auto-generated method stub
       
        CustomUserDetails employee = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		System.out.println("name log in: " + employee.getUsername());
		//HttpSession session = req.getSession();
		//session.setAttribute("USER_LOGIN", employee.getUsername());
		Employee employee1 = employeeRepository.findByEmail(employee.getUsername());
        logger.info("userName: " + employee1.getName());
        //HttpSession session = request.getSession();
        session.setAttribute("USER_LOGIN",  employee1.getName());

    }

}