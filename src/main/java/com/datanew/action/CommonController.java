package com.datanew.action;

import cn.com.gsoft.core.www.client.FrameCache;
import com.datanew.dto.Pages;
import com.datanew.dto.Result;
import com.datanew.model.BaseModule;
import com.datanew.model.BaseOperator;
import com.datanew.model.BaseProcessInfo;
import com.datanew.model.BaseProcessNodes;
import com.datanew.model.BaseProcessRole;
import com.datanew.model.BbTbUserType;
import com.datanew.service.CommonService;
import com.datanew.util.DateUtil;
import com.datanew.util.StaticData;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hzzk.common.dto.OperatorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"common"})
public class CommonController
{

  @Autowired
  CommonService commonService;

  @RequestMapping({"downloadChrome"})
  public void downloadChrome(HttpServletRequest request, HttpServletResponse response)
          throws Exception
  {
    this.commonService.downloadChrome(request, response);
  }

  //获取新用户树
  @ResponseBody
  @RequestMapping({"getUserTree"})
  public List getUserTree(HttpSession session) {
    return this.commonService.getUserTree();
  }

  /**
   * 获取jczl角色树
   */
  @ResponseBody
  @RequestMapping("findJczlPostTree")
  public Object findJczlPostTree(HttpSession session){
    return commonService.findJczlPostTree();
  }

  /**
   * 获取流程配置信息
   */
  @ResponseBody
  @RequestMapping("getProcessNode")
  public Object getProcessNode(Pages page,String processId){
    Pages page1=commonService.getProcessNode(page,processId);
    return page;
  }

  @ResponseBody
  @RequestMapping({"findProcessTree"})
  public List findProcessTree(HttpSession session) {
    return this.commonService.findProcessTree();
  }

