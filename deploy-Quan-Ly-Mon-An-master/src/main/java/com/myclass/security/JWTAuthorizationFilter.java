package com.myclass.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
	private UserDetailsService userDetailsService;

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
		super(authenticationManager);
		// TODO Auto-generated constructor stub
		this.userDetailsService = userDetailsService;

	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		final String JWT_SECRET = "chuoi_bi_mat";
		// lấy chuỗi token từ header của request
		String headerAuthorization = request.getHeader("Authorization");
		System.out.println("JWTAuthorizationFilter headerAuthorization: " + headerAuthorization);
		// Kiểm tra xem token đã được đính kèm vào request chưa
		// và có đúng định dạng hay không (token phải bắt đầu bằng Bearer)

		if (headerAuthorization != null && headerAuthorization.startsWith("Bearer ")) {
			// thay thế Bearer bằng "" đế lấy chuỗi token chính xác
			String token = headerAuthorization.replace("Bearer ", "");
			System.out.println("JWTAuthorizationFilter: " + token);
			// GIẢI mã token lấy email
			String email = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody().getSubject();
			System.out.println("email after resolve token: " + email);
			// lấy thông tin user từ database
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);

			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
					userDetails, null, userDetails.getAuthorities());

			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		}
		chain.doFilter(request, response);
	}

}
