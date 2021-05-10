package com.ikubinfo.primefaces.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "AuthFilter", urlPatterns = { "/*" })
public class AuthorizationFilter implements Filter {

	public AuthorizationFilter() { 
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try {

			HttpServletRequest reqt = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			HttpSession ses;
			if (reqt.getSession(false) == null) {
				ses = reqt.getSession(true);
			}else
			{
				ses = reqt.getSession(false);
			}
				 
			
			String reqURI = reqt.getRequestURI();
			
			if ((ses != null && ses.getAttribute("username") != null)
					|| reqURI.contains("javax.faces.resource")||!reqURI.contains("/loginReq/"))
				chain.doFilter(request, response);
			else {
				ses.setAttribute("destination", reqURI);
				resp.sendRedirect(reqt.getContextPath() + "/faces/login.xhtml");
			}
				
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void destroy() {

	}
}