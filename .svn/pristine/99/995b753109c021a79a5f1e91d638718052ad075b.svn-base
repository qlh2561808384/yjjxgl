package com.datanew.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtil {

	/**
	 * 根据名字获取cookie
	 * @param request
	 * @param name cookie名字
	 * @return
	 */
	public static String getCookieByName(HttpServletRequest request,String name){
		Cookie[] cookies =request.getCookies();
		String returnstr="";
		if(cookies!=null){
			for (Cookie cookie : cookies) {
				if(name.equals(cookie.getName())){
					returnstr=StringUtil.unescape(cookie.getValue());
					break;
			    }   
			}
		}
		return returnstr;
	}

}
