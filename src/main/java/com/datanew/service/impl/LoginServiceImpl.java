package com.datanew.service.impl;

import cn.com.gsoft.core.www.client.FrameCache;
import cn.com.gsoft.core.www.client.servlet.LoginServlet;

import com.datanew.dao.BaseDao;
import com.datanew.dto.Result;
import com.datanew.model.BaseOperator;
import com.datanew.service.LoginService;
import com.datanew.util.ConfigureParser;
import com.datanew.util.MD5Util;
import com.hzzk.common.Config;
import com.hzzk.common.dto.OperatorDTO;
import com.hzzk.common.exception.BusinessException;
import com.hzzk.common.remote.Remote;
import com.hzzk.common.util.Encoder;

import java.io.*;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hzzk.common.util.LoginServletByCA;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("loginService")
public class LoginServiceImpl extends LoginServlet
  implements LoginService
{

  @Resource
  private BaseDao baseDao;

  public String getJczlOperator(String userCode) {
    String name = "";
    String sql = "select t.name from jczl.operator t where code = '" + userCode + "' ";
    List list = baseDao.selectBySql(sql);
    if (list.size()>0){
      name = (String) list.get(0);
    }

    return name;
  }

  public void logIn(HttpServletRequest request, HttpServletResponse response) {
    try {
      doPost(request, response);
    } catch (ServletException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  public Map setUserInfo(HttpServletRequest request,String userKey) throws DocumentException {
    HashMap resultMap = new HashMap();
    class Depart {
      List<Map> li = new ArrayList<Map>();

      List<Map> getMenu(String x) throws DocumentException {
        x = x.substring(x.indexOf("<actionSet"),x.length());
        Document doc = DocumentHelper.parseText(x);
        List<Element> menus = doc.selectNodes("/actionSet/treeNode");
        depart(menus, "");
        return li;
      }

      void depart(List<Element> list, String pid) {
//                Map session = FrameCache.getSession(userKey);
        for (Element e : list) {
          List<Element> listNextLevel = e.elements();
          Map ma = new HashMap();
          String id = e.attributeValue("id");
          String name = e.attributeValue("name");
          String href = e.attributeValue("href");
          String code = e.attributeValue("code");
          ma.put("id", id);
          ma.put("name", name);
          ma.put("url", href);
          ma.put("code", code);
//                ma.put("icon","");
          if (pid != null && !"".equals(pid)) {
            ma.put("pId", pid);
          } else {
            ma.put("pId", -1);
          }
//                ma.put("isOpen","");
          li.add(ma);
          if (listNextLevel.size() > 0) {

            String canselected = e.attributeValue("canselected");
            depart(listNextLevel, e.attributeValue("id"));
          }
        }
      }
    }
    Map session = FrameCache.getSession(userKey);
    OperatorDTO operator = (OperatorDTO) session.get("user_context");
    String returnStr = Remote.getRemoteFunctionService().getMenuByOperGuId(operator.getGuId(), Config.getAttribute("application.code"), Integer.parseInt( Config.getAttribute("application.setid")));
    System.out.println("菜单报文:"+returnStr);
    List<Map>menus=new Depart().getMenu(returnStr);

    resultMap.put("success", Boolean.valueOf(true));
    resultMap.put("loginuser", operator);
    resultMap.put("usermenus", menus);

    return resultMap;

  }


  public String getOperator(String userCode) {
    String hql = "from BaseOperator where loginname =?";
    List values = new ArrayList();
    values.add(userCode);
    BaseOperator userinfo = (BaseOperator)this.baseDao.loadByHql(hql, values);
    String name = "";
    if (userinfo != null)
    {
      name = userinfo.getUsername();
    }
    return name;
  }

  public boolean setLocalLogin(HttpServletRequest request, HttpServletResponse response) {
    String code = request.getParameter("operatorCode");
    String password = request.getParameter("password");
    HttpSession session = request.getSession();
    String hql = "from BaseOperator  where loginname =? and password=? ";
    List values = new ArrayList();
    values.add(code);
    values.add(MD5Util.MD5(password));
    BaseOperator userinfo = (BaseOperator)this.baseDao.loadByHql(hql, values);
    if (userinfo == null) {
      return false;
    }
    String sessionId = session.getId();
    try {
      String key = Encoder.str2md5(code + "@" + session.getId() + ":" + InetAddress.getLocalHost().getHostAddress());
      if (FrameCache.checkUserKey(key)) {
        key = key + "_01";
      }
      session.setAttribute("current_user_logon_key", key);
      for (Enumeration e = session.getAttributeNames(); e.hasMoreElements(); )
      {
        String name = (String)e.nextElement();
        FrameCache.putSession(key, name, session.getAttribute(name));
        session.removeAttribute(name);
      }
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.print("<result>");
      out.print("<key>");
      out.print(key);
      out.print("</key>");
      out.print("<sessionId>");
      out.print(sessionId);
      out.print("</sessionId>");
      out.print("</result>");
      out.flush();
      out.close();
    } catch (IOException e) {
      throw new BusinessException(e.getMessage());
    }
    String menusql = "select distinct new map(m.menuid as MENUID,m.menuname as MENUNAME ,m.menutype as MENUTYPE,m.parentid as PARENTID,m.menuurl as MENUURL,m.enable as ENABLE,m.menuicon as MENUICON, m.ordernum as ORDERNUM) from BaseOperatortopost op, BasePosttomenu pm, BaseMenu m where op.postid = pm.postid and pm.menuid = m.menuid and m.enable=1 and m.menutype=1 and op.userid = '" + userinfo.getUserid() + "' order by m.ordernum, m.menuid ";
    List menulist = this.baseDao.selectByHql(menusql);
    Map indexmenu = new HashMap();
    indexmenu.put("ENABLE", Integer.valueOf(1));
    indexmenu.put("MENUNAME", "首页");
    indexmenu.put("MENUID", "11");
    indexmenu.put("MENUICON", "");
    indexmenu.put("MENUURL", "indexcontent.jsp");
    menulist.add(0, indexmenu);
    userinfo.setLastLoginTime(userinfo.getLogintime());
    userinfo.setLogintime(new Date());
    this.baseDao.update(userinfo);
    session.setAttribute("LOGINUSER", userinfo);
    session.setAttribute("USERMENUS", menulist);
    Long count = this.baseDao.getCountBySQL("select count(1) from Bb_Report o,Bb_ReportToUser u where u.reportid = o.guid and o.enable='1' and u.userid = " + userinfo.getUserid(), null);
    Long count1 = this.baseDao.getCountBySQL("select count(1) from Base_Posttomenu t,Base_Operatortopost o where o.postid=t.postid and o.userid=" + userinfo.getUserid() + " and t.menuid='23'", null);
    Map map = new HashMap();
    if (count1.longValue() > 0L) {
      map.put("iswork", Boolean.valueOf(true));
      map.put("count", count);
    } else {
      map.put("iswork", Boolean.valueOf(false));
    }
    session.setAttribute("USERWORK", map);
    return true;
  }


	public void setExcelFile(MultipartFile file,String remark) throws Exception{
		String code = "";
        List obj = baseDao.selectBySql("select max(businessno) from  business_record");        
        if(obj.get(0)==null){
            code = "pc_"+ConfigureParser.getPropert("Curve_Region")+"000001";
        }else{
        	Long addno = Long.valueOf(obj.get(0).toString().substring(3));
            code = "pc_"+(addno+1)+"";
        }
        baseDao.executeBySql("insert into business_record (guid,businessno,isrecord,remark,businessdate) values (business_record_sequence.nextval,'"+code+"','0','"+remark+"',to_date(to_char(sysdate,'yyyy-mm-dd'),'yyyy-mm-dd'))");
		try {  
          // 构造 Workbook 对象，execelFile 是传入文件路径(获得Excel工作区)  
          Workbook book = null;  
          try {  
              // Excel 2007获取方法  
              book = new XSSFWorkbook(file.getInputStream());  
          } catch (Exception ex) {  
              // Excel 2003获取方法  
              book = new HSSFWorkbook(file.getInputStream());  
          }  
            
          // 读取表格的第一个sheet页  
          Sheet sheet = book.getSheetAt(0);  
          // 定义 row、cell  
          Row row;  
          String[] cell;  
          String t = "";
          // 总共有多少行,从0开始  
          int totalRows = sheet.getLastRowNum() ;  
          // 循环输出表格中的内容,首先循环取出行,再根据行循环取出列  
          for (int i = 1; i <= totalRows; i++) {
          	
              row = sheet.getRow(i);  
              // 处理空行  
              if(row == null){  
                  continue ;  
              }  
              // 总共有多少列,从0开始  
              int totalCells = row.getLastCellNum() ; 
              cell = new String[totalCells];
              for (int j = row.getFirstCellNum(); j < totalCells; j++) {  
                  // 处理空列  
                  if(row.getCell(j) == null){  
                  	cell[j]=""; 
                  }  
                  // 通过 row.getCell(j).toString() 获取单元格内容  
                  cell[j] = row.getCell(j).toString();  
              }
              baseDao.executeBySql("insert into zfls (pc,year,dwcode,dwname,billno,fcode,fname,ecode,ename,tzmc,totalmoney) values ('"+code+"','"+cell[1]+"','"+cell[2]+"','"+cell[3]+"','"+cell[4]+"','"+cell[5]+"','"+cell[6]+"','"+cell[7]+"','"+cell[8]+"','"+cell[9]+"','"+cell[10]+"')");
          }  
      } catch (FileNotFoundException e) {  
          e.printStackTrace();  
      } catch (IOException e) {  
          e.printStackTrace();  
      }  
	}

  @Override
  public void downloadChrome(HttpServletRequest request, HttpServletResponse response) throws Exception{
///       String filename="chrome64_installer.zip";
    String filename="49.0.2623.112_chrome_installer.exe";
    //设置文件MIME类型
    response.setContentType("application/octet-stream");
    //设置Content-Disposition
    response.setHeader("Content-Disposition", "attachment;filename="+new String(filename.replaceAll(" ", "").getBytes("utf-8"),"iso8859-1"));

    String itempath=request.getSession().getServletContext().getRealPath("/");

    ServletOutputStream outputStream =response.getOutputStream() ;

    InputStream inputStream = new FileInputStream(new File(itempath+"/WEB-INF/doc/49.0.2623.112_chrome_installer.exe"));
    byte[] buffer = new byte[1024*1024];
    int i = -1;
    while ((i = inputStream.read(buffer)) != -1) {
      outputStream.write(buffer, 0, i);
    }
    inputStream.close();
    outputStream.flush();
    outputStream.close();
  }

  @Override
  public void downloadChromeLitter(HttpServletRequest request, HttpServletResponse response) throws Exception {
    ///       String filename="chrome64_installer.zip";
    String filename="69.0.3497.81_chrome32_stable_windows_installer.exe";
    //设置文件MIME类型
    response.setContentType("application/octet-stream");
    //设置Content-Disposition
    response.setHeader("Content-Disposition", "attachment;filename="+new String(filename.replaceAll(" ", "").getBytes("utf-8"),"iso8859-1"));

    String itempath=request.getSession().getServletContext().getRealPath("/");

    ServletOutputStream outputStream =response.getOutputStream() ;

    InputStream inputStream = new FileInputStream(new File(itempath+"/WEB-INF/doc/69.0.3497.81_chrome32_stable_windows_installer.exe"));
    byte[] buffer = new byte[1024*1024];
    int i = -1;
    while ((i = inputStream.read(buffer)) != -1) {
      outputStream.write(buffer, 0, i);
    }
    inputStream.close();
    outputStream.flush();
    outputStream.close();
  }

  @Override
  public void downloadModelEssay(HttpServletRequest request, HttpServletResponse response) throws Exception {
    ///       String filename="chrome64_installer.zip";
    String filename="itemMonitor.pdf";
    //设置文件MIME类型
    response.setContentType("application/octet-stream");
    //设置Content-Disposition
    response.setHeader("Content-Disposition", "attachment;filename="+new String(filename.replaceAll(" ", "").getBytes("utf-8"),"iso8859-1"));

    String itempath=request.getSession().getServletContext().getRealPath("/");

    ServletOutputStream outputStream =response.getOutputStream() ;

    InputStream inputStream = new FileInputStream(new File(itempath+"/WEB-INF/doc/itemMonitor.pdf"));
    byte[] buffer = new byte[1024*1024];
    int i = -1;
    while ((i = inputStream.read(buffer)) != -1) {
      outputStream.write(buffer, 0, i);
    }
    inputStream.close();
    outputStream.flush();
    outputStream.close();
  }

  @Override
  public void downloadInstruction(HttpServletRequest request, HttpServletResponse response) throws Exception {
    ///       String filename="chrome64_installer.zip";
    String filename="opratorInstruction.doc";
    //设置文件MIME类型
    response.setContentType("application/octet-stream");
    //设置Content-Disposition
    response.setHeader("Content-Disposition", "attachment;filename="+new String(filename.replaceAll(" ", "").getBytes("utf-8"),"iso8859-1"));

    String itempath=request.getSession().getServletContext().getRealPath("/");

    ServletOutputStream outputStream =response.getOutputStream() ;

    InputStream inputStream = new FileInputStream(new File(itempath+"/WEB-INF/doc/opratorInstruction.doc"));
    byte[] buffer = new byte[1024*1024];
    int i = -1;
    while ((i = inputStream.read(buffer)) != -1) {
      outputStream.write(buffer, 0, i);
    }
    inputStream.close();
    outputStream.flush();
    outputStream.close();
  }

  @Override
  public void downloadReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
    ///       String filename="chrome64_installer.zip";
    String filename="Report.rar";
    //设置文件MIME类型
    response.setContentType("application/octet-stream");
    //设置Content-Disposition
    response.setHeader("Content-Disposition", "attachment;filename="+new String(filename.replaceAll(" ", "").getBytes("utf-8"),"iso8859-1"));

    String itempath=request.getSession().getServletContext().getRealPath("/");

    ServletOutputStream outputStream =response.getOutputStream() ;

    InputStream inputStream = new FileInputStream(new File(itempath+"/WEB-INF/doc/Report.rar"));
    byte[] buffer = new byte[1024*1024];
    int i = -1;
    while ((i = inputStream.read(buffer)) != -1) {
      outputStream.write(buffer, 0, i);
    }
    inputStream.close();
    outputStream.flush();
    outputStream.close();
  }

  /*
      ca登录
     */
  @Override
  public Result fLoginByCA(HttpServletRequest request, HttpServletResponse response){
    Result result = new Result();
    try {
      LoginServletByCA.loginByCA(request,response);
      result.setSuccess(true);
      result.setContent("登陆成功");
    }  catch (Exception e) {
      e.printStackTrace();
      result.setSuccess(true);
      result.setContent("登陆失败");
    }
    return result;
  }
}