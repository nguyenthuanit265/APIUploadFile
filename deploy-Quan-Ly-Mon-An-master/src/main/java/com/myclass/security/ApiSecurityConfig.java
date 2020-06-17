package com.myclass.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@ComponentScan("com.myclass")
@Order(2)
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder1() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}

	// khai báo service nào lấy thông tin user từ db và khai báo phương thức mã hóa
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder1());

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.cors();

		http.csrf().disable().antMatcher("/api/**") // phân quyền cho các url bắt đầu api
				.authorizeRequests().antMatchers("/api/login", "/api/register").permitAll() // cho phép truy cập
				.anyRequest().permitAll(); // Những link còn lại ngoài cái api/login api/register yêu cầu đăng nhập
				//.authenticated(); // kiểm tra đăng nhập hay chưa

		// CHECK TOKEN LẤY THÔNG TIN NGƯỜI DÙNG
		System.out.println("abcccc");
		http.addFilter(new JWTAuthorizationFilter(authenticationManager(), userDetailsService));
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

}
