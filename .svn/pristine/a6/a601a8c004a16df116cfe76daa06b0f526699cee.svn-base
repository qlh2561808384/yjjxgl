package com.datanew.util;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Lustin on 2015/11/24.
 */
public class JsonParser {
        public static <T> List<T> convertToList(String json,Class<T[]> cls){
            List<T> list = null;  //目标list
            T[] arr = null; //ObjectMapper无法将json直接解析成对象的list，
            //因此需要先将其解析成对象数组，
            //再通过Arrays.asList转换成对象List
            try {
                ObjectMapper mapper = new ObjectMapper();
                list = Arrays.asList(mapper.readValue(json, cls)); //执行转换
            }catch (JsonParseException e){
                e.printStackTrace();
            }catch (JsonMappingException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
            return list;
        }
        
        
        public static <T> List<T> readListValue(String json, Class<T> tClazz){  
            return JSON.parseArray(json, tClazz);  
        } 
        
//    public static <T>T convertToList(String json,Class<T[]> cls){
//        T[] arr = null; //ObjectMapper无法将json直接解析成对象的list，
//        //因此需要先将其解析成对象数组，
//        //再通过Arrays.asList转换成对象List
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            list = Arrays.asList(mapper.readValue(json, cls)); //执行转换
//        }catch (JsonParseException e){
//            e.printStackTrace();
//        }catch (JsonMappingException e){
//            e.printStackTrace();
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//        return list;
//    }
}
