package com.datanew.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.datanew.utils.CompHelper;

public class TestCase {
    public static byte[] getBytes(String filePath){  
        byte[] buffer = null;  
        try {  
            File file = new File(filePath);  
            FileInputStream fis = new FileInputStream(file);  
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);  
            byte[] b = new byte[1000];  
            int n;  
            while ((n = fis.read(b)) != -1) {  
                bos.write(b, 0, n);  
            }  
            fis.close();  
            bos.close();  
            buffer = bos.toByteArray();  
        } catch (FileNotFoundException e) {  
        e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return buffer;  
	}  
    /**
	 * 根据byte数组，生成文件
	 */
	public static void getFile(byte[] bfile, String filePath,String fileName) {
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		File file = null;
		try {
			File dir = new File(filePath);
			if(!dir.exists()&&dir.isDirectory()){//判断文件目录是否存在
				dir.mkdirs();
			}
			file = new File(filePath+"\\"+fileName);
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			bos.write(bfile);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
//	public String testCompress() {
//		byte[]  bytes = getBytes("D:/test.xml");
//		String xml = CompHelper.compressXml(bytes);
//		return xml;
//	}
//	
//	@Test
//	public void testdecodeCompress() {
//		String xmlString = testCompress();
//		byte[] bytes = CompHelper.decodeCompressXml(xmlString);
//		getFile(bytes,"D:/test","test2.xls");
//		
//	}
	
	//URLEncoder编码
	public static String urlEncode(String str, String enc) {
		  if (str == null)
		   return "";
		  try {
		   return java.net.URLEncoder.encode(str, enc);
		  } catch (UnsupportedEncodingException ex) {
		   return str;
		  }
		 }
	
	//1、传递服务器IP、端口，报表ID、authid、参数等，获取到结果，xml格式
	public String getXmlbyUrl(String fileUrl,String infile,String sj) {
		
	    /*String dn_authId=urlEncode("anonymous_admin","UTF-8"); // anonymous_地税实例 URLEncoder编码，下同
		String dn_targetVolume=urlEncode("Z3psZ2w","UTF-8");   // 地税实例
		String dn_fileAlias=urlEncode("问题类别","UTF-8"); //问题类别，报表标题会作为导出的Excel工作表名称。
		String dn_fileName="hte_161129093715938";  //报表内部名称 hte_161129093715938
		String dn_variants=urlEncode("bm","UTF-8")+"=1";    //日期=20090108，报表没参数就不用传了
		*/
		//String reqUrl = "http://127.0.0.1:8081/HappyServer/ExportReportResult?"+"authId=anonymous_%E5%9C%B0%E7%A8%8E%E5%AE%9E%E4%BE%8B"
		//+"&targetVolume=%E5%9C%B0%E7%A8%8E%E5%AE%9E%E4%BE%8B"+"&fileAlias=%E5%8F%82%E6%95%B0%E6%8A%A5%E8%A1%A8_%E6%97%A5%E6%9C%9F"
		//+"&fileName=hte_110216205149781"+"&variants=%E6%97%A5%E6%9C%9F=20090108";
		
		//构造后台直接导出报表内容的URL接口
		String reqUrl=fileUrl.replace("hrServlet", "ExportReportResult");
		int ind = reqUrl.indexOf("YHM=");
		String trString = reqUrl.substring(0,ind+4);
		reqUrl=trString+""+infile+";XZQH=;GKCS=;DW=;SJ="+sj;
		System.out.println("reqUrl:"+reqUrl);
		

		HttpURLConnection url_con = null;
	    String responseContent = null;
	        try
	        {
	            StringBuffer params = new StringBuffer();
	            String queryUrl = reqUrl;
	            int paramIndex = reqUrl.indexOf("?");

	            if (paramIndex > 0)
	            {
	                queryUrl = reqUrl.substring(0, paramIndex);
	                String parameters = reqUrl.substring(paramIndex + 1, reqUrl
	                        .length());
	                String[] paramArray = parameters.split("&");
	                for (int i = 0; i < paramArray.length; i++)
	                {
	                    String string = paramArray[i];
	                    int index = string.indexOf("=");
	                    if (index > 0)
	                    {
	                        String parameter = string.substring(0, index);
	                        String value = string.substring(index + 1, string
	                                .length());
	                        params.append(parameter);
	                        params.append("=");
	                        params.append(URLEncoder.encode(value,
	                                "UTF-8"));
	                        params.append("&");
	                    }
	                }

	                params = params.deleteCharAt(params.length() - 1);
	            }
	            //System.out.println(queryUrl);
	            URL url = new URL(queryUrl);
	            url_con = (HttpURLConnection) url.openConnection();
	            url_con.setRequestMethod("GET");
	            //System.setProperty("sun.net.client.defaultConnectTimeout", String
	              //      .valueOf(10000));// （单位：毫秒）jdk1.4换成这个,连接超时
	            //System.setProperty("sun.net.client.defaultReadTimeout", String
	                //    .valueOf(10000)); // （单位：毫秒）jdk1.4换成这个,读操作超时
	            url_con.setConnectTimeout(5000);//（单位：毫秒）jdk
	            // 1.5换成这个,连接超时
	            url_con.setReadTimeout(5000);//（单位：毫秒）jdk 1.5换成这个,读操作超时
	            url_con.setDoOutput(true);
	            byte[] b = params.toString().getBytes();
	            url_con.getOutputStream().write(b, 0, b.length);
	            url_con.getOutputStream().flush();
	            url_con.getOutputStream().close();
	            InputStream in = url_con.getInputStream();
	            BufferedReader rd = new BufferedReader(new InputStreamReader(in, "UTF-8"));
	            String tempLine = rd.readLine();
	            StringBuffer temp = new StringBuffer();
	            String crlf=System.getProperty("line.separator");
	            while (tempLine != null)
	            {
	                temp.append(tempLine);
	                temp.append(crlf);
	                tempLine = rd.readLine();
	            }
	            responseContent = temp.toString();
	            rd.close();
	            in.close();
	        }
	        catch (IOException e)
	        {
	          responseContent="0";
	        }
	        finally
	        {
	            if (url_con != null)
	            {
	                url_con.disconnect();
	            }
	        }
	        
		return responseContent;
	}
	
	//2、根据获取到的xml内容（包括了excel文件内容）
	public byte[] decodeXML(String responseContent) throws UnsupportedEncodingException{
		
		SAXReader reader= new SAXReader();
		byte[] bytes = null;
		try {
			String xmlDoc=responseContent;  //调用url接口，返回xml内容，包括报表结果。
			Document doc= reader.read(new ByteArrayInputStream(xmlDoc.getBytes("UTF-8")));
			Element root = doc.getRootElement();
			String  xml = root.element("ExcelFile").element("R").asXML(); //读取xml内容里的ExcelFile节点信息。
			bytes = CompHelper.decodeCompressXml(xml); //解压缩
			/*getFile(bytes,"D:/",fileName+".xls");  //最终Excel文件的保存路径和文件名
            System.out.println("导出完成!");*/
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bytes;
	}
	
	/*public static void main(String args[]){
		try {
			new TestCase().decodeXML();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
		
}
