package com.example.demo.security;

import java.io.IOException;
import java.security.cert.X509Certificate;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.exception.InvalidCertificateException;
import com.example.demo.service.CertificateValidationService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CertificateFilter extends OncePerRequestFilter {

	private final static String CERTIFICATE_ATTRIBUTE = "javax.servlet.request.X509Certificate";

	private final CertificateValidationService validationService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		if (request.getServletPath().startsWith("/api/requests") && request.getMethod().equalsIgnoreCase("delete")) {
			if (!this.validationService.isCertificateValid(((X509Certificate[]) 
					request.getAttribute(CERTIFICATE_ATTRIBUTE))[0].getSerialNumber().longValue())) {
				throw new InvalidCertificateException();
			}
		}
		filterChain.doFilter(request, response);		

	}
	
}
