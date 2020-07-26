package com.datanew.util;

import java.io.ByteArrayOutputStream;
import java.util.*;

public class StringUtil {
	public static String formatNullString(String str) {
		if (str == null || "null".equals(str)) {
			return "";
		} else {
			return str;
		}
	}

	/**
	 * 判断字符串是否为空或空字符串
	 * 
	 * @Description:
	 * @param @param
	 *            str
	 * @param @return
	 * @return boolean
	 * @throws @author
	 *             hjz
	 * @date 2015年11月10日 上午11:51:15
	 */
	public static boolean isblank(String str) {
		if (str == null || "".equals(str)) {
			return true;
		} else {
			return false;
		}
	}

	public static String escape(String src) {
		int i;
		char j;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length() * 6);
		for (i = 0; i < src.length(); i++) {
			j = src.charAt(i);
			if (Character.isDigit(j) || Character.isLowerCase(j) || Character.isUpperCase(j))
				tmp.append(j);
			else if (j < 256) {
				tmp.append("%");
				if (j < 16)
					tmp.append("0");
				tmp.append(Integer.toString(j, 16));
			} else {
				tmp.append("%u");
				tmp.append(Integer.toString(j, 16));
			}
		}
		return tmp.toString();
	}

	public static String unescape(String src) {
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos = 0, pos = 0;
		char ch;
		while (lastPos < src.length()) {
			pos = src.indexOf("%", lastPos);
			if (pos == lastPos) {
				if (src.charAt(pos + 1) == 'u') {
					ch = (char) Integer.parseInt(src.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else {
					ch = (char) Integer.parseInt(src.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else {
				if (pos == -1) {
					tmp.append(src.substring(lastPos));
					lastPos = src.length();
				} else {
					tmp.append(src.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}
		return tmp.toString();
	}

	// 随机生成指定长度的密码字符串
	public static String passWord(int length) {
		String password = "";
		Random random1 = new Random();
		int[][] array = { { 10, 48 }, { 26, 65 }, { 26, 97 } };
		for (int i = 0; i < length; i++) {
			int type = random1.nextInt(3);
			char one = (char) (random1.nextInt(array[type][0]) + array[type][1]);
			password += one;
		}
		return password;
	}

	
	 /**
	  * 
	   * codeAddFront
	   *
	   * @Title: codeAddFront
	   * @Description: 字符串位数不够前补充字符串
	   * @param @param code
	   * @param @param len
	   * @param @param str
	   * @param @return    
	   * @return String    
	   * @author hjz
	   * @throws
	  */
	 public static String codeAddFront(String code, int len,String str){
	     Integer intHao = Integer.parseInt(code);
	     intHao++;
	     String strHao = intHao.toString();
	     while (strHao.length() < len) {
	         strHao = str + strHao;
	     }
	     return strHao;
	 }

	/**
	 * 将每一个对象转换成map，并将key（key就是对象的属性名称）全部变为小写
	 * @param l
	 * @return
	 */
	public static List lowList(List l) {
		List list = new ArrayList();
		for (int i = 0; i < l.size(); i++) {
			Map m = (Map) l.get(i);
			Map p = new HashMap();
			Iterator iter = m.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				Object value = m.get(key);
				p.put(key.toLowerCase(), value);
			}
			list.add(p);
		}

		return list;

	}


}
