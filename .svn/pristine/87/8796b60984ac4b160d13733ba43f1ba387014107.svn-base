package com.datanew.util;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Lustin on 2017/3/14.
 */
public class FunctionCache {
    private static final Map functionMap=new ConcurrentHashMap();
    private static final Map functionTimeMap=new ConcurrentHashMap();

        public static void clearFunction(String key){
         functionMap.remove("session_"+key);
        functionTimeMap.remove("session_"+key);
    }
    public static void putFunction(String sessionid,List list){
        functionMap.put("session_"+sessionid,list);
        functionTimeMap.put("session_"+sessionid,new Date().getTime());
    }
    public static boolean isAuthoried(String sessionId,String uri){
        List<Map> functionList= ((List) functionMap.get("session_" + sessionId));
        if(functionList==null)return false;
        for(Map m:functionList){
           String url= ((String) m.get("url"));
            if(url.indexOf("/")>0&&uri.indexOf(url.substring(url.indexOf("/")))>0){
                return true;
            }

        }
        return false;
    }

    public static void flush() {
        Set<Map.Entry> ens=functionTimeMap.entrySet();
        Long cur=new Date().getTime();
        System.out.println("清除功能表");
        for(Map.Entry en:ens){
            if(cur-((Long) en.getValue())>1000*60*60){
                System.out.println("清除session"+en.getKey()+"功能对应表");
                functionMap.remove(en.getKey());
                ens.remove(en);
            }
        }

    }
}