  /**
   * 保存流程信息
   * @return
   */
  @RequestMapping("saveProcess")
  public void saveProcess(String userKey, HttpServletResponse res, BaseProcessInfo bpr){
    Map session = FrameCache.getSession(userKey);
    OperatorDTO operator = (OperatorDTO) session.get("user_context");
    try{
      commonService.saveProcess(operator,bpr);
      res.setContentType("text/html;charset=UTF-8");
      res.getWriter().write("{\"success\":true,\"msg\":\"保存成功\"}");
    }catch(Exception e){
      res.setContentType("text/html;charset=UTF-8");
      try {
        res.getWriter().write("{\"success\":false,\"msg\":\"保存失败\"}");
      } catch (IOException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    }
  }

  /**
   * 获取流程信息
   */
  @ResponseBody
  @RequestMapping("findProcessInfo")
  public Map findProcessInfo(String processId){
    return commonService.findProcessInfo(processId);
  }

  /**
   * 删除流程信息
   */
  @ResponseBody
  @RequestMapping("deleteProcess")
  public Object deleteProcess(String processId){
    boolean t = commonService.deleteProcess(processId);
    Result result = new Result();
    if(t){
      result.setSuccess(t);
      result.setContent("删除成功");
    }else{
      result.setSuccess(false);
      result.setContent("删除失败");
    }
    return result;
  }

  /**
   * 删除环节信息
   */
  @ResponseBody
  @RequestMapping("deleteNode")
  public Object deleteNode(String nodeId){
    boolean t = commonService.deleteNode(nodeId);
    Result result = new Result();
    if(t){
      result.setSuccess(t);
      result.setContent("删除成功");
    }else{
      result.setSuccess(false);
      result.setContent("删除失败");
    }
    return result;
  }

  /**
   * 保存流程配置信息
   * @return
   */
  @RequestMapping("saveProcessNode")
  public void saveProcessNode(HttpServletResponse res,BaseProcessNodes bpn,String processId,String authNames){
    try{
      commonService.saveProcessNode(bpn,processId,authNames);
      res.setContentType("text/html;charset=UTF-8");
      res.getWriter().write("{\"success\":true,\"msg\":\"保存成功\"}");
    }catch(Exception e){
      res.setContentType("text/html;charset=UTF-8");
      try {
        res.getWriter().write("{\"success\":false,\"msg\":\"保存失败\"}");
      } catch (IOException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    }
  }












  /**
   * 获取角色对应用户信息
   */
  @ResponseBody
  @RequestMapping("getRoleUser")
  public Object getRoleUser(Pages page,String roleid){
    Pages page1=commonService.getRoleUser(page,roleid);
    return page;
  }

  /**
   * 获取角色信息
   */
  @ResponseBody
  @RequestMapping("findRoleInfo")
  public Map findRoleInfo(String roleid){
    return commonService.findRoleInfo(roleid);
  }

  /**
   * 删除角色信息
   */
  @ResponseBody
  @RequestMapping("deleteRole")
  public Object deleteRole(String roleid){
    boolean t = commonService.deleteRole(roleid);
    Result result = new Result();
    if(t){
      result.setSuccess(t);
      result.setContent("删除成功");
    }else{
      result.setSuccess(false);
      result.setContent("删除失败");
    }
    return result;
  }

  /**
   * 保存角色信息
   * @return
   */
  @RequestMapping("saveRole")
  public void saveRole(String userKey, HttpServletResponse res, BaseProcessRole bpr){
    Map session = FrameCache.getSession(userKey);
    OperatorDTO operator = (OperatorDTO) session.get("user_context");
    try{
      commonService.saveRole(operator,bpr);
      res.setContentType("text/html;charset=UTF-8");
      res.getWriter().write("{\"success\":true,\"msg\":\"保存成功\"}");
    }catch(Exception e){
      res.setContentType("text/html;charset=UTF-8");
      try {
        res.getWriter().write("{\"success\":false,\"msg\":\"保存失败\"}");
      } catch (IOException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    }
  }

  /**
   * 获取角色树
   */
  @ResponseBody
  @RequestMapping("findRoleTree")
  public Object findRoleTree(HttpSession session){
    return commonService.findRoleTree();
  }

  //更新用户信息
  @ResponseBody
  @RequestMapping({"updateUserInfo"})
  public Object updateUserInfo() {
    Result result = new Result();
    this.commonService.updateUserInfo(result);
    return result;
  }

  //获取用户信息
  @ResponseBody
  @RequestMapping({"getUserInfo"})
  public Map getUserInfo(String userid)
  {
    return this.commonService.getUserInfo(userid);
  }



  @ResponseBody
  @RequestMapping({"findUserTree"})
  public List findUserTree(HttpSession session) {
    return this.commonService.findUserTree();
  }
  @ResponseBody
  @RequestMapping({"findRegionTree"})
  public List findRegionTree(HttpSession session) {
    return this.commonService.findRegionTree();
  }
  @ResponseBody
  @RequestMapping({"findEnteTree"})
  public List findEnteTree(HttpSession session) {
    return this.commonService.findEnteTree();
  }
  @ResponseBody
  @RequestMapping({"findReEnteTree"})
  public List findReEnteTree(HttpSession session, String regioncode) {
    return this.commonService.findReEnteTree(regioncode);
  }
  @ResponseBody
  @RequestMapping({"findBankTree"})
  public List findBankTree(HttpSession session) {
    return this.commonService.findBankTree();
  }
  @ResponseBody
  @RequestMapping({"findDivisionTree"})
  public List findDivisionTree(HttpSession session) {
    return this.commonService.findDivisionTree();
  }
  @ResponseBody
  @RequestMapping({"findPostTree"})
  public List findPostTree(HttpSession session) {
    return this.commonService.findPostTree();
  }
  @ResponseBody
  @RequestMapping({"findUserReportTree"})
  public List findUserReportTree(HttpSession session) {
    BaseOperator operator = (BaseOperator)session.getAttribute("LOGINUSER");
    return this.commonService.findUserReportTree(operator);
  }
  @ResponseBody
  @RequestMapping({"findReportTree"})
  public List findReportTree(HttpSession session) {
    return this.commonService.findReportTree();
  }
  @ResponseBody
  @RequestMapping({"findReportFillNameTree"})
  public List findReportFillNameTree(HttpSession session) {
    return this.commonService.findReportFillNameTree();
  }
  @ResponseBody
  @RequestMapping({"findModuleTree"})
  public List findModuleTree(HttpSession session) {
    return this.commonService.findModuleTree();
  }

  @ResponseBody
  @RequestMapping({"findMenuTree"})
  public List findMenuTree(HttpSession session) {
    return this.commonService.findMenuTree();
  }

  @ResponseBody
  @RequestMapping({"getModule"})
  public Object getModule(Pages page)
  {
    Pages page1 = this.commonService.getModule(page);
    return page;
  }

  @ResponseBody
  @RequestMapping({"saveModule"})
  public Object saveModule(String guId, String name, String writeTime, String code, String content)
  {
    Result result = new Result();
    BaseModule bpr = new BaseModule();
    if (!"".equals(guId)) {
      bpr.setGuId(Long.valueOf(guId));
      bpr.setWriteTime(DateUtil.getCurrentDate(writeTime, "yyyy-MM-dd"));
    } else {
      bpr.setWriteTime(DateUtil.getCurrentDate("yyyy-MM-dd"));
    }
    bpr.setCode(code);
    bpr.setContent(content);
    bpr.setName(name);
    try {
      this.commonService.saveModule(bpr);
      result.setSuccess(true);
      result.setContent("保存成功");
    } catch (Exception e) {
      result.setContent("保存失败："+e.toString());
    }
    return result;
  }

  @ResponseBody
  @RequestMapping({"deleteModule"})
  public Object deleteModule(String guid)
  {
    boolean t = this.commonService.deleteModule(guid);
    Result result = new Result();
    if (t) {
      result.setSuccess(t);
      result.setContent("删除成功");
    } else {
      result.setSuccess(false);
      result.setContent("删除失败");
    }
    return result;
  }



  /**
   * 获取填报人类别树
   */
  @ResponseBody
  @RequestMapping("findTypeTree")
  public Object findTypeTree(HttpSession session){
    return commonService.findTypeTree();
  }

  /**
   * 获取操作权限树
   */
  @ResponseBody
  @RequestMapping("findAuthTree")
  public Object findAuthTree(HttpSession session){
    return null;
  }


  /**
   * 保存填报人类别信息
   * @return
   */
  @RequestMapping("saveType")
  public void saveType(HttpSession session,HttpServletResponse res,BbTbUserType bpr){
    BaseOperator operator = (BaseOperator) session.getAttribute(StaticData.LOGINUSER);
    try{
      commonService.saveType(operator,bpr);
      res.setContentType("text/html;charset=UTF-8");
      res.getWriter().write("{\"success\":true,\"msg\":\"保存成功\"}");
    }catch(Exception e){
      res.setContentType("text/html;charset=UTF-8");
      try {
        res.getWriter().write("{\"success\":false,\"msg\":\"保存失败\"}");
      } catch (IOException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    }
  }

  /**
   * 获取填报人类别对应用户信息
   */
  @ResponseBody
  @RequestMapping("getTypeUser")
  public Object getTypeUser(String typeid){
    Result result = new Result();
    commonService.getTypeUser(typeid,result);
    return result;
  }


  /**
   * 获取填报人类别信息
   */
  @ResponseBody
  @RequestMapping("findTypeInfo")
  public Map findTypeInfo(String typeid){
    return commonService.findTypeInfo(typeid);
  }
  /**
   * 获取填报用户信息
   */
  @ResponseBody
  @RequestMapping("findInformantTree")
  public Object findInformantTree(){
    return commonService.findInformantTree();
  }


  /**
   * 删除填报人类别信息
   */
  @ResponseBody
  @RequestMapping("deleteType")
  public Object deleteType(String typeid){
    boolean t = commonService.deleteType(typeid);
    Result result = new Result();
    if(t){
      result.setSuccess(t);
      result.setContent("删除成功");
    }else{
      result.setSuccess(false);
      result.setContent("删除失败");
    }
    return result;
  }

  /**
   * 更新填报人类别信息
   */
  @RequestMapping("saveTypeToUser")
  public void saveTypeToUser(HttpServletResponse res,String userIds,String typeId){
    try{
      commonService.saveTypeToUser(userIds,typeId);
      res.setContentType("text/html;charset=UTF-8");
      res.getWriter().write("{\"success\":true,\"msg\":\"保存成功\"}");
    }catch(Exception e){
      res.setContentType("text/html;charset=UTF-8");
      try {
        res.getWriter().write("{\"success\":false,\"msg\":\"保存失败\"}");
      } catch (IOException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    }
  }

  /**
   * 获取填报时间信息
   */
  @ResponseBody
  @RequestMapping("findReportSJTree")
  public Object findReportSJTree(String reportid){
    return commonService.findReportSJTree(reportid);
  }

  /**
   * 获取填报时间信息
   */
  @ResponseBody
  @RequestMapping("getReginame")
  public Object getReginame(){
    Result result = new Result();
    result.setSuccess(true);
    result.setContent(commonService.getReginame());
    return result;
  }
}