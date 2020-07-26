package com.datanew.service;

import com.datanew.dto.Pages;
import com.datanew.dto.Result;
import com.datanew.model.BaseModule;
import com.datanew.model.BaseOperator;
import com.datanew.model.BaseProcessInfo;
import com.datanew.model.BaseProcessNodes;
import com.datanew.model.BaseProcessRole;
import com.datanew.model.BbTbUserType;
import com.hzzk.common.dto.OperatorDTO;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract interface CommonService
{
  List getUserTree();//用户树(jczl)

  List findJczlPostTree();//基础资料角色树

  /**
   * 获取流程配置信息
   */
  Pages getProcessNode(Pages page,String processId);

  List findProcessTree();

  /**
   * 保存流程信息
   * @return
   */
  void saveProcess(OperatorDTO operator, BaseProcessInfo bpr);

  /**
   * 获取流程信息
   */
  Map findProcessInfo(String processId);

  /**
   * 删除流程信息
   */
  boolean deleteProcess(String processId);

  /**
   * 删除环节信息
   */
  boolean deleteNode(String nodeId);

  /**
   * 保存流程配置信息
   * @return
   */
  void saveProcessNode(BaseProcessNodes bpn,String processId,String authNames);







  /**
   * 获取角色对应用户信息
   */
  Pages getRoleUser(Pages page,String roleid);

  /**
   * 获取角色信息
   */
  Map findRoleInfo(String roleid);

  /**
   * 删除角色信息
   */
  boolean deleteRole(String roleid);

  /**
   * 保存角色信息
   * @return
   */
  void saveRole(OperatorDTO operator, BaseProcessRole bpr);

  List findRoleTree();

  void updateUserInfo(Result result);//更新用户信息

  Map getUserInfo(String userid);//获取用户信息

  public abstract List findUserTree();

  public abstract List findRegionTree();

  public abstract List findEnteTree();

  public abstract List findReEnteTree(String paramString);

  public abstract List findBankTree();

  public abstract List findDivisionTree();

  public abstract List findPostTree();

  public abstract List findUserReportTree(BaseOperator paramBaseOperator);

  public abstract List findReportTree();

  public abstract List findReportFillNameTree();

  public abstract List findModuleTree();

  public abstract List findMenuTree();



  List findTypeTree();


  /**
   * 获取短信模板信息
   */
  Pages getModule(Pages page);
  /**
   * 保存短信模板信息
   * @return
   */
  void saveModule(BaseModule bpr);
  /**
   * 删除短信模板信息
   */
  boolean deleteModule(String guid);
  /**
   *
   * @Title: downloadChrome
   * @Description: 下载chrome
   * @param request
   * @param response
   * @return: void
   */
  void downloadChrome(HttpServletRequest request, HttpServletResponse response) throws Exception;

  /**
   * 保存填报人类别信息
   * @return
   */
  void saveType(BaseOperator operator,BbTbUserType bpr);




  /**
   * 获取填报人类别对应用户信息
   */
  void getTypeUser(String typeid,Result result);

  /**
   * 获取填报人类别信息
   */
  Map findTypeInfo(String typeid);

  /**
   * 获取填报用户信息
   */
  List findInformantTree();

  /**
   * 删除填报人信息
   */
  boolean deleteType(String typeid);

  /**
   * 更新填报人类别信息
   */
  void saveTypeToUser(String userIds,String typeId);

  /**
   * 获取填报用户信息
   */
  List findReportSJTree(String reportid);

  Object[] getReginame();
}