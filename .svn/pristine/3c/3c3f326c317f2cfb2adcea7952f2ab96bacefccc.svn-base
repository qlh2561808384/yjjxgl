package com.datanew.webservice;

import java.net.URL;
import java.text.SimpleDateFormat;

import org.codehaus.xfire.client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datanew.util.ConfigureParser;

public class WebServiceClient {
	private static final Logger method_error = LoggerFactory.getLogger("method_error");
	private static final Logger methodLog = LoggerFactory.getLogger("method");
	
	
	/**
	 * 调用办公助手提供的webservice方法
	 * @param method
	 * @param xml
	 * @return
	 */
	public static String getClientMessage(String method, Object[] xml) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");		
		String returnString = "";
		//调用次数
		try {
			Client client = new Client(new URL(ConfigureParser.getPropert("WEBSERVICE_MESSAGE",null)));
			Object[] results = client.invoke(method, xml);
			returnString = results[0].toString();
			 methodLog.info("\r\n--开始调用"+method+"方法，请求的数据为["+xml+"]\r\n--返回的数据为"+returnString);
		}catch (Exception e) {
			e.printStackTrace();
			method_error.error("\r\n--开始调用"+method+"方法，请求的数据为["+xml+"]\r\n--异常："+e.toString());
		}
		
		return returnString;
	}
	/**
	 * 调用银行提供的webservice方法
	 * @param method
	 * @param xml
	 * @return
	 */
	public static String get_AccBalanceDetail_Client(String adress,String method, String xml) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");		
		String returnString = "";
		Object[] obj= new Object[1];
		obj[0]=xml;
		//调用次数
		try {
			Client client = new Client(new URL(adress));
			Object[] results = client.invoke(method, obj);
			returnString = results[0].toString();
			 methodLog.info("\r\n--开始调用"+method+"方法，请求的数据为["+xml+"]\r\n--返回的数据为"+returnString);
		}catch (Exception e) {
			e.printStackTrace();
			method_error.error("\r\n--开始调用"+method+"方法，请求的数据为["+xml+"]\r\n--异常："+e.toString());
		}
		
		return returnString;
	}
	public static void main(String[] args) {
	}

}
