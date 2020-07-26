package com.datanew.util;

import java.util.HashMap;
import java.util.Map;

import com.datanew.dto.Constants;
import com.datanew.dto.Result;

public class StaticData {

		//登录的用户信息
		public static final String LOGINUSER="LOGINUSER";
		//登录的用户对应的菜单权限
		public static final String USERMENUS="USERMENUS";
		//登录的用户对应的按钮权限
		public static final String USERBUTTONS="USERBUTTONS";
		//登录的用户待办事项
	    public static final String USERWORK="USERWORK";
		//报表地址
		private static String url = ConfigureParser.getPropert("url", "");
		/*
		 * 获取文件信息
		 */
		public static Result getFileProperty(String authId,
				String targetVolume,String fileName)
				throws Exception {
			Result resultInfo = new Result();
			try {
				HttpRequester request = new HttpRequester();
				Map map = new HashMap();
				map.put("authId", java.net.URLEncoder.encode(authId, "UTF-8"));
				map.put("targetVolume", java.net.URLEncoder.encode(targetVolume, "UTF-8"));
				map.put("fileName", java.net.URLEncoder.encode(fileName, "UTF-8"));
				String response = request.sendPost(url + "GetFileProperty", map);
				XmlUtil xml = new XmlUtil(response);
				if (xml.getError() != null && !xml.getError().equals("")) {
					resultInfo.setMsg(xml.getError());
				}else {
					resultInfo.setFileInfo(xml.getFileInfo());
				}
			} catch (Exception e) {
				throw e;
			}
			return resultInfo;
		}
		/*
		 * 删除文件
		 */
		public static String deleteFile(String authId, String targetVolume,
				 String fileName) throws Exception {
			String msg = "";
			try {
				HttpRequester request = new HttpRequester();
				Map map = new HashMap();
				map.put("authId", java.net.URLEncoder.encode(authId, "UTF-8"));
				map.put("targetVolume", java.net.URLEncoder.encode(targetVolume, "UTF-8"));
				map.put("fileName", java.net.URLEncoder.encode(fileName, "UTF-8"));
				String response = request.sendPost(url + "DeleteFile", map);
				XmlUtil xml = new XmlUtil(response);
				if (xml != null && xml.getError() != null && !xml.getError().equals("")) {
					msg = xml.getError();
				}
			} catch (Exception e) {
				throw e;
			}
			return msg;
		}
		/*
		 * 获取报表参数信息
		 */
		public static Result publishReport(String targetVolume,
				String authId, String fileName, String customTableName,String mode)
				throws Exception {
			Result result = new Result();
			try {
				HttpRequester request = new HttpRequester();
				Map map = new HashMap();
				map.put("targetVolume", java.net.URLEncoder.encode(targetVolume, "UTF-8"));
				map.put("authId", java.net.URLEncoder.encode(authId, "UTF-8"));
				map.put("fileName", java.net.URLEncoder.encode(fileName, "UTF-8"));
				map.put("customTableName", java.net.URLEncoder.encode(customTableName, "UTF-8"));
				map.put("mode", java.net.URLEncoder.encode(mode, "UTF-8"));
				map.put("clearData", java.net.URLEncoder.encode(mode!=null&&mode.equals(Constants.PUBLISH_REPORT_MODE_ALL)?"true":"false", "UTF-8"));
				String response = request.sendPost(url + "publishReport", map);
				XmlUtil xml = new XmlUtil(response);
				if (!StringUtil.isblank(xml.getSuccess())) {
					result.setStrsuccess(xml.getSuccess());
				}
				if (!StringUtil.isblank(xml.getError())) {
					result.setError(xml.getError());
				}
				if (!StringUtil.isblank(xml.getReportFileName())) {
					result.setReportFileName(xml.getReportFileName());
				}
				if (StringUtil.isblank(xml.getBdoFileName())) {
					result.setBdoFileName(xml.getBdoFileName());
				}
			} catch (Exception e) {
				throw e;
			}
			return result;
		}

}
