package com.datanew.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.datanew.service.ReportService;

/**
 * 获取spring容器，以访问容器中定义的其他bean
 * @author  lyltiger
 * @since  MOSTsView 3.0 2009-11-16
 */
public class SpringContextUtil implements ApplicationContextAware{

	private static ApplicationContext	applicationContext;

	/**
	 * 实现ApplicationContextAware接口的回调方法，设置上下文环境
	 */
	public void setApplicationContext(ApplicationContext applicationContext){
		SpringContextUtil.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext(){
		return applicationContext;
	}

	/**
	 * 获取对象
	 * @return  Object 一个以所给名字注册的bean的实例 (service注解方式，自动生成以首字母小写的类名为bean name)
	 */
	public static ReportService getReportService() {
		return (ReportService)applicationContext.getBean("reportService");
	}
}
