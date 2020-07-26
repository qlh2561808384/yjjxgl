package com.datanew.service;

import com.datanew.dto.Pages;
import com.datanew.dto.Result;
import com.hzzk.common.dto.OperatorDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ProService {

    Pages getProReleaseLog(Pages page,String type);

    void saveBaseInfo(String year, String reportid, String reportname, OperatorDTO operator, Result result,String Remarks);

    Pages getProBase(OperatorDTO operator, Pages page, String search_year, String nodenum, String enterguid, String batch);

    Pages getBasePerformance(OperatorDTO operator, Pages page, String search_year, String nodenum, String enterguid, String batch);

    Pages getBaseManagePerformance(OperatorDTO operator, Pages page, String search_year, String nodenum, String enterguid, String batch);

    Pages getProStepInfo(OperatorDTO operator, Pages page, String search_year, String nodenum, String enterguid, String batch);

    Pages getPerformanceStepInfo(OperatorDTO operator, Pages page, String search_year, String nodenum, String enterguid, String batch);

    Pages getPerformanceManageStepInfo(OperatorDTO operator, Pages page, String search_year, String nodenum, String enterguid, String batch);

    Pages getProStepDetail(OperatorDTO operator, Pages page, String year, String enterguid, String batch);

    Pages getPerformanceStepDetail(OperatorDTO operator, Pages page, String year, String enterguid, String batch);

    Pages getManagePerformanceStepDetail(OperatorDTO operator, Pages page, String year, String enterguid, String batch);

    Pages getProBaseAllStep(OperatorDTO operator, Pages page, String search_year, String nodenum, String enterguid, String batch);

    Pages getBasePerformanceAllStep(OperatorDTO operator, Pages page, String search_year, String nodenum, String enterguid, String batch);

    Pages getBasePerformanceManageAllStep(OperatorDTO operator, Pages page, String search_year, String nodenum, String enterguid, String batch);

    void submitBaseInfo(String basedata, Result result);

    void submitBasePerformance(String basedata, Result result);

    void submitBaseManage(String basedata, Result result);

    Pages getReportModel(Pages page);

    void saveReportModel(String GUID, String REPORTCODE, String REPORTNAME, String REPORTID, String ISEDIT, String PROCESSID, Result result);

    void saveReleaseLog(String ISOPEN, String DEADLINEDATE, String reportid, Result result);

    void deleteReportModel(String guid, Result result);

    void deleteReleaseLog(String year,String batch,String reportid, Result result);

    List getProcessInfos();

    List getReportModelTree();

    List getStepcodeTree(OperatorDTO operator);

    List getManagePerformanceStepcodeTree(OperatorDTO operator);

    List getPerformanceStepcodeTree(OperatorDTO operator);

    List getJczlEnteTree(OperatorDTO operator, String year);

    List getAllJczlEnteTree(String year);

    void backBaseInfo(String baseinfodata, Result result);

    void backBasePerformance(String baseinfodata, Result result);

    void backBaseManage(String baseinfodata, Result result);

    void saveEnterPhone(String guid, String enterguid, String entername, String phoneno, Result result);

    Map findEnterPhone(String enterguid);

    List getDateTree(String reportid);

    void sendMassage(Result result,String basedata);


}