package com.datanew.action;

import cn.com.gsoft.core.www.client.FrameCache;

import com.datanew.dto.Result;
import com.datanew.model.BaseOperator;
import com.datanew.model.BbAttachment;
import com.datanew.service.CommonService;
import com.datanew.service.LogOutService;
import com.datanew.service.LoginService;
import com.datanew.util.TestCase;
import com.datanew.util.ZipUtils;
import com.hzzk.common.dto.OperatorDTO;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tools.zip.ZipOutputStream;
import org.dom4j.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping({"/login"})
@Controller
public class LoginController {

  @Resource
  private LoginService loginService;

  @Resource
  private LogOutService logOutService;

  @Resource
  private CommonService commonService;

  @ResponseBody
  @RequestMapping({"getJczlOperator"})
  public Map getJczlOperator(String userCode) {
    String name = this.loginService.getJczlOperator(userCode);
    Map map = new HashMap();
    map.put("name", name);
    return map;
  }

  @RequestMapping({"logIn"})
  public void logIn(HttpServletRequest request, HttpServletResponse response) {
    try {
      this.loginService.logIn(request, response);
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println(e.getMessage());
      String err = "<result><errMsg>"+e.getMessage()+"</errMsg></result>";
      try {
        response.setContentType("text/html");
        response.getWriter().println(err);
      } catch (IOException e1) {
        e1.printStackTrace();
      }
    }
  }

  @ResponseBody
  @RequestMapping({"getUserInfo"})
  public Object getUserInfo(HttpServletRequest req, HttpServletResponse response) {
    String userkey = req.getParameter("currkey");
    Map result = null;
    try {
      result = this.loginService.setUserInfo(req, userkey);
    } catch (DocumentException e) {
      e.printStackTrace();
    }
    return result;
  }

  @RequestMapping({"logOut"})
  public void logOut(HttpServletRequest request, HttpServletResponse response) {
    this.logOutService.logOut(request, response);
  }


  @ResponseBody
  @RequestMapping({"getOperator"})
  public Map getOperator(String userCode) {
    String name = this.loginService.getOperator(userCode);
    Map map = new HashMap();
    map.put("name", name);
    return map;
  }

  @RequestMapping("excelFile")
  @ResponseBody
  public Result excelFile(MultipartFile fileUpLoad, String remark, HttpServletResponse response) {
    try {
      loginService.setExcelFile(fileUpLoad, remark);
      return new Result(true, "上传成功");
    } catch (Exception e) {
      e.printStackTrace();
      return new Result(true, "上传失败");
    }
  }

  @ResponseBody
  @RequestMapping({"downloadChrome"})
  public void downloadChrome(HttpServletRequest request, HttpServletResponse response) throws Exception {
    loginService.downloadChrome(request, response);
  }

  @ResponseBody
  @RequestMapping({"downloadChromeLitter"})
  public void downloadChromeLitter(HttpServletRequest request, HttpServletResponse response) throws Exception {
    loginService.downloadChromeLitter(request, response);
  }

  @ResponseBody
  @RequestMapping({"downloadModelEssay"})
  public void downloadModelEssay(HttpServletRequest request, HttpServletResponse response) throws Exception {
    loginService.downloadModelEssay(request, response);
  }

  @ResponseBody
  @RequestMapping({"downloadInstruction"})
  public void downloadInstruction(HttpServletRequest request, HttpServletResponse response) throws Exception {
    loginService.downloadInstruction(request, response);
  }

  @ResponseBody
  @RequestMapping({"downloadReport"})
  public void downloadReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
    loginService.downloadReport(request, response);
  }
  /*
    新加： ca登录
   */
  @ResponseBody
  @RequestMapping({"fLoginByCA"})
  public Object fLoginByCA(HttpServletRequest request, HttpServletResponse response) {
    return loginService.fLoginByCA(request, response);
  }
}