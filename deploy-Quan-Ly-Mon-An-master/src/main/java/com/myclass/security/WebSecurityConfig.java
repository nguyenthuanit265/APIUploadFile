package com.myclass.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@ComponentScan("com.myclass")
@Order(1)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	AuthenticationSuccessHandler authenticationSuccessHandler;
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new PasswordEncoder() {
			
			 @Override
			    public String encode(CharSequence charSequence) {
			        return charSequence.toString();
			    }

			    @Override
			    public boolean matches(CharSequence charSequence, String s) {
			        return charSequence.toString().equals(s);
			    }
		};
	}

	// khai báo service nào lấy thông tin user từ db và khai báo phương thức mã hóa
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().antMatcher("/admin/**") // phân quyền cho các url bắt đầu admin
				.authorizeRequests().antMatchers("/admin/login**").permitAll() // cho phép truy cập
				.antMatchers("/admin/home").hasAnyRole("EMPLOYEE", "USER", "ADMIN", "MANAGER")
				.antMatchers("/admin/bill/**").hasAnyRole("EMPLOYEE", "ADMIN", "MANAGER","USER")
				.antMatchers("/admin/detail/**").hasAnyRole("EMPLOYEE", "ADMIN", "MANAGER","USER")
				.antMatchers("/admin/product").hasAnyRole("EMPLOYEE", "ADMIN", "MANAGER","USER")
				.antMatchers("/admin/employee").hasAnyRole("EMPLOYEE", "ADMIN", "MANAGER","USER")
				.antMatchers("/admin/user/**").hasAnyRole("EMPLOYEE","ADMIN", "MANAGER","USER")
				.antMatchers("/admin/**")
				.hasAnyRole("ADMIN", "MANAGER","USER")// ví dụ /admin/manager chỉ cho quyền ADMIN và Manager truy cập
				.anyRequest() // Những liên kết khác /admin/** cho truy cập
				.permitAll();

		// cấu hình đăng nhập
		http.formLogin().loginPage("/admin/login") // custom link show trang đăng nhập ( default là: /login )
				.loginProcessingUrl("/admin/login") // link submit thông tin đăng nhập
				.usernameParameter("email").passwordParameter("password").defaultSuccessUrl("/admin/home").permitAll()
				.failureUrl("/admin/login?error=true");

		// cấu hình logout
		http.logout().logoutUrl("/admin/logout") // nhập vào link /admin/logout thì logout
				.logoutSuccessUrl("/admin/login") // logout thành công
				.deleteCookies("JSESSIONID");

		// cấu hình truy cập không đủ quyền
		http.exceptionHandling().accessDeniedPage("/error/403");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		super.configure(web);
	}
}
