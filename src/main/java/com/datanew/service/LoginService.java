package com.datanew.service;

import com.datanew.dto.Result;
import org.dom4j.DocumentException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public abstract interface LoginService {
  public abstract String getJczlOperator(String paramString);

  public abstract void logIn(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse);

  public abstract Map setUserInfo(HttpServletRequest paramHttpServletRequest, String userkey) throws DocumentException;


  public abstract String getOperator(String paramString);

  public abstract boolean setLocalLogin(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse);


  /**
   * 保存上传的文件
   *
   * @param file
   */
  public void setExcelFile(MultipartFile file, String remark) throws Exception;

  /*
    下载Chrome64浏览器方法
   */
  void downloadChrome(HttpServletRequest request, HttpServletResponse response) throws Exception;

  /*
   下载Chrome32浏览器方法
  */
  void downloadChromeLitter(HttpServletRequest request, HttpServletResponse response) throws Exception;

  /*
    下载监控表范文
  */
  void downloadModelEssay(HttpServletRequest request, HttpServletResponse response) throws Exception;

  /*
    下载用户操作指南
  */
  void downloadInstruction(HttpServletRequest request, HttpServletResponse response) throws Exception;

  /*
    下载监控自评考核表。
  */
  void downloadReport(HttpServletRequest request, HttpServletResponse response) throws Exception;

  /*
    ca登录
   */
  Result fLoginByCA(HttpServletRequest request, HttpServletResponse response);
}