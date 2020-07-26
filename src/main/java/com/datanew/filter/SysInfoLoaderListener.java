package com.datanew.filter;



import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

import com.datanew.dto.Global;




public class SysInfoLoaderListener extends ContextLoaderListener {

	public void contextDestroyed(ServletContextEvent event) {

		super.contextDestroyed(event);
	}

	public void contextInitialized(ServletContextEvent event) {
		//super.contextInitialized(event);
		System.out.println("spring加载完毕,开始初始化数据服务");
		
		Global.setApplicationContext(getCurrentWebApplicationContext());
		System.out.println("初始化数据服务完毕");
	}
	
}
