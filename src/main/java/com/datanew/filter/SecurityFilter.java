package com.datanew.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.datanew.util.StringUtil;


/**
 * 
  * @ClassName: SecurityFilter
  * @Description: 登录拦截
  * @author hjz
  * @date 2016年4月21日 下午4:51:24
  *
 */
public class SecurityFilter implements Filter {
	@SuppressWarnings("unused")
	private FilterConfig filterCon = null;

	public void init(FilterConfig config) throws ServletException {
		filterCon = config;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		Map sysUser =  (Map) httpRequest.getSession().getAttribute("LOGINUSER");   
	       
		
		StringBuffer url = httpRequest.getRequestURL();

		if (sysUser == null) {
			httpResponse.sendRedirect(httpRequest.getContextPath()
							+ "/result.html?MSG="+StringUtil.escape("您无权访问此页面"));
		} else {
			filterChain.doFilter(request, response);
		}
	}

	public void destroy() {
		filterCon = null;
	}
}
