package com.datanew.service.impl;

import com.datanew.dao.BaseDao;
import com.datanew.dto.Pages;
import com.datanew.dto.Result;
import com.datanew.model.BaseModule;
import com.datanew.model.BaseOperator;
import com.datanew.model.BaseProcessInfo;
import com.datanew.model.BaseProcessNodes;
import com.datanew.model.BaseProcessRole;
import com.datanew.model.BaseUserRole;
import com.datanew.model.BbTbUserType;
import com.datanew.model.BbTypeToUser;
import com.datanew.service.CommonService;
import com.datanew.util.ConfigureParser;
import com.datanew.util.StringUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hzzk.common.Config;
import com.hzzk.common.dto.OperatorDTO;
import org.springframework.stereotype.Service;

@Service("commonService")
public class CommonServiceImpl
        implements CommonService
{

  @Resource
  BaseDao baseDao;

  /**
   * 用户树(jczl)
   * @return
   */
  public List getUserTree()
  {
    String sql = "select t.guid,t.name,t.parent_guid from jczl.operator t order by t.level_num,t.dorder ";
    List uList = this.baseDao.selectBySql(sql);
    List u_lList = new ArrayList();

    for (int i = 0; i < uList.size(); i++) {
      Object[] obj = (Object[])uList.get(i);
      Map umap = new HashMap();
      umap.put("id", obj[0]);
      umap.put("name", obj[1]);
      umap.put("pId", obj[2]);

      u_lList.add(umap);
    }

    return u_lList;
  }

  /**
   * 获取jczl角色树
   * @return
   */
  public List findJczlPostTree() {
    String sql = "select p.guid,p.name from jczl.post p,jczl.application t where t.code='"+ Config.getAttribute("application.code")+"' and p.appguid = t.guid order by t.dorder ";
    List pList = this.baseDao.selectBySql(sql);
    List p_lList = new ArrayList();

    for (int i = 0; i < pList.size(); i++) {
      Object[] obj = (Object[])pList.get(i);
      Map pmap = new HashMap();
      pmap.put("id", obj[0]);
      pmap.put("name", obj[1]);
      pmap.put("pId", 0);

      p_lList.add(pmap);
    }

    return p_lList;
  }

  /**
   * 获取流程配置信息
   * @param page
   * @param processId
   * @return
   */
  public Pages getProcessNode(Pages page,String processId){
    String sql="select p.name           as ROLENAME,\n" +
            "       u.nodeId         as NODEID,\n" +
            "       u.nodename       as NODENAME,\n" +
            "       u.nodeNum        as NODENUM,\n" +
            "       u.after_Node_Num as AFTERNODENUM,\n" +
            "       u.roleId         as ROLEID,\n" +
            "       u.authIds        as AUTHIDS,\n" +
            "       u.authNames      as AUTHNAMES,\n" +
            "       u.isRoleSelect   as ISROLESELECT,\n" +
            "       u.processId      as PROCESSID\n" +
            "  from Base_Process_Nodes u\n" +
            "  left join jczl.post p\n" +
            "    on u.roleid = p.guid\n" +
            "  left join jczl.application t\n" +
            "    on p.appguid = t.guid\n" +
            "   and t.code = '"+ Config.getAttribute("application.code")+"'\n";
    StringBuffer addsql = new StringBuffer("");
    if(processId != null&&!"".equals(processId)){
      addsql.append(" where u.processId=" + Long.valueOf(processId) + " order by u.roleId");
      sql += addsql.toString();
      String hsql = "select count(0) from Base_Process_Nodes u left join jczl.post p on u.roleid = p.guid left join  jczl.application t on p.appguid = t.guid and t.code = '"+ Config.getAttribute("application.code")+"' " ;
      hsql += addsql.toString();
      List list = baseDao.selectMapsBySQL(sql, null, page.getOffset(),page.getLimit());
      Long count = baseDao.getCountBySQL(hsql, null);
      page.setRows(list);
      page.setTotal(count.intValue());
    }else{
      page.setRows(new ArrayList());
      page.setTotal(0);
    }
    return page;
  }

  /**
   * 获取流程树
   * @return
   */
  public List findProcessTree() {
    String hql = "select new map(o.processName as name, o.processId as id, 0 as pId) from BaseProcessInfo o";
    return baseDao.selectByHql(hql);
  }

  /**
   * 保存流程信息
   * @param operator
   * @param bp
   */
  public void saveProcess(OperatorDTO operator,BaseProcessInfo bp){
    Long processId=bp.getProcessId();
    if(processId==null){
      bp.setUpdateDate(new Date());
      bp.setCreateUserId(Long.valueOf(operator.getGuId()));
      bp.setCreateUserName(operator.getName());
      bp.setCreateDate(new Date());
      baseDao.save(bp);
    }else{
      BaseProcessInfo bpr = (BaseProcessInfo) baseDao.load(BaseProcessInfo.class, processId);
      bpr.setUpdateDate(new Date());
      bpr.setRemark(bp.getRemark());
      bpr.setProcessCode(bp.getProcessCode());
      bpr.setProcessName(bp.getProcessName());
      bpr.setProcessStatus(bp.getProcessStatus());
      baseDao.save(bpr);
    }
  }

  /**
   * 获取流程信息
   * @param processId
   * @return
   */
  public Map findProcessInfo(String processId){
    List bplist = baseDao.selectByHql("from BaseProcessInfo t where t.processId = '"+processId+"'");
    Map map = new HashMap();
    map.put("process", bplist);
    return map;
  }

  /**
   * 删除流程信息
   * @param processId
   * @return
   */
  public boolean deleteProcess(String processId){
    if (processId != null){
      BaseProcessInfo ProcessRole = (BaseProcessInfo)this.baseDao.load(BaseProcessInfo.class, Long.valueOf(processId));
      if (ProcessRole.getProcessId() != null){
        this.baseDao.executeBySql("delete from BASE_PROCESS_NODES n where n.processId ='"+processId.toString()+"'");
        this.baseDao.delete(ProcessRole);
        return true;
      }else{
        return false;
      }
    }
    return false;
  }

  /**
   * 删除环节信息
   * @param nodeId
   * @return
   */
  public boolean deleteNode(String nodeId){
    if (nodeId != null){
      this.baseDao.executeBySql("delete from BASE_PROCESS_NODES n where n.nodeId ='"+nodeId.toString()+"'");
      return true;
    }
    return false;
  }

  /**
   * 保存流程配置信息
   * @param bpn
   * @param processId
   * @param authNames
   */
  public void saveProcessNode(BaseProcessNodes bpn,String processId,String authNames){
    bpn.setProcessId(Long.valueOf(processId));
    bpn.setAuthNames(authNames);
    baseDao.saveOrUpdate(bpn);
  }









  /**
   * 获取角色对应用户信息
   * @param page
   * @param roleid
   * @return
     */
  public Pages getRoleUser(Pages page,String roleid){
    String sql="select o.name as username, p.roleName as rolename from JCZL.OPERATOR o, BASE_USER_ROLE u, BASE_PROCESS_ROLE p " +
            " where o.guid = u.userId and u.roleId = p.roleId ";
    StringBuffer addsql = new StringBuffer("");
    if(roleid != null&&!"".equals(roleid)){
      addsql.append(" and u.roleId=" + Long.valueOf(roleid));
      sql += addsql.toString();
      String hsql = "select count(0) from JCZL.OPERATOR o,Base_User_Role u,Base_Process_Role p where o.guid = u.userId and u.roleId = p.roleId";
      hsql += addsql.toString();
      List list = baseDao.selectMapsBySQL(sql, null, page.getOffset(),page.getLimit());
      Long count = baseDao.getCountBySQL(hsql, null);
      page.setRows(list);
      page.setTotal(count.intValue());
    }else{
      page.setRows(new ArrayList());
      page.setTotal(0);
    }
    return page;
  }

  /**
   * 获取角色信息
   * @param roleid
   * @return
   */
  public Map findRoleInfo(String roleid){
    List bplist = baseDao.selectByHql("from BaseProcessRole t where t.roleId = '"+roleid+"'");
    List urlist = baseDao.selectByHql("select t.userId from BaseUserRole t where t.roleId = '"+roleid+"'");
    Map map = new HashMap();
    map.put("role", bplist);
    String usersString = "";
    for(int i=0;i<urlist.size();i++){
      if(i<urlist.size()-1){
        usersString += urlist.get(i).toString()+",";
      }else{
        usersString += urlist.get(i).toString();
      }
    }
    map.put("user", urlist);
    return map;
  }

  /**
   * 删除角色信息
   * @param roleid
   * @return
   */
  public boolean deleteRole(String roleid){
    if (roleid != null){
      BaseProcessRole ProcessRole = (BaseProcessRole)this.baseDao.load(BaseProcessRole.class, Long.valueOf(roleid));
      if (ProcessRole.getRoleId() != null){
        this.baseDao.executeBySql("delete from BASE_USER_ROLE n where n.roleId ='"+roleid.toString()+"'");
        this.baseDao.delete(ProcessRole);
        return true;
      }else{
        return false;
      }
    }
    return false;
  }

  /**
   * 保存角色信息
   * @param operator
   * @param bp
     */
  public void saveRole(OperatorDTO operator, BaseProcessRole bp){
    Long roleId=bp.getRoleId();
    if(roleId!=null){
      this.baseDao.executeBySql("delete from BASE_USER_ROLE n where n.roleId ='"+roleId.toString()+"'");
      BaseProcessRole bpr = (BaseProcessRole) baseDao.load(BaseProcessRole.class, roleId);
      bpr.setUpdateDate(new Date());
      bpr.setRemark(bp.getRemark());
      bpr.setRoleCode(bp.getRoleCode());
      bpr.setRoleName(bp.getRoleName());
      baseDao.save(bpr);
    }else{
      bp.setCreateDate(new Date());
      bp.setUpdateDate(new Date());
      bp.setCreateUserId(Long.valueOf(operator.getGuId()));
      bp.setCreateUserName(operator.getName());
      baseDao.save(bp);
    }
    String[] user = bp.getUserId().split(",");
    for(int i=0;i<user.length;i++){
      BaseUserRole ur = new BaseUserRole();
      ur.setRoleId(bp.getRoleId());
      ur.setUserId(Long.valueOf(user[i]));
      baseDao.save(ur);
    }
  }

  /**
   * 获取角色树
   * @return
     */
  public List findRoleTree() {
    String hql = "select new map(o.roleName as name, o.roleId as id, 0 as pId) from BaseProcessRole o";
    return baseDao.selectByHql(hql);
  }

  /**
   * 更新用户信息
   * @param result
     */
  public void updateUserInfo(Result result) {
    //删除用户信息
    String delete_sql = "delete from base_operator ";
    baseDao.executeBySql(delete_sql);

    //同步jczl.base_operator用户信息
    String insert_sql = "insert into base_operator \n" +
            "select o.guid,sysdate,o.phoneno,o.sfzh,o.code,null,o.mobiphone,o.password,null,o.name,null,null,o.userid,o.enable,o.parent_guid from jczl.operator o";

    baseDao.executeBySql(insert_sql);

    result.setSuccess(true);
    result.setContent("更新成功");

  }

  /**
   * 获取用户信息
   * @param userid
   * @return
     */
  public Map getUserInfo(String userid) {
    List bplist = baseDao.selectByHql("from BaseOperator t where t.userid = " + userid);
    Map map = new HashMap();
    map.put("info", bplist.get(0));
    return map;
  }




  public List findUserTree()
  {
    String hql = "select o.username as name, o.userid as id from Base_Operator o ";
    List uList = this.baseDao.selectBySql(hql);
    List u_lList = new ArrayList();

    String enteids = "";
    for (int i = 0; i < uList.size(); i++) {
      Object[] obj = (Object[])uList.get(i);
      Map umap = new HashMap();
      umap.put("name", obj[0]);
      umap.put("id", obj[1]);
      umap.put("ISLEAF", Character.valueOf('1'));

      u_lList.add(umap);
    }

    return u_lList;
  }

  public List findRegionTree() {
    String hql = "select o.regiName as name, o.regiCode as id, o.p_regiCode as pId from Region o where o.regiCode like '%" + ConfigureParser.getPropert("Region_leval") + "%'";
    List uList = this.baseDao.selectByHql(hql);
    List u_lList = new ArrayList();

    for (int i = 0; i < uList.size(); i++) {
      Object[] obj = (Object[])uList.get(i);
      Map umap = new HashMap();
      umap.put("name", obj[0]);
      umap.put("id", obj[1]);
      if (obj[1].toString().length() == 6)
        umap.put("ISLEAF", Character.valueOf('1'));
      else {
        umap.put("ISLEAF", Character.valueOf('0'));
      }
      umap.put("pId", obj[2]);
      u_lList.add(umap);
    }
    return u_lList;
  }

  public List findEnteTree() {
    String hql = "select concat(concat(concat('['o.isbn_code),']'),o.name) as name, o.guid as id, o.fs_guid as code, o.parent_guid as pId from base_enter o order by o.code";
    List list = this.baseDao.selectBySql(hql);
    List addlList = new ArrayList();

    for (int i = 0; i < list.size(); i++) {
      Object[] obj = (Object[])list.get(i);
      Map map = new HashMap();
      map.put("name", obj[0]);
      map.put("id", obj[1]);
      map.put("code", obj[2]);
      map.put("pId", obj[3]);
      addlList.add(map);
    }
    return addlList;
  }

  public List findReEnteTree(String regioncode) {
    String hql = "select concat(concat(concat('[',o.isbn_code),']'),o.name) as name, o.guid as id, o.parent_guid as pId from base_enterprise o";
    hql = hql + " order by o.guid";
    List list = this.baseDao.selectBySql(hql);
    List addlList = new ArrayList();

    for (int i = 0; i < list.size(); i++) {
      Object[] obj = (Object[])list.get(i);
      Map map = new HashMap();
      map.put("name", obj[0]);
      map.put("id", obj[1]);
      map.put("pId", obj[2]);
      addlList.add(map);
    }
    return addlList;
  }

  public List findBankTree() {
    String hql = "select o.bankfsguid as id,concat(concat(concat('[',o.bankfsguid),']'),o.accountname) as name, 0 as pId from bank_info o order by o.id";
    List list = this.baseDao.selectBySql(hql);
    List addlList = new ArrayList();

    for (int i = 0; i < list.size(); i++) {
      Object[] obj = (Object[])list.get(i);
      Map map = new HashMap();
      map.put("id", obj[0]);
      map.put("name", obj[1]);
      map.put("pId", obj[2]);
      addlList.add(map);
    }
    return addlList;
  }

  public List findDivisionTree()
  {
    String hql = "select new map(o.name as name, o.guid as id, 0 as pId) from Division o";
    return this.baseDao.selectByHql(hql);
  }

  public List findPostTree() {
    String hql = "select new map(o.postname as name, o.postid as id, 0 as pId) from BasePost o";
    return this.baseDao.selectByHql(hql);
  }

  public List findUserReportTree(BaseOperator operator) {
    String hql = "select distinct new map(o.name as name, o.guid as id, o.fillName as fillName, 0 as pId) from BbReport o,BbReportToUser u where u.reportid = o.guid and o.enable='1' and o.tbenable='1' and u.userid = " + operator.getUserid();
    return this.baseDao.selectByHql(hql);
  }

  public List findReportTree() {
    String hql = "select new map(o.name as name, o.guid as id, 0 as pId) from BbReport o";
    return this.baseDao.selectByHql(hql);
  }

  public List findReportFillNameTree() {
    String hql = "select new map(o.name as name, o.guid as id,o.fillName as code,o.collectName as collectcode, 0 as pId) from BbReport o where o.enable='1'";
    return this.baseDao.selectByHql(hql);
  }

  public List findModuleTree() {
    String hql = "select new map(concat(concat(concat('[',o.code),']'),o.name) as name, o.guId as id, 0 as pId) from BaseModule o";
    return this.baseDao.selectByHql(hql);
  }

  public List findMenuTree() {
    String hql = "select new map(o.menuname as name, o.menuid as id, parentid as pId) from BaseMenu o";
    return this.baseDao.selectByHql(hql);
  }

  public List findTypeTree() {
    String hql = "select new map(concat(concat(concat('[',o.typeCode),']'),o.typeName) as name, o.typeId as id, 0 as pId) from BbTbUserType o";
    return baseDao.selectByHql(hql);
  }

  /**
   * 获取短信模板信息
   */
  public Pages getModule(Pages page){
    String sql="select new map(o.guId as GUID,o.code as CODE, o.name as NAME, o.content as CONTENT,to_char(o.writeTime, 'yyyy-MM-dd') as WRITETIME) from BaseModule o";
    String hsql = "select count(0)  from Base_Module u";
    List list = baseDao.selectByHql(sql, null, page.getOffset(),page.getLimit());
    Long count = baseDao.getCountBySQL(hsql, null);
    page.setRows(list);
    page.setTotal(count.intValue());
    return page;
  }
  /**
   * 保存短信模板信息
   */
  public void saveModule(BaseModule bpr){
    baseDao.saveOrUpdate(bpr);
  }
  /**
   * 删除短信模板信息
   * @param guid
   * @return
   */
  public boolean deleteModule(String guid){
    if (guid != null){
      baseDao.executeBySql("delete from BASE_MODULE n where n.guid = "+guid+"");
      return true;
    }
    return false;
  }
  public void downloadChrome(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String filename="chrome64_installer.zip";

    //设置文件MIME类型
    response.setContentType("text/html");
    //设置Content-Disposition
    response.setHeader("Content-Disposition", "attachment;filename="+new String(filename.replaceAll(" ", "").getBytes("utf-8"),"iso8859-1"));

    String itempath=request.getSession().getServletContext().getRealPath("/");

    ServletOutputStream outputStream =response.getOutputStream() ;

    InputStream inputStream = new FileInputStream(new File(itempath+"WEB-INF/classes/doc/chrome64_installer.zip"));
    byte[] buffer = new byte[1024];
    int i = -1;
    while ((i = inputStream.read(buffer)) != -1) {
      outputStream.write(buffer, 0, i);
    }
    inputStream.close();
    outputStream.flush();
    outputStream.close();

  }


  /**
   * 保存填报人类别信息
   * @return
   */
  public void saveType(BaseOperator operator,BbTbUserType bp){
    Long typeId=bp.getTypeId();
    if(typeId!=null){
      BbTbUserType bpr = (BbTbUserType) baseDao.load(BbTbUserType.class, typeId);
      bpr.setUpdateDate(new Date());
      bpr.setRemark(bp.getRemark());
      bpr.setTypeCode(bp.getTypeCode());
      bpr.setTypeName(bp.getTypeName());
      baseDao.save(bpr);
    }else{
      bp.setCreateDate(new Date());
      bp.setUpdateDate(new Date());
      bp.setCreateUserId(Long.valueOf(operator.getUserid()));
      bp.setCreateUserName(operator.getUsername());
      baseDao.save(bp);
    }
  }

  /**
   * 获取填报人类别对应用户信息
   * @param typeid
   * @return
   */
  public void getTypeUser(String typeid,Result result){
    StringBuffer userIds = new StringBuffer();
    if(!StringUtil.isblank(typeid)){
      String hql="select u.userid from BbTypeToUser u,BbTbUserType p where u.typeid = p.typeId and u.typeid="+typeid;
      List list = baseDao.selectByHql(hql);
      for (int i = 0; i < list.size(); i++) {
        userIds.append(list.get(i).toString());
        userIds.append(",");
      }
    }
    result.setSuccess(true);
    result.setContent(userIds);
  }

  /**
   * 获取填报人类别信息
   * @param typeid
   * @return
   */
  public Map findTypeInfo(String typeid){
    List bplist = baseDao.selectByHql("from BbTbUserType t where t.typeId = '"+typeid+"'");
    Map map = new HashMap();
    map.put("type", bplist);
    return map;
  }
  /**
   * 获取填报用户信息
   * @return
   */
  public List findInformantTree(){
    String thql = "select new map(o.username as name, u.typeCode||'_'||o.userid as id, 'tbr_'||u.typeCode as pId) from BbTypeToUser t,BaseOperator o,BbTbUserType u where t.userid = o.userid and u.typeId = t.typeid";
    String uhql = "select new map(u.typeName as name, 'tbr_'||u.typeCode as id, 0 as pId) from BbTbUserType u ";
    List tList = baseDao.selectByHql(thql);
    List uList = baseDao.selectByHql(uhql);
    List addlist = new ArrayList();
    for(int i=0;i<uList.size(); i++){
      Map map = (Map) uList.get(i);
      map.put("open", true);
      addlist.add(map);
    }
    tList.addAll(addlist);
    return tList;
  }

  /**
   * 删除填报人类别信息
   * @param typeid
   * @return
   */
  public boolean deleteType(String typeid){
    if (typeid != null){
      BbTbUserType tbtype = (BbTbUserType)this.baseDao.load(BbTbUserType.class, Long.valueOf(typeid));
      if (tbtype.getTypeId() != null){
        this.baseDao.executeBySql("delete from BB_TYPETOUSER n where n.typeid ='"+typeid.toString()+"'");
        this.baseDao.delete(tbtype);
        return true;
      }else{
        return false;
      }
    }
    return false;
  }

  public void saveTypeToUser(String userIds,String typeId) {
    String delSql = "delete from BB_TYPETOUSER  where typeid = '" + typeId + "'";
    baseDao.executeBySql(delSql);
    if(!"".equals(userIds)){
      String[] userId = userIds.split(",");
      for (String str : userId) {
        BbTypeToUser btu = new BbTypeToUser();
        btu.setTypeid(Long.valueOf(typeId));
        btu.setUserid(Long.valueOf(str));
        baseDao.save(btu);
      }
    }

  }

  public List findReportSJTree(String reportid) {
    List list = baseDao.selectBySql("select distinct to_char(t.sj,'yyyy-MM-dd') from bb_reporttouser t where t.reportid="+reportid+" order by to_char(t.sj,'yyyy-MM-dd') desc");
    List addlList = new ArrayList();
    Map map;
    for(int i=0;i<list.size();i++){
      String obj = list.get(i).toString();
      map =  new HashMap();
      map.put("id", obj);
      map.put("name", obj);
      map.put("pId", '0');
      addlList.add(map);
    }
    return addlList;
  }

  public Object[] getReginame() {
    List list = baseDao.selectBySql("select t.regiName,t.financeLevel from fszg.xt_Region t where t.regiCode = "+ConfigureParser.getPropert("Curve_Region"));
    Object[] objects = (Object[])list.get(0);
    return objects;
  }

}