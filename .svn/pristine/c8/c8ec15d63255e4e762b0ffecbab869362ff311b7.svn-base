package com.datanew.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
     public static Timestamp getCurrentTimeStamp(){
    	 Timestamp d = new Timestamp(System.currentTimeMillis());   
    	 return d;
     }
     public static Date getCurrentDate(String formatStr){
    	    SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
	     	Date date = null;
	     	try {
	     		date = sdf.parse(sdf.format(new Date()));
	 		} catch (ParseException e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		}
			return date;
     }
     public static Date getCurrentDate(Date indate,String formatStr){
 	    SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
	     	Date date = null;
	     	try {
	     		date = sdf.parse(sdf.format(indate));
	 		} catch (ParseException e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		}
			return date;
  }
     public static Date getCurrentDate(String indate,String formatStr){
  	    SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
 	     	Date date = null;
 	     	try {
 	     		date = sdf.parse(indate);
 	 		} catch (ParseException e) {
 	 			// TODO Auto-generated catch block
 	 			e.printStackTrace();
 	 		}
 			return date;
   }
}
