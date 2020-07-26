package com.datanew.task;

import com.datanew.util.SpringContextUtil;
import com.datanew.util.StringUtil;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;


/**
 * <p> TimerCheckOut.java </p>
 * <p> 定时处理            </p>
 * @author $Author: chenzl $
 * @version $Revision: 1.6 $
 */
public class TimerCheckOut {		
	
	private String currentTime = "";	 //当前系统时间
	private static Timer _fldnew;				 //系统时钟
	
	/**
	 * 取系统本地时间
	 */
	private  String getLocalTime(){
		int h,mi; 
		Calendar cal=Calendar.getInstance();
		h=cal.get(Calendar.HOUR_OF_DAY); 
		mi=cal.get(Calendar.MINUTE); 
		String nowTime = "";
		if (mi < 10){
			nowTime = h + ":0" +mi;
		}else{
			nowTime = h + ":" +mi;
		}
		return nowTime;
		
	}
	
    public void SendMessage(String reportid,String moduleid){
    	_fldnew = new Timer(true); 
    	_fldnew.schedule(new CheckMessage(reportid,moduleid), 0L,60*60*1000L);
    }
    
	 class CheckMessage extends TimerTask{
		private String reportid="";
		private String moduleid="";
		public CheckMessage(String reportid,String moduleid){
			this.reportid=reportid;
			this.moduleid=moduleid;
		}
		public void run() {
			    if(!StringUtil.isblank(reportid)&&!StringUtil.isblank(moduleid)){
					if (SpringContextUtil.getReportService().saveSendMessage(reportid, moduleid)){
						System.out.println("@@@@[填报短信发送]成功@@@@");
					}else{
						System.out.println("@@@@[填报短信发送]失败@@@@");
					}
					 _fldnew.cancel();
			    }
		}
	} 

}

	