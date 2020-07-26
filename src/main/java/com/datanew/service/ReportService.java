package com.datanew.service;

import com.datanew.dto.Pages;
import com.datanew.dto.Result;
import com.datanew.model.BaseOperator;
import com.datanew.model.BbReport;
import com.datanew.util.BusinessException;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public abstract interface ReportService
{
  public abstract Map findReportInfo(String paramString);

  public abstract void saveReportInfo(BaseOperator paramBaseOperator, BbReport paramBbReport);

  public abstract boolean deleteRopert(String paramString)
    throws BusinessException;

  public abstract void updateRopert(String paramString1, String paramString2);

  public abstract String updateEnable(String paramString);

  public abstract String updateTbEnable(String paramString);

  public abstract void updateIsupload(String paramString);

  public abstract void updateIsSelectReport(String paramString);

  public abstract boolean hasFillData(String paramString);

  public abstract String saveReport(String paramString1, String paramString2, Result paramResult)
    throws Exception;

  public abstract void saveUserToReport(String paramString1, String paramString2, String paramString3, String paramString4)
    throws Exception;

  public abstract void saveSendBack(String paramString1, String paramString2, String paramString3, String paramString4)
    throws Exception;

  public abstract void saveComplete(String paramString1, String paramString2, String paramString3)
    throws Exception;

  public abstract boolean saveSendMessage(String paramString1, String paramString2);

  public abstract Pages getFillInfo(Pages paramPages, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6);

  public abstract Pages getFileInfo(Pages paramPages, String paramString1, String paramString2, String paramString3);

  public abstract void saveViewPass(String paramString1, String paramString2, String paramString3)
    throws Exception;

  public abstract boolean deleteRopertFile(String paramString)
    throws BusinessException;

  public abstract String getProgress(String paramString1, String paramString2, String paramString3);

  public abstract String getTbEnable(String paramString1, String paramString2);

  public abstract List findReportUserTree();

  public abstract String getTbsj(String paramString1, String paramString2);
  
  public abstract Pages getExcelInfo(Pages page,String isrecord,String startdate,String enddate);
  
  public abstract Pages getExcelMxInfo(Pages page, String guid);
  
  public abstract void setInfoRecord(String businessno)
		    throws BusinessException;
  
  public abstract void deleteExcelFile(String businessno);

}