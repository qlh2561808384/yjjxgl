package com.datanew.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.datanew.util.TestCase;

public class ExportServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public ExportServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String path = request.getContextPath();
		PrintWriter out = response.getWriter();
		//获取传过来的表单数据,根据表单中的name获取所填写的值
		String ip = request.getParameter("webip");
		TestCase tc = new TestCase();
		//String responseContent = tc.getXmlbyUrl(ip,"");
		//boolean flag = tc.decodeXML(responseContent);
		/*if(flag){
			 response.sendRedirect(path + "/success.html");
		}else{
			response.sendRedirect(path + "/fail.html");
		}*/
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
