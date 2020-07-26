package com.datanew.action;

import com.datanew.dto.Pages;
import com.datanew.dto.Result;
import com.datanew.model.BaseOperator;
import com.datanew.model.BbReport;
import com.datanew.service.ReportService;
import com.datanew.task.TimerCheckOut;
import com.datanew.util.BusinessException;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping({"report"})
public class ReportController
{

  @Autowired
  ReportService reportService;

  @ResponseBody
  @RequestMapping({"findReportInfo"})
  public Map findReportInfo(String reportId)
  {
    return this.reportService.findReportInfo(reportId);
  }

  @RequestMapping({"saveReportInfo"})
  public void saveReportInfo(HttpSession session, HttpServletResponse res, BbReport b)
  {
    BaseOperator operator = (BaseOperator)session.getAttribute("LOGINUSER");
    try {
      this.reportService.saveReportInfo(operator, b);
      res.setContentType("text/html;charset=UTF-8");
      res.getWriter().write("{\"success\":true,\"msg\":\"保存成功\"}");
    } catch (Exception e) {
      res.setContentType("text/html;charset=UTF-8");
      try {
        res.getWriter().write("{\"success\":false,\"msg\":\"保存失败\"}");
      }
      catch (IOException e1) {
        e1.printStackTrace();
      }
    }
  }

  @ResponseBody
  @RequestMapping({"deleteRopert"})
  public Object deleteRopert(String reportid)
  {
    Result result = new Result();
    boolean t = false;
    try {
      t = this.reportService.deleteRopert(reportid);
      result.setSuccess(true);
      result.setContent("删除成功");
    } catch (BusinessException e) {
      result.setSuccess(t);
      result.setContent(e.getMessage());
    }
    return result;
  }

  @ResponseBody
  @RequestMapping({"updateRopert"})
  public Object updateRopert(String guid, String designName)
  {
    Result result = new Result();
    try {
      this.reportService.updateRopert(guid, designName);
      result.setSuccess(true);
      result.setContent("更新报表信息成功");
    } catch (Exception e) {
      result.setSuccess(false);
      result.setContent("更新报表信息失败");
    }
    return result;
  }

  @ResponseBody
  @RequestMapping({"enableReport"})
  public Object enableReport(String guid)
  {
    Result result = new Result();
    String str = "";
    try {
      str = this.reportService.updateEnable(guid);
      if ("1".equals(str)) {
        result.setSuccess(true);
        result.setContent("启用成功");
      } else {
        result.setSuccess(true);
        result.setContent("停用成功");
      }
    } catch (Exception e) {
      if ("1".equals(str)) {
        result.setSuccess(false);
        result.setContent("启用失败");
      } else {
        result.setSuccess(false);
        result.setContent("停用失败");
      }
    }
    return result;
  }

  @ResponseBody
  @RequestMapping({"tbenableReport"})
  public Object tbenableReport(String guid)
  {
    Result result = new Result();
    String str = "";
    try {
      str = this.reportService.updateTbEnable(guid);
      if ("1".equals(str)) {
        result.setSuccess(true);
        result.setContent("启用成功");
      } else {
        result.setSuccess(true);
        result.setContent("停用成功");
      }
    } catch (Exception e) {
      if ("1".equals(str)) {
        result.setSuccess(false);
        result.setContent("启用失败");
      } else {
        result.setSuccess(false);
        result.setContent("停用失败");
      }
    }
    return result;
  }

  @ResponseBody
  @RequestMapping({"isuploadReport"})
  public Object isuploadReport(String guid)
  {
    Result result = new Result();
    try {
      this.reportService.updateIsupload(guid);
      result.setSuccess(true);
      result.setContent("设置成功");
    } catch (Exception e) {
      result.setSuccess(false);
      result.setContent("设置失败");
    }
    return result;
  }

  @ResponseBody
  @RequestMapping({"isSelectReport"})
  public Object isSelectReport(String guid)
  {
    Result result = new Result();
    try {
      this.reportService.updateIsSelectReport(guid);
      result.setSuccess(true);
      result.setContent("设置成功");
    } catch (Exception e) {
      result.setSuccess(false);
      result.setContent("设置失败");
    }
    return result;
  }

  @ResponseBody
  @RequestMapping({"findFillData"})
  public Object findFillData(String tableName, String fillName)
  {
    Result result = new Result();
    boolean t = false;
    try {
      if ((fillName != null) && (!"".equals(fillName)))
        t = this.reportService.hasFillData(tableName);
      else
        t = true;
    }
    catch (Exception e) {
      t = true;
      result.setContent(e.getMessage());
    }
    result.setSuccess(t);
    return result;
  }

  @ResponseBody
  @RequestMapping({"savePublishReport"})
  public Object savePublishReport(String guid, String mode)
  {
    Result result = new Result();
    String reportFileName = "";
    try {
      reportFileName = this.reportService.saveReport(guid, mode, result);
      result.setSuccess(true);
      result.setContent("发布成功");
      result.setReportFileName(reportFileName);
    } catch (Exception e) {
      result.setSuccess(false);
      result.setContent(e.getMessage());
    }
    return result;
  }

  @ResponseBody
  @RequestMapping({"sendTask"})
  public Object sendTask(String userid, String module, String guid, String isMax, String remark)
  {
    Result result = new Result();
    try {
      this.reportService.saveUserToReport(userid, guid, isMax, remark);
      new TimerCheckOut().SendMessage(guid, module);
      result.setSuccess(true);
      result.setContent("发布任务成功");
    } catch (Exception e) {
      result.setSuccess(false);
      result.setContent("发布任务失败:" + e.getMessage());
    }
    return result;
  }

  @ResponseBody
  @RequestMapping({"sendMessage"})
  public Object sendMessage(String userid, String module, String reportid, String reportsj)
  {
    Result result = new Result();
    try {
      this.reportService.saveSendBack(userid, reportid, module, reportsj);
      result.setSuccess(true);
      result.setContent("退回成功");
    } catch (Exception e) {
      result.setSuccess(false);
      result.setContent(e.getMessage());
    }
    return result;
  }

  @ResponseBody
  @RequestMapping({"complete"})
  public Object complete(String userid, String reportid, String reportsj)
  {
    Result result = new Result();
    try {
      this.reportService.saveComplete(userid, reportid, reportsj);
      result.setSuccess(true);
      result.setContent("填报已完成");
    } catch (Exception e) {
      result.setSuccess(false);
      result.setContent(e.getMessage());
    }
    return result;
  }
  @ResponseBody
  @RequestMapping({"getFillInfo"})
  public Object getFillInfo(Pages page, String reportid, String divisionguid, String auditstatus, String entename, String userids, String sj, HttpSession session) {
    this.reportService.getFillInfo(page, reportid, divisionguid, auditstatus, entename, userids, sj);
    return page;
  }

  @ResponseBody
  @RequestMapping({"viewPass"})
  public Object viewPass(String userid, String reportid, String reportsj)
  {
    Result result = new Result();
    try {
      this.reportService.saveViewPass(userid, reportid, reportsj);
      result.setSuccess(true);
      result.setContent("审核通过");
    } catch (Exception e) {
      result.setSuccess(false);
      result.setContent(e.getMessage());
    }
    return result;
  }

  @ResponseBody
  @RequestMapping({"getFileInfo"})
  public Object getFileInfo(Pages page, String reportid, String userid, String reportsj, HttpSession session)
  {
    this.reportService.getFileInfo(page, reportid, userid, reportsj);
    return page;
  }

  @ResponseBody
  @RequestMapping({"deleteRopertFile"})
  public Object deleteRopertFile(String fileguid)
  {
    Result result = new Result();
    boolean t = false;
    try {
      t = this.reportService.deleteRopertFile(fileguid);
      result.setSuccess(true);
      result.setContent("删除成功");
    } catch (BusinessException e) {
      result.setSuccess(t);
      result.setContent(e.getMessage());
    }
    return result;
  }

  @ResponseBody
  @RequestMapping({"getProgress"})
  public Object getProgress(String reportId, String userid, String reportsj)
  {
    Result result = new Result();
    String t = this.reportService.getProgress(reportId, userid, reportsj);
    result.setSuccess(true);
    result.setContent(t);
    return result;
  }

  @ResponseBody
  @RequestMapping({"getTbsj"})
  public Object getTbsj(String reportId, String userid)
  {
    Result result = new Result();
    String t = this.reportService.getTbsj(reportId, userid);
    result.setSuccess(true);
    result.setContent(t);
    return result;
  }

  @ResponseBody
  @RequestMapping({"getTbEnable"})
  public Object getTbEnable(String reportId, String userid)
  {
    Result result = new Result();
    String t = this.reportService.getTbEnable(reportId, userid);
    result.setSuccess(true);
    result.setContent(t);
    return result;
  }

  @ResponseBody
  @RequestMapping({"findReportUserTree"})
  public List findReportUserTree()
  {
    return this.reportService.findReportUserTree();
  }
  
  @ResponseBody
  @RequestMapping({"getExcelInfo"})
  public Object getExcelInfo(Pages page,String isrecord,String startdate,String enddate, HttpSession session) {
    this.reportService.getExcelInfo(page, isrecord,startdate,enddate);
    return page;
  }
  
  @ResponseBody
  @RequestMapping({"getExcelMxInfo"})
  public Pages getExcelMxInfo(Pages page, String guid) {
	  this.reportService.getExcelMxInfo(page, guid);
	    return page;
  }
  
  @ResponseBody
  @RequestMapping({"infoRecord"})
  public Object infoRecord(Pages page, String businessno) {
	  Result result = new Result();
	  try {
	    this.reportService.setInfoRecord(businessno);
	    result.setSuccess(true);
	    result.setContent("操作成功");
	  } catch (BusinessException e) {
		  e.printStackTrace();
	    result.setSuccess(true);
	    result.setContent("操作失败");
	  }
	  return result;
  }
  @ResponseBody
  @RequestMapping({"deleteExcelFile"})
  public Object deleteExcelFile(String businessno)
  {
    Result result = new Result();
    try {
      this.reportService.deleteExcelFile(businessno);
      result.setSuccess(true);
      result.setContent("删除成功");
    } catch (Exception e) {
      e.printStackTrace();
      result.setSuccess(true);
      result.setContent("删除失败");
    }
    return result;
  }
}