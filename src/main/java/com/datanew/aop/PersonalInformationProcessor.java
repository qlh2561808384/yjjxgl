package com.datanew.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lustin on 2016/2/24.
 */
//@Aspect
//@Component
//@Order(1)
public class PersonalInformationProcessor {

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Around("(execution(* com.datanew.service.impl.ServicesServiceImpl.*(..)))")
    public Object doAround(ProceedingJoinPoint pjp)  {
        Object result=null;
        try {
            result= pjp.proceed();
            if(result!=null){
                if(result.toString().contains("PAYERSFZ")){
                    Document doc=null;
                    try{
                        doc= DocumentHelper.parseText(result.toString());
                        List<Element>identitys=doc.selectNodes("//PAYERSFZ");
                        List<Element>params=doc.selectNodes("//PARAM[@NAME='PAYERSFZ']");
                        if(identitys==null){
                        	identitys= new ArrayList();
                        }
                        if(params!=null){
                        	 identitys.addAll(params);
                        }
                       
                        for(Element e:identitys){
                            if(e.getTextTrim()!=null&&!"".equals(e.getTextTrim())){
                                String realId=e.getTextTrim();
                                if(realId.length()>=2){
                                	  StringBuffer idBu=new StringBuffer(realId);
                                      idBu.replace(1,idBu.length()-1,realId.substring(1,realId.length()-1).replaceAll(".","*"));
                                      e.setText(idBu.toString());
                                }
                            }

                        }
                        


                    }catch (DocumentException e){
                        e.printStackTrace();
                    }
                    return doc.asXML();
                }
                if(result.toString().contains("NAME")){
                    Document doc=null;
                    try{
                        doc= DocumentHelper.parseText(result.toString());
                        List<Element>names=doc.selectNodes("//PAYERINFO/NAME");
                        if(names==null){
                        	names=new ArrayList();
                        }
                        
                       
                       for(Element e:names){
                            if(e.getTextTrim()!=null&&!"".equals(e.getTextTrim())){
                                String realName=e.getTextTrim();
                                String name = "*" + realName.substring(1);
                                e.setText(name.toString());
                            }

                        }
                        


                    }catch (DocumentException e){
                        e.printStackTrace();
                    }
                    return doc.asXML();
                }
                if(result.toString().contains("TEL")||result.toString().contains("MOBILE")){
                    Document doc=null;
                    try{
                        doc= DocumentHelper.parseText(result.toString());
                        List<Element>tels=doc.selectNodes("//PAYERINFO/TEL");
                        List<Element>mobiles=doc.selectNodes("//PAYERINFO/MOBILE");
                        List<Element>params=doc.selectNodes("//PARAM[@NAME='PAYERMOBILE']");
                        if(tels==null){
                        	tels=new ArrayList();
                        }
                        if(mobiles!=null){
                        	tels.addAll(mobiles);
                        }
                        
                        if(params!=null){
                        	tels.addAll(params);
                        }
                        
                        
                        for(Element e:tels){
                            if(e.getTextTrim()!=null&&!"".equals(e.getTextTrim())){
                                String realTel=e.getTextTrim();
                                if(realTel.length()>=6){
                                	int num = realTel.length()-4;
                            		String str=""; 
                            		for (int i = 0; i < num; i++) {
                        				str+="*";
                        			}
                                    String returnstr = realTel.substring(0,2) + str + realTel.substring(realTel.length()-2);
                            		return returnstr; 
                                }
                            }

                        }
                        


                    }catch (DocumentException e){
                        e.printStackTrace();
                    }
                    return doc.asXML();
                }
            }

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return result;
    }

    public static void main(String[] args) throws DocumentException {
        String xml="<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?> \n" +
                "<Test>\n" +
                "<cell><data type=\"String\">Alpha</data></cell> \n" +
                "<cell><data type=\"Number\">100</data></cell> \n" +
                "<cell><data type=\"Number\">200</data></cell>\n" +
                "<cell><data type=\"Boolean\">true</data></cell> \n" +
                "<as></as>"+
                "</Test> ";
        Document doc=DocumentHelper.parseText(xml);
        List<Element> ass=doc.selectNodes("//cell/data[@type='String']");
        System.out.println(ass.size());

    }
}
