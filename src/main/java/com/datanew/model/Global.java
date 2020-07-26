package com.datanew.model;


import com.datanew.dao.BaseDao;
import com.datanew.util.ConfigureParser;
import org.springframework.context.ApplicationContext;


public class Global {
	private static ApplicationContext _ctx = null;
    public static void setApplicationContext( ApplicationContext context){
        _ctx=context;
    }

	public static ApplicationContext getContext() {
		return _ctx;
	}


    public static BaseDao getCommonDao(){
    	return (BaseDao) getContext().getBean("baseDao");
    }
    public static String  getAppGUID(){
        return ConfigureParser.getPropert("application.id");
    }
    public static String getFszgUser(){
        return ConfigureParser.getPropert("fszgdatabaseuser");
    }



}
