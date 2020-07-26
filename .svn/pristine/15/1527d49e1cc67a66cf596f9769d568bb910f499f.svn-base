package com.datanew.action;

import cn.com.gsoft.core.www.client.FrameCache;
import com.datanew.dto.Pages;
import com.datanew.dto.Result;
import com.datanew.model.BaseOperator;
import com.datanew.service.ProService;
import com.hzzk.common.dto.OperatorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping({"/pro"})//作用在类上
public class ProController
{

  @Autowired
  private ProService proService;//按照字段注入

  @ResponseBody
  @RequestMapping({"getProReleaseLog"})//作用在方法上
  public Object getProReleaseLog(Pages page,String type) {
    this.proService.getProReleaseLog(page,type);
    return page;
  }

  @ResponseBody
  @RequestMapping({"saveBaseInfo"})
  public Object saveBaseInfo(String userKey,String year, String reportid, String reportname,String Remarks) {
    Result result = new Result();
    Map session = FrameCache.getSession(userKey);
    OperatorDTO operator = (OperatorDTO) session.get("user_context");
    try {
      this.proService.saveBaseInfo(year, reportid, reportname, operator, result,Remarks);
    } catch (Exception e) {
      e.printStackTrace();
      result.setSuccess(false);
      result.setContent(e.toString());

    }
    return result;
  }

  @ResponseBody
  @RequestMapping({"getProBase"})
  public Object getProBase(String userKey, Pages page, String search_year, String nodenum, String enterguid, String batch) {
    Map session = FrameCache.getSession(userKey);
    OperatorDTO operator = (OperatorDTO) session.get("user_context");
    this.proService.getProBase(operator, page, search_year, nodenum, enterguid, batch);
    return page;
  }
  @ResponseBody
  @RequestMapping({"getBasePerformance"})
  public Object getBasePerformance(String userKey, Pages page, String search_year, String nodenum, String enterguid, String batch) {
    Map session = FrameCache.getSession(userKey);
    OperatorDTO operator = (OperatorDTO) session.get("user_context");
    this.proService.getBasePerformance(operator, page, search_year, nodenum, enterguid, batch);
    return page;
  }
  @ResponseBody
  @RequestMapping({"getBaseManagePerformance"})
  public Object getBaseManagePerformance(String userKey, Pages page, String search_year, String nodenum, String enterguid, String batch) {
    Map session = FrameCache.getSession(userKey);
    OperatorDTO operator = (OperatorDTO) session.get("user_context");
    this.proService.getBaseManagePerformance(operator, page, search_year, nodenum, enterguid, batch);
    return page;
  }
  @ResponseBody
  @RequestMapping({"getProStepInfo"})
  public Object getProStepInfo(String userKey, Pages page, String search_year, String nodenum, String enterguid, String batch) {
    Map session = FrameCache.getSession(userKey);
    OperatorDTO operator = (OperatorDTO) session.get("user_context");
    this.proService.getProStepInfo(operator, page, search_year, nodenum, enterguid, batch);
    return page;
  }
  @ResponseBody
  @RequestMapping({"getPerformanceStepInfo"})
  public Object getPerformanceStepInfo(String userKey, Pages page, String search_year, String nodenum, String enterguid, String batch) {
    Map session = FrameCache.getSession(userKey);
    OperatorDTO operator = (OperatorDTO) session.get("user_context");
    this.proService.getPerformanceStepInfo(operator, page, search_year, nodenum, enterguid, batch);
    return page;
  }
  @ResponseBody
  @RequestMapping({"getPerformanceManageStepInfo"})
  public Object getPerformanceManageStepInfo(String userKey, Pages page, String search_year, String nodenum, String enterguid, String batch) {
    Map session = FrameCache.getSession(userKey);
    OperatorDTO operator = (OperatorDTO) session.get("user_context");
    this.proService.getPerformanceManageStepInfo(operator, page, search_year, nodenum, enterguid, batch);
    return page;
  }
  @ResponseBody
  @RequestMapping({"getProStepDetail"})
  public Object getProStepDetail(String userKey, Pages page, String year, String enterguid, String batch) {
    Map session = FrameCache.getSession(userKey);
    OperatorDTO operator = (OperatorDTO) session.get("user_context");
    this.proService.getProStepDetail(operator, page, year, enterguid, batch);
    return page;
  }
  @ResponseBody
  @RequestMapping({"getPerformanceStepDetail"})
  public Object getPerformanceStepDetail(String userKey, Pages page, String year, String enterguid, String batch) {
    Map session = FrameCache.getSession(userKey);
    OperatorDTO operator = (OperatorDTO) session.get("user_context");
    this.proService.getPerformanceStepDetail(operator, page, year, enterguid, batch);
    return page;
  }
  @ResponseBody
  @RequestMapping({"getManagePerformanceStepDetail"})
  public Object getManagePerformanceStepDetail(String userKey, Pages page, String year, String enterguid, String batch) {
    Map session = FrameCache.getSession(userKey);
    OperatorDTO operator = (OperatorDTO) session.get("user_context");
    this.proService.getManagePerformanceStepDetail(operator, page, year, enterguid, batch);
    return page;
  }
  @ResponseBody
  @RequestMapping({"getProBaseAllStep"})
  public Object getProBaseAllStep(String userKey, Pages page, String search_year, String nodenum, String enterguid, String batch) {
    Map session = FrameCache.getSession(userKey);
    OperatorDTO operator = (OperatorDTO) session.get("user_context");
    this.proService.getProBaseAllStep(operator, page, search_year, nodenum, enterguid, batch);
    return page;
  }
    @ResponseBody
    @RequestMapping({"getBasePerformanceAllStep"})
    public Object getBasePerformanceAllStep(String userKey, Pages page, String search_year, String nodenum, String enterguid, String batch) {
        Map session = FrameCache.getSession(userKey);
        OperatorDTO operator = (OperatorDTO) session.get("user_context");
        this.proService.getBasePerformanceAllStep(operator, page, search_year, nodenum, enterguid, batch);
        return page;
    }
  @ResponseBody
  @RequestMapping({"getBasePerformanceManageAllStep"})
  public Object getBasePerformanceManageAllStep(String userKey, Pages page, String search_year, String nodenum, String enterguid, String batch) {
    Map session = FrameCache.getSession(userKey);
    OperatorDTO operator = (OperatorDTO) session.get("user_context");
    this.proService.getBasePerformanceManageAllStep(operator, page, search_year, nodenum, enterguid, batch);
    return page;
  }
  @ResponseBody
  @RequestMapping({"submitBaseInfo"})
  public Object submitBaseInfo(String basedata) {
    Result result = new Result();
    try {
      this.proService.submitBaseInfo(basedata, result);
    } catch (Exception e) {
      e.printStackTrace();
      result.setSuccess(false);
      result.setContent(e.toString());
    }
    return result;
  }

  @ResponseBody
  @RequestMapping({"submitBasePerformance"})
  public Object submitBasePerformance(String basedata) {
    Result result = new Result();
    try {
      this.proService.submitBasePerformance(basedata, result);
    } catch (Exception e) {
      e.printStackTrace();
      result.setSuccess(false);
      result.setContent(e.toString());
    }
    return result;
  }
    @ResponseBody
    @RequestMapping({"submitBaseManage"})
    public Object submitBaseManage(String basedata) {
        Result result = new Result();
        try {
            this.proService.submitBaseManage(basedata, result);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setContent(e.toString());
        }
        return result;
    }
  @ResponseBody
  @RequestMapping({"backBaseInfo"})
  public Object backBaseInfo(String baseinfodata) {
    Result result = new Result();
    try {
      this.proService.backBaseInfo(baseinfodata, result);
    } catch (Exception e) {
      e.printStackTrace();
      result.setSuccess(false);
      result.setContent(e.toString());

    }
    return result;
  }
  @ResponseBody
  @RequestMapping({"backBasePerformance"})
  public Object backBasePerformance(String baseinfodata) {
    Result result = new Result();
    try {
      this.proService.backBasePerformance(baseinfodata, result);
    } catch (Exception e) {
      e.printStackTrace();
      result.setSuccess(false);
      result.setContent(e.toString());

    }
    return result;
  }
    @ResponseBody
    @RequestMapping({"backBaseManage"})
    public Object backBaseManage(String baseinfodata) {
        Result result = new Result();
        try {
            this.proService.backBaseManage(baseinfodata, result);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setContent(e.toString());

        }
        return result;
    }
  @ResponseBody
  @RequestMapping({"getReportModel"})
  public Object getReportModel(Pages page) {
    this.proService.getReportModel(page);
    return page;
  }

  @ResponseBody
  @RequestMapping({"saveReportModel"})
  public Object saveReportModel(String GUID, String REPORTCODE, String REPORTNAME, String REPORTID, String ISEDIT, String PROCESSID) {
    Result result = new Result();
    try {
      this.proService.saveReportModel(GUID, REPORTCODE, REPORTNAME, REPORTID, ISEDIT, PROCESSID, result);
    } catch (Exception e) {
      e.printStackTrace();
      result.setSuccess(false);
      result.setContent(e.toString());

    }
    return result;
  }

