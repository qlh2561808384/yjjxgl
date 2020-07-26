package com.datanew.service.impl;

import com.datanew.dto.Result;
import com.datanew.util.ConfigureParser;
import com.datanew.util.StringUtil;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * Created by Dell on 2016/10/27.
 */
//@Service("commonService")
public class ServiceServiceImpl{

   /* @Resource
    BaseDao baseDao;*/
    
    /**
     * mispos获取支付单信息
     */
	public void getMisposPayInfo(String noticeno, String tradecode, Result result) {
		String hql = "from MisposLog m where m.noticeno = '"+ noticeno +"'";
		/*List list = baseDao.selectByHql(hql);
		if (list.size() > 0) {
			result.setSuccess(true);
			result.setContent(XmlJSON.xml2JSON("<ROOT><IS_SUCCESS CODE='99'>该缴款单已缴款</IS_SUCCESS></ROOT>"));
			return;
		}*/
		
		Document doc = DocumentHelper.createDocument(); 
    	Element root = doc.addElement("ROOT");
    	Element payerinfo = root.addElement("PAYERINFO");
    	Element name = payerinfo.addElement("NAME");
    	Element payersfz = payerinfo.addElement("PAYERSFZ");
    	Element mobile = payerinfo.addElement("MOBILE");
    	Element tel = payerinfo.addElement("TEL");
    	Element address = payerinfo.addElement("ADDRESS");
    	Element email = payerinfo.addElement("EMAIL");
    	Element request = root.addElement("REQUEST");
    	Element no = request.addElement("PARAM").addAttribute("NAME","NOTICENO");
    	Element yc = request.addElement("PARAM").addAttribute("NAME","YWCODE");
    	Element td = request.addElement("PARAM").addAttribute("NAME","TRADECODE");
    	Element waycode = request.addElement("PARAM").addAttribute("NAME","WAYCODE");
    	Element isbiztrade = request.addElement("PARAM").addAttribute("NAME","ISBIZTRADE");
    	no.setText(StringUtil.formatNullString(noticeno));
    	td.setText(tradecode);
    	waycode.setText(ConfigureParser.getPropert("MISPOSCODE"));//MISPOS支付方式
    	isbiztrade.setText("");
		String adress = "";
		/*String returnString = BillClient.get_Bill_Client(adress,"Get_Pay_Info_With_Order_Flow", doc.asXML());
    	//String returnString = "<?xml version='1.0' encoding='GBK' standalone='yes'?><ROOT><IS_SUCCESS CODE='00'>成功</IS_SUCCESS><ERRMSG></ERRMSG><PAYERINFO><NAME></NAME><PAYERSFZ></PAYERSFZ><MOBILE></MOBILE><TEL></TEL><ADDRESS></ADDRESS><EMAIL></EMAIL></PAYERINFO><REQUEST><PARAM NAME='NOTICENO'>33000100117006832</PARAM><PARAM NAME='YWCODE'></PARAM><PARAM NAME='TRADECODE'>01</PARAM><PARAM NAME='WAYCODE'>07</PARAM><PARAM NAME='ISBIZTRADE'></PARAM></REQUEST><RESPONSE><RESULT><APP_PAY><USERID></USERID><REGICODE>330000</REGICODE><NOTICENO>33000100117006832</NOTICENO><PAYLISTNO>000201707310000005</PAYLISTNO><ORIGINALNOTICENO></ORIGINALNOTICENO><CHANNELNO>330001001</CHANNELNO><NOTICEDIS>6</NOTICEDIS><PAYER>恺恺</PAYER><PAYERSFZ></PAYERSFZ><MAKEDATE>20170731</MAKEDATE><MAKETIME>152414</MAKETIME><TOTALMONEY>0.01</TOTALMONEY><YWCODE></YWCODE><ENTERCODE>999985</ENTERCODE><ENTERNAME>浙江省财政厅（财政执收）</ENTERNAME><ISTRUENAME>0</ISTRUENAME><ISCOUNTOVERDUE>0</ISCOUNTOVERDUE><EXPIREDPAYDATE>20170803</EXPIREDPAYDATE><EXPIREDPAYTIME>235959</EXPIREDPAYTIME><PRINTINFO></PRINTINFO><FJ1>行政区划</FJ1><FJCONTENT1></FJCONTENT1><FJ2>说明</FJ2><FJCONTENT2></FJCONTENT2><FJ3>所属学校</FJ3><FJCONTENT3></FJCONTENT3><FJ4></FJ4><FJCONTENT4></FJCONTENT4><FJ5></FJ5><FJCONTENT5></FJCONTENT5><PROJECT><CHITCODE>00032923</CHITCODE><CHITNAME>捐赠收入</CHITNAME><METRICUNIT>元</METRICUNIT><CHCOUNT>1.0</CHCOUNT><CHARGESTANDARD>0.01</CHARGESTANDARD><CHMONEY>0.01</CHMONEY><ISEXISTSOVERDUE>0</ISEXISTSOVERDUE><MAXPAYMENT></MAXPAYMENT><PAYMENTRADIO></PAYMENTRADIO><STARTCALCDATE></STARTCALCDATE><BELONGCHITCODE>00032923</BELONGCHITCODE><BELONGCHITNAME>捐赠收入</BELONGCHITNAME></PROJECT></APP_PAY><ORDERFLOW><PAYNO>0002017073100000004</PAYNO><REGICODE>330000</REGICODE><PAYLISTNO>000201707310000005</PAYLISTNO><NOTICENO>33000100117006832</NOTICENO><PAYMONEY>0.01</PAYMONEY><ENTERCODE>999985</ENTERCODE><ENTERNAME>浙江省财政厅（财政执收）</ENTERNAME><TRADECODE>01</TRADECODE><TRADENAME>工商银行</TRADENAME><WAYCODE>07</WAYCODE><WAYNAME>刷卡支付</WAYNAME><CREATEDATE>20170731</CREATEDATE><CREATETIME>153405</CREATETIME><POSTACCOUNT>120201010015</POSTACCOUNT><PAYDATE></PAYDATE><PAYTIME></PAYTIME><LOSEDATE>20170731</LOSEDATE><LOSETIME>160359</LOSETIME><WRITEMAN></WRITEMAN><USERID></USERID></ORDERFLOW></RESULT></RESPONSE></ROOT>";
		if (StringUtil.isblank(returnString)) {
			result.setSuccess(false);
			result.setContent("统一支付平台获取支付单信息没响应  请重试或者联系管理员");
		} else {
			result.setSuccess(true);
			result.setContent(XmlJSON.xml2JSON(returnString));
		}*/
	}
}
