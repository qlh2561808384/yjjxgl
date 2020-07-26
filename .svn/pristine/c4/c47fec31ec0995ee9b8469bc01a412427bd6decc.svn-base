package com.datanew.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Properties;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.context.request.RequestContextHolder;
/**
 * 读取配置文件Configure.properties中的值
* @ClassName: ConfigureParser 
* @Description: 
* @author hjz
* @date 2015年11月1日 上午10:49:37 
*
 */
public class ConfigureParser {
	
	private static Properties properties;
	
	//静态工厂方法   
    public static Properties getInstance() {  
    	 if (properties == null) {    
    		 Resource res  =new ClassPathResource("config.properties");
    		 properties = new Properties();
    		try {
				properties.load(res.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
         }    
        return properties;  
    } 

    /**
     * 根据名称获取值
    * @Description: 
    * @param @param propertname
    * @param @return    
    * @return String   
    * @throws 
    * @author hjz 
    * @date 2015年11月1日 上午10:53:24
     */
    public static String getPropert(String propertname) {
    	return getPropert(propertname, null);
	}
    /**
     * 根据名称获取值
    * @Description: 
    * @param @param propertname
    * @param @param defalutvalue   默认值
    * @param @return    
    * @return String   
    * @throws 
    * @author hjz 
    * @date 2015年11月1日 上午10:54:30
     */
    public static String getPropert(String propertname,String defalutvalue) {
		if(propertname.equals("BB_URL")){
			String[] bb_url= getInstance().getProperty(propertname).toString().split(",");
			HttpServletRequest request =  ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			for(int i=0;i<bb_url.length;i++){
				String[] bb=properties.getProperty(bb_url[i]).split("\\|");
				String url = request.getServerName()+":"+request.getServerPort();
				if(url.equals(bb[0])){
					   return "http://"+bb[1].toString().trim()+"/HappyServer/";
				}
			}
		}else{
			String value = getInstance().getProperty(propertname);
			if(value==null){
			  return defalutvalue;	
			}else{
			  return value.toString().trim();
			}
		}
		return defalutvalue;
	}

    

}