  @ResponseBody
  @RequestMapping({"saveReleaseLog"})
  public Object saveReleaseLog(String ISOPEN, String DEADLINEDATE, String reportid) {
    Result result = new Result();
    try {
      this.proService.saveReleaseLog(ISOPEN,DEADLINEDATE,reportid,result);
        result.setSuccess(true);
        result.setContent("修改成功");
    } catch (Exception e) {
      e.printStackTrace();
      result.setSuccess(false);
      result.setContent(e.toString());

    }
    return result;
  }

  @ResponseBody
  @RequestMapping({"deleteReportModel"})
  public Object deleteReportModel(String guid) {
    Result result = new Result();
    try {
      this.proService.deleteReportModel(guid, result);
    } catch (Exception e) {
      e.printStackTrace();
      result.setSuccess(false);
      result.setContent(e.toString());

    }
    return result;
  }

    @ResponseBody
    @RequestMapping({"deleteReleaseLog"})
    public Object deleteReleaseLog(String year,String batch,String reportid) {
        Result result = new Result();
        try {
            this.proService.deleteReleaseLog(year,batch,reportid, result);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setContent(e.toString());

        }
        return result;
    }

  @ResponseBody
  @RequestMapping({"getProcessInfos"})
  public Object getProcessInfos() {
    return this.proService.getProcessInfos();
  }

  @ResponseBody
  @RequestMapping({"getReportModelTree"})
  public Object getReportModelTree() {
    return this.proService.getReportModelTree();
  }

  @ResponseBody
  @RequestMapping({"getStepcodeTree"})
  public Object getStepcodeTree(String userKey) {
    OperatorDTO operator = null;
    if(userKey!=null&&!"".equals(userKey)){
      Map session = FrameCache.getSession(userKey);
      operator = (OperatorDTO) session.get("user_context");
    }
    return this.proService.getStepcodeTree(operator);
  }
  @ResponseBody
  @RequestMapping({"getManagePerformanceStepcodeTree"})
  public Object getManagePerformanceStepcodeTree(String userKey) {
    OperatorDTO operator = null;
    if(userKey!=null&&!"".equals(userKey)){
      Map session = FrameCache.getSession(userKey);
      operator = (OperatorDTO) session.get("user_context");
    }
    return this.proService.getManagePerformanceStepcodeTree(operator);
  }
  @ResponseBody
  @RequestMapping({"getPerformanceStepcodeTree"})
  public Object getPerformanceStepcodeTree(String userKey) {
    OperatorDTO operator = null;
    if(userKey!=null&&!"".equals(userKey)){
      Map session = FrameCache.getSession(userKey);
      operator = (OperatorDTO) session.get("user_context");
    }
    return this.proService.getPerformanceStepcodeTree(operator);
  }
  @ResponseBody
  @RequestMapping({"getJczlEnteTree"})
  public Object getJczlEnteTree(String userKey, String year) {
    Map session = FrameCache.getSession(userKey);
    OperatorDTO operator = (OperatorDTO) session.get("user_context");
    return this.proService.getJczlEnteTree(operator, year);
  }

  @ResponseBody
  @RequestMapping({"getAllJczlEnteTree"})
  public Object getAllJczlEnteTree(String year) {
    return this.proService.getAllJczlEnteTree(year);
  }

  @ResponseBody
  @RequestMapping({"saveEnterPhone"})
  public Object saveEnterPhone(String guid, String enterguid, String entername, String phoneno) {
    Result result = new Result();
    try {
      this.proService.saveEnterPhone(guid, enterguid, entername, phoneno, result);
    } catch (Exception e) {
      e.printStackTrace();
      result.setSuccess(false);
      result.setContent(e.toString());

    }
    return result;
  }

  @ResponseBody
  @RequestMapping({"findEnterPhone"})
  public Object findEnterPhone(String enterguid) {
    Map m = this.proService.findEnterPhone(enterguid);
    return m;
  }
//批次获取时间
  @ResponseBody
  @RequestMapping({"getDateTree"})
  public Object getDateTree(String reportid){
    return this.proService.getDateTree(reportid);
  }

  @ResponseBody//转成JSON格式
  @RequestMapping({"sendMassage"})
  public Object sendMassage(String basedata) {
    Result result = new Result();
    try {
      this.proService.sendMassage(result,basedata);
    } catch (Exception e) {
      e.printStackTrace();
      result.setSuccess(false);
      result.setContent(e.toString());

    }
    return result;
  }
}