package com.datanew.util;

import java.util.List;
import java.util.Map;

import com.alibaba.druid.sql.ast.statement.SQLWithSubqueryClause.Entry;
import com.alibaba.fastjson.JSONArray;

import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

/**
 * 
* <p>Title: JSON-XML转换工具</p>
* <p>desc:
* <p>Copyright: Copyright(c)Gb 2012</p>
* @author http://www.ij2ee.com
* @time 上午8:20:40
* @version 1.0
* @since
 */
public class XmlJSON {
    private static final String STR_JSON = "{\"name\":\"Michael\",\"address\":{\"city\":\"Suzou\",\"street\":\" Changjiang Road \",\"postcode\":100025},\"blog\":\"http://www.ij2ee.com\"}";
    public static String xml2JSON(String xml){
        return new XMLSerializer().read(xml).toString();
    }
     
    public static String json2XML(String json){
        JSONObject jobj = JSONObject.fromObject(json);
        String xml =  new XMLSerializer().write(jobj);
        return xml;
    }
    public static List<Map<String,Object>> json2List(String json){
	    JSONArray jsonArray = JSONArray.parseArray(json);
	    List<Map<String,Object>> mapListJson = (List)jsonArray;
	    for (int i = 0; i < mapListJson.size(); i++) {
	        Map<String,Object> obj=mapListJson.get(i);
	         
	        for(java.util.Map.Entry<String, Object> entry : obj.entrySet()){
	            String strkey1 = entry.getKey();
	            Object strval1 = entry.getValue();
	        }
	    }
	    return mapListJson;
    }
    public static void main(String[] args) {
    	String webserviceReturn = "<?xml version=\"1.0\" encoding=\"GBK\" standalone=\"yes\"?><ROOT><IS_SUCCESS CODE=\"00\">成功</IS_SUCCESS><ERRMSG></ERRMSG><PAYERINFO><NAME></NAME><PAYERSFZ></PAYERSFZ><MOBILE></MOBILE><TEL></TEL><ADDRESS></ADDRESS><EMAIL></EMAIL></PAYERINFO><REQUEST><PARAM NAME=\"NOTICENO\">3300010012015110410000032</PARAM><PARAM NAME=\"YWCODE\"></PARAM><PARAM NAME=\"USERID\"></PARAM></REQUEST><RESPONSE><RESULT><DETAIL><REGICODE>330000 省本级</REGICODE><NOTICENO>3300010012015110410000032</NOTICENO><CHANNELNO>330001001</CHANNELNO><ORIGINALNOTICENO></ORIGINALNOTICENO><PAYER>章小小28</PAYER><PAYERSFZ>330724194612101819</PAYERSFZ><MAKEDATE>20151104</MAKEDATE><MAKETIME>125136</MAKETIME><TOTALMONEY>0.10</TOTALMONEY><YWCODE></YWCODE><ENTERCODE>999985</ENTERCODE><ENTERNAME>浙江省财政厅（财政执收）</ENTERNAME><NOTICEDIS>6</NOTICEDIS><ISTRUENAME>0</ISTRUENAME><EXPIREDPAYDATE></EXPIREDPAYDATE><EXPIREDPAYTIME></EXPIREDPAYTIME><ISCOUNTOVERDUE>0</ISCOUNTOVERDUE><PRINTINFO></PRINTINFO><FJ1></FJ1><FJCONTENT1></FJCONTENT1><FJ2></FJ2><FJCONTENT2></FJCONTENT2><FJ3></FJ3><FJCONTENT3></FJCONTENT3><FJ4></FJ4><FJCONTENT4>6230910799003081430</FJCONTENT4><FJ5></FJ5><FJCONTENT5></FJCONTENT5><PROJECT><CHITCODE>00032923</CHITCODE><CHITNAME>捐赠收入</CHITNAME><METRICUNIT>元</METRICUNIT><CHCOUNT>1.0</CHCOUNT><CHARGESTANDARD>0.10</CHARGESTANDARD><CHMONEY>0.10</CHMONEY><ISEXISTSOVERDUE>0</ISEXISTSOVERDUE><MAXPAYMENT></MAXPAYMENT><PAYMENTRADIO></PAYMENTRADIO><STARTCALCDATE></STARTCALCDATE><BELONGCHITCODE>00032923</BELONGCHITCODE><BELONGCHITNAME>捐赠收入</BELONGCHITNAME></PROJECT> <PROJECT><CHITCODE>00032923</CHITCODE><CHITNAME>捐赠收入</CHITNAME><METRICUNIT>元</METRICUNIT><CHCOUNT>2.0</CHCOUNT><CHARGESTANDARD>0.20</CHARGESTANDARD><CHMONEY>0.40</CHMONEY><ISEXISTSOVERDUE>0</ISEXISTSOVERDUE><MAXPAYMENT></MAXPAYMENT><PAYMENTRADIO></PAYMENTRADIO><STARTCALCDATE></STARTCALCDATE><BELONGCHITCODE>00032923</BELONGCHITCODE><BELONGCHITNAME>捐赠收入</BELONGCHITNAME></PROJECT></DETAIL></RESULT></RESPONSE></ROOT>";
		
        String xml = json2XML(STR_JSON);
        System.out.println("xml = "+xml);
        String json = xml2JSON(webserviceReturn);
        System.out.println("json="+json);
    }
}