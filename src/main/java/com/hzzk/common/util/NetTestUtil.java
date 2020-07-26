package com.hzzk.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datanew.util.StringUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.URL;


/**
 * Created by Administrator on 2017/9/1.
 */
public class NetTestUtil {
    private static final Logger method_error = LoggerFactory.getLogger("method_error");
    private static final Logger methodLog = LoggerFactory.getLogger("method");

    public static boolean isReachable(String remoteInetAddr) {
       boolean reachable = false;
        try {
                InetAddress address = InetAddress.getByName(remoteInetAddr);
                reachable = address.isReachable(2000);
        } catch (Exception e) {
                e.printStackTrace();
        }
        return reachable;
     }

    public static String getUrl(String urlString ){
        if(urlString == null){
            return "";
        }
        String reg = ".*\\/\\/([^\\/\\:]*).*";
        String str1 = urlString;
        if(str1.matches(reg)){
            return str1.replaceAll (reg, "$1");
        }else{
            return "";
        }
    }

    public static Boolean pingIP(String urlString ){
        boolean isreachable = true;
        String serverIP = NetTestUtil.getUrl(urlString);
        if(!StringUtil.isblank(serverIP.trim())){
            if(!NetTestUtil.isReachable(serverIP.trim())){
                isreachable = false;
                methodLog.info("ping ip地址："+urlString+",不通;");
            }
        }else {
            isreachable = true;
        }
        return isreachable;
    }

    public static void main(String[] args) {
        System.out.println(pingIP("http://59.202.26.9:8080/nontax/services/paymentRemottingService?wsdl"));
    }

}
