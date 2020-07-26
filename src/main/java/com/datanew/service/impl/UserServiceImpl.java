package com.datanew.service.impl;

import com.datanew.dao.BaseDao;
import com.datanew.dto.Pages;
import com.datanew.dto.Result;
import com.datanew.model.BaseOperator;
import com.datanew.model.BaseOperatortopost;
import com.datanew.model.BasePosttomenu;
import com.datanew.service.UserService;
import com.datanew.util.MD5Util;
import com.datanew.util.StringUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl
  implements UserService
{

  @Autowired
  BaseDao baseDao;

  public Pages getUsersInformation(Pages page, String user_division, String user_name, String user_phone, String user_loginname, String user_region, String user_entecode)
  {
    String sql = "select t.userid as USERID,t.loginname as LOGINNAME,t.username as USERNAME,t.password as PASSWORD,t.remark as REMARK,t.moblephonenum as MOBLEPHONENUM,t.telephone as TELEPHONE,t.idcard as IDCARD,t.regicode as REGICODE,r.regiName as REGINAME,t.createdate as CREATEDATE,R.GUID,R.NAME from Base_Operator t  left join base_Region r on t.regicode = r.regiCode left join base_enterprise r on t.ENTECODE = r.GUID where 1=1";
    String countsql = "select count(1) from Base_Operator t  left join base_Region r on t.regicode = r.regiCode left join base_enterprise r on t.ENTECODE = r.GUID  where 1=1";
    if (!StringUtil.isblank(user_name)) {
      sql = sql + " and t.username like '%" + user_name + "%'";
      countsql = countsql + " and t.username like '%" + user_name + "%'";
    }
    if (!StringUtil.isblank(user_phone)) {
      sql = sql + " and t.moblephonenum like '%" + user_phone + "%'";
      countsql = countsql + " and t.moblephonenum like '%" + user_phone + "%'";
    }
    if (!StringUtil.isblank(user_loginname)) {
      sql = sql + " and t.loginname like '%" + user_loginname + "%'";
      countsql = countsql + " and t.loginname like '%" + user_loginname + "%'";
    }
    if (!StringUtil.isblank(user_region)) {
      sql = sql + " and t.regicode = '" + user_region + "'";
      countsql = countsql + " and t.regicode = '" + user_region + "'";
    }
    if (!StringUtil.isblank(user_entecode))
    {
      sql = sql + " and j.name like '%" + user_entecode + "%'";
      countsql = countsql + " and j.name like '%" + user_entecode + "%'";
    }
    List list = this.baseDao.selectBySql(sql, null, page.getOffset().intValue(), page.getLimit().intValue());
    List addlList = new ArrayList();

    for (int i = 0; i < list.size(); i++) {
      Object[] obj = (Object[])list.get(i);
      Map map = new HashMap();
      map.put("USERID", obj[0]);
      map.put("LOGINNAME", obj[1]);
      map.put("USERNAME", obj[2]);
      map.put("PASSWORD", obj[3]);
      map.put("REMARK", obj[4]);
      map.put("MOBLEPHONENUM", obj[5]);
      map.put("TELEPHONE", obj[6]);
      map.put("IDCARD", obj[7]);
      map.put("REGICODE", obj[8]);
      map.put("REGINAME", obj[9]);
      map.put("CREATEDATE", obj[10]);
      map.put("ENTECODE", obj[11]);
      map.put("ENTENAME", obj[12]);
      addlList.add(map);
    }
    Long listcount = this.baseDao.getCountBySQL(countsql, null);
    page.setRows(addlList);
    page.setTotal(Integer.valueOf(listcount.intValue()));
    return page;
  }

  public void deleteUsers(String id) {
    String sql = "delete from BASE_OPERATOR  where userid=" + id;
    this.baseDao.executeBySql(sql);
  }

  public void saveUsers(BaseOperator operator, String oldPassWord, Result result, int stuts) {
    if (stuts == -1) {
      operator.setPassword(MD5Util.MD5(operator.getPassword()));
      this.baseDao.save(operator);
      result.setSuccess(true);
      result.setContent("保存成功");
    } else {
      BaseOperator upBaseOperator = (BaseOperator)this.baseDao.load(BaseOperator.class, operator.getUserid());
      if (!oldPassWord.equals(operator.getPassword())) {
        upBaseOperator.setPassword(MD5Util.MD5(operator.getPassword()));
      }
      upBaseOperator.setMoblephonenum(operator.getMoblephonenum());
      upBaseOperator.setTelephone(operator.getTelephone());
      upBaseOperator.setRegicode(operator.getRegicode());
      upBaseOperator.setUsername(operator.getUsername());
      upBaseOperator.setEntecode(operator.getEntecode());
      this.baseDao.update(upBaseOperator);
      result.setSuccess(true);
      result.setContent("修改成功");
    }
  }

  public void saveUserPost(String id, String post, Result result)
  {
    String delSql = "delete from BASE_OPERATORTOPOST  where userid = '" + id + "'";
    this.baseDao.executeBySql(delSql);
    if (!"".equals(post)) {
      String[] postIds = post.split(",");
      for (String str : postIds) {
        BaseOperatortopost operatortopost = new BaseOperatortopost();
        operatortopost.setUserid(id);
        operatortopost.setPostid(str);
        this.baseDao.save(operatortopost);
      }
    }
    result.setSuccess(true);
    result.setContent("保存成功");
  }

  public void findUserPost(String userId, Result result) {
    String hql = "from BaseOperatortopost t where t.userid = '" + userId + "'";
    List list = this.baseDao.selectByHql(hql);
    StringBuffer postIds = new StringBuffer();
    for (int i = 0; i < list.size(); i++) {
      BaseOperatortopost operatortopost = (BaseOperatortopost)list.get(i);
      postIds.append(operatortopost.getPostid());
      postIds.append(",");
    }
    result.setSuccess(true);
    result.setContent(postIds);
  }

  public void findPostMenu(String postId, Result result) {
    String hql = "from BasePosttomenu  where postid = '" + postId + "'";
    List list = this.baseDao.selectByHql(hql);
    StringBuffer menus = new StringBuffer();
    for (int i = 0; i < list.size(); i++) {
      BasePosttomenu posttomenu = (BasePosttomenu)list.get(i);
      menus.append(posttomenu.getMenuid());
      menus.append(",");
    }
    result.setSuccess(true);
    result.setContent(menus);
  }

  public void savePostMenu(String postId, String menus, Result result)
  {
    String delSql = "delete from BASE_POSTTOMENU  where postid = '" + postId + "'";
    this.baseDao.executeBySql(delSql);
    if (!"".equals(menus)) {
      String[] menuIds = menus.split(",");
      for (String str : menuIds) {
        BasePosttomenu posttomenu = new BasePosttomenu();
        posttomenu.setPostid(postId);
        posttomenu.setMenuid(str);
        this.baseDao.save(posttomenu);
      }
    }

    result.setSuccess(true);
    result.setContent("保存成功");
  }

  public Result updatePassword(String oldpassword, String newpassword, HttpSession session)
  {
    BaseOperator operator = (BaseOperator)session.getAttribute("LOGINUSER");
    Result result = new Result();
    BaseOperator o = (BaseOperator)this.baseDao.load(BaseOperator.class, operator.getUserid());
    if (o.getPassword().equals(MD5Util.MD5(oldpassword))) {
      o.setPassword(MD5Util.MD5(newpassword));
      this.baseDao.update(o);
      result.setSuccess(true);
      result.setContent("修改成功");
    } else {
      result.setSuccess(false);
      result.setContent("修改失败，原密码错误");
    }

    return result;
  }

  public Result savePassword(String userid, Result result) {
    this.baseDao.executeBySql("update base_operator t set t.password='" + MD5Util.MD5("123456") + "' where t.userid=" + userid);
    result.setSuccess(true);
    result.setContent("重置成功");
    return result;
  }
}