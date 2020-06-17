package com.myclass.security;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableOAuth2Sso
@ComponentScan("com.myclass")
@Order(3)
public class OAuthConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.antMatcher("/**")
		.authorizeRequests()
		.antMatchers("/","/login**").permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.logout()
		.logoutUrl("/oauth/logout")
		.logoutSuccessUrl("/admin/login")
		.permitAll();
		http.logout().logoutUrl("/oauth/logout") // nhập vào link /admin/logout thì logout
				.logoutSuccessUrl("/admin/login") // logout thành công
				.deleteCookies("JSESSIONID");
	}
}
