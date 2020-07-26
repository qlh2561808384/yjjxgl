package com.datanew.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.datanew.dao.BaseDao;
import com.datanew.dto.Pages;
import com.datanew.dto.Result;
import com.datanew.model.ReportModel;
import com.datanew.service.ProService;
import com.datanew.util.ConfigureParser;
import com.datanew.util.StringUtil;
import com.hzzk.common.dto.OperatorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import winsoft.smsserviceclient.SmsService;

import java.text.SimpleDateFormat;
import java.util.*;

@Service("proService")
public class ProServiceImpl implements ProService {

  @Autowired
  BaseDao baseDao;

  /**
   * 查询发布日志记录
   * @param page
   * @param type
   * @return
     */
    public Pages getProReleaseLog(Pages page,String type) {
      if(type.equals("1")){
        String sql = "select * from pro_release_log t where sysdate<add_months(t.createdate,12*3)";
        List list = baseDao.selectMapsBySQL(sql, null, page.getOffset().intValue(), page.getLimit().intValue());
        Long count = baseDao.getCountBySQL("select count(1) from ("+sql+")", null);
        page.setRows(list);
        page.setTotal(Integer.valueOf(""+count));
      }else if(type.equals("2")){
        String sql = "select * from pro_release_log t where sysdate>add_months(t.createdate,12*3)";
        List list = baseDao.selectMapsBySQL(sql, null, page.getOffset().intValue(), page.getLimit().intValue());
        Long count = baseDao.getCountBySQL("select count(1) from ("+sql+")", null);
        page.setRows(list);
        page.setTotal(Integer.valueOf(""+count));
      }else {
        String sql = "select * from PRO_RELEASE_LOG t where 1=1 ";
        List list = baseDao.selectMapsBySQL(sql, null, page.getOffset().intValue(), page.getLimit().intValue());
        Long count = baseDao.getCountBySQL("select count(1) from ("+sql+")", null);
        page.setRows(list);
        page.setTotal(Integer.valueOf(""+count));
      }
    return page;
  }

  /**
   * 初始化发布数据
   * @param year
   * @param reportguid
   * @param reportname
   * @param operator
     * @param result
     */
  public void saveBaseInfo(String year, String reportguid, String reportname, OperatorDTO operator, Result result,String Remarks) {
    //查询是否有发布记录及批次号

    // int batch = 1;//TODO 年月201801
    //获取时间
   /* Calendar time = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
    int y = time.get(Calendar.YEAR);
    int mm = time.get(Calendar.MONTH + 1);*/
    String date = (new SimpleDateFormat("yyyyMM")).format(new Date());
    String batch = "【" + date + "】" + "_" + Remarks;
//    int batch = Integer.parseInt(date);
    String reportid = null;
    //查询年、报表guid、批次号是否存在  存在提示该年月已发布  不存在走发布流程
    String batch_sql = "select t.batch from pro_release_log t where t.year = " + year + " and t.reportmodelid = " + reportguid + " and substr(t.batch,2,6) = " + date;
    List batch_list = baseDao.selectBySql(batch_sql);
    if (batch_list.size() > 0) {
      result.setSuccess(false);
      result.setContent("已发布。。。");
    } else {
      String reportid_sql = "select t.reportid from report_model t where t.guid = " + reportguid;
      List reportid_list = baseDao.selectBySql(reportid_sql);
      if (reportid_list.size() > 0) {
        reportid = (String) reportid_list.get(0);
      }
      if (ConfigureParser.getPropert("JXGZID").equals(reportid)) {
        //初始化相关项目绩效表
       /* String sql = "select distinct p.eid, p.iid, p.iname " +
                "  from ysbz.payoutdata p, ysbz.workflow w " +
                " where p.year = w.year " +
                "   and p.iid like '2.%' " +
                "   and p.stepid = w.stepid " +
                "   and w.flowstep = 4 " +
                "   and w.deptype = 4 " +
//                "   and w.flowstep = 1 " +
//                "   and w.deptype = 1 " +
                "   and w.year = " + year;*/
       /*String sql = "select distinct pd.eid, pi.iid, pi.name\n" +
               "  from (select eid, iid, year\n" +
               "          from ysbz.payoutdata\n" +
               "         where year = \n" + year +
               "           and stepid = 10\n" +
               "           and iid like '2.%') pd,\n" +
               "       (select eid, iid, year, name\n" +
               "          from ysbz.payoutitem\n" +
               "         where year = \n" + year +
               "           and isaccept = 1) pi\n" +
               " where pd.year = pi.year\n" +
               "   and pd.iid = pi.iid\n" +
               "   and pd.eid = pi.eid\n" +
               "   and pi.year = " + year;*/
        String sql = "select distinct pd.eid, pi.iid, pi.name\n" +
                "  from (select eid, iid, year\n" +
                "          from ysbz.payoutdata\n" +
                "         where year = \n" + year +
                "           and stepid = 10\n" +
                "           and iid like '2.%') pd,\n" +
                "       (select eid, iid, year, name\n" +
                "          from ysbz.payoutitem\n" +
                "         where year = \n" + year +
                "           and isaccept = 1) pi,\n" +
                "       (select year, in_code, name from jczl.enterprise where year = " + year + ") e\n" +
                " where pd.year = pi.year\n" +
                "   and pd.year = e.year\n" +
                "   and pd.iid = pi.iid\n" +
                "   and pd.eid = pi.eid\n" +
                "   and pd.eid = e.in_code\n" +
                "   and pi.year = \n" + year +
                "   and e.name not like '%代编%'\n" +
                "   and e.name not in('永嘉县陡门小学',\n" +
                "              '永嘉县岩坦镇黄南小学',\n" +
                "              '永嘉县碧莲镇山坑小学',\n" +
                "              '永嘉县桥下镇昆阳中学',\n" +
                "              '永嘉县桥头镇朱涂中学',\n" +
                "              '永嘉县桥头镇白云中学',\n" +
                "              '永嘉县桥下镇第二中学',\n" +
                "              '永嘉县公费医疗管理委员会',\n" +
                "              '永嘉县财政局(城乡居民社会养老保险基金专户)',\n" +
                "              '永嘉县城乡居民基本医疗保险基金专户',\n" +
                "              '永嘉县财政局(县社会医疗救助财政专户)',\n" +
                "              '永嘉县财政局(县财政社会保障基金财政专户)',\n" +
                "              '永嘉县计生综治办',\n" +
                "              '永嘉县财政局社会保障风险补充基金专户',\n" +
                "              '永嘉县促就业资金专户')";
        List list = baseDao.selectMapsBySQL(sql);
        if (list.size() > 0) {
          for (int i = 1; i < list.size(); i++) {
            Map m = (Map) list.get(i);
            //初始化基本信息表
            String insert_sql1 = "insert into pro_base (year,eid,iid,batch,stepcode) values " +
                    "(" + year + ",'" + m.get("EID") + "','" + m.get("IID") + "', '" + batch + "', 1)";

            //资金支出计划表
            String insert_sql2 = "insert into pro_expend_plan (year,eid,iid,batch,line,stepcode) values " +
                    "(" + year + ",'" + m.get("EID") + "','" + m.get("IID") + "', '" + batch + "', 1, 1)";

            //项目实施计划表
            String insert_sql3 = "insert into pro_plement_plan (year,eid,iid,batch,line,stepcode) values " +
                    "(" + year + ",'" + m.get("EID") + "','" + m.get("IID") + "','" + batch + "', 1, 1)";

            //绩效目标表
            String insert_sql4 = "insert into pro_performance (year,eid,iid,batch,line,stepcode) values " +
                    "(" + year + ",'" + m.get("EID") + "','" + m.get("IID") + "', '" + batch + "', 1, 1)";

            //环节记录表
            String insert_sql5 = "insert into pro_base_step_log (year,eid,iid,batch,stepcode,reportguid) values " +
                    "(" + year + ",'" + m.get("EID") + "','" + m.get("IID") + "', '" + batch + "',1," + reportguid + ")";

            baseDao.executeBySql(insert_sql1);
            baseDao.executeBySql(insert_sql2);
            baseDao.executeBySql(insert_sql3);
            baseDao.executeBySql(insert_sql4);
            baseDao.executeBySql(insert_sql5);

            if (i % 100 == 0) {
              baseDao.flush();
            }
          }
          //保存发布记录表
          String insert_sql = "insert into pro_release_log (guid,isopen,deadlinedate,year,reportmodelid,reportmodelname,batch,createuserid,createusername,createdate) values " +
                  "(pro_release_log_sequence.nextval,'" + 0 + "'," + "sysdate + 30" + "," + year + "," + reportguid + ",'" + reportname + "','" + batch + "','" + operator.getGuId() + "','" + operator.getName() + "',sysdate)";
          baseDao.executeBySql(insert_sql);
          result.setSuccess(true);
          result.setContent("发布成功");

        } else {
          result.setSuccess(false);
          result.setContent("无发布相关数据");
        }

      } else if (ConfigureParser.getPropert("JXZPID").equals(reportid)) {
        //初始化相关项目绩效表
        /*String sql = "select distinct p.eid, p.iid, p.iname " +
                "  from ysbz.payoutdata p, ysbz.workflow w " +
                " where p.year = w.year " +
                "   and p.iid like '2.%' " +
                "   and p.stepid = w.stepid " +
                "   and w.flowstep = 4 " +
                "   and w.deptype = 4 " +
//                "   and w.flowstep = 1 " +
//                "   and w.deptype = 1 " +
                "   and w.year = " + year;*/
        /*String sql = "select distinct pd.eid, pi.iid, pi.name\n" +
                "  from (select eid, iid, year\n" +
                "          from ysbz.payoutdata\n" +
                "         where year = \n" + year +
                "           and stepid = 10\n" +
                "           and iid like '2.%') pd,\n" +
                "       (select eid, iid, year, name\n" +
                "          from ysbz.payoutitem\n" +
                "         where year = \n" + year +
                "           and isaccept = 1) pi\n" +
                " where pd.year = pi.year\n" +
                "   and pd.iid = pi.iid\n" +
                "   and pd.eid = pi.eid\n" +
                "   and pi.year = " + year;*/
        String sql = "select distinct pd.eid, pi.iid, pi.name\n" +
                "  from (select eid, iid, year\n" +
                "          from ysbz.payoutdata\n" +
                "         where year = \n" + year +
                "           and stepid = 10\n" +
                "           and iid like '2.%') pd,\n" +
                "       (select eid, iid, year, name\n" +
                "          from ysbz.payoutitem\n" +
                "         where year = \n" + year +
                "           and isaccept = 1) pi,\n" +
                "       (select year, in_code, name from jczl.enterprise where year = " + year + ") e\n" +
                " where pd.year = pi.year\n" +
                "   and pd.year = e.year\n" +
                "   and pd.iid = pi.iid\n" +
                "   and pd.eid = pi.eid\n" +
                "   and pd.eid = e.in_code\n" +
                "   and pi.year = \n" + year +
                "   and e.name not like '%代编%'\n" +
                "   and e.name not in('永嘉县陡门小学',\n" +
                "              '永嘉县岩坦镇黄南小学',\n" +
                "              '永嘉县碧莲镇山坑小学',\n" +
                "              '永嘉县桥下镇昆阳中学',\n" +
                "              '永嘉县桥头镇朱涂中学',\n" +
                "              '永嘉县桥头镇白云中学',\n" +
                "              '永嘉县桥下镇第二中学',\n" +
                "              '永嘉县公费医疗管理委员会',\n" +
                "              '永嘉县财政局(城乡居民社会养老保险基金专户)',\n" +
                "              '永嘉县城乡居民基本医疗保险基金专户',\n" +
                "              '永嘉县财政局(县社会医疗救助财政专户)',\n" +
                "              '永嘉县财政局(县财政社会保障基金财政专户)',\n" +
                "              '永嘉县计生综治办',\n" +
                "              '永嘉县财政局社会保障风险补充基金专户',\n" +
                "              '永嘉县促就业资金专户')";
        List list = baseDao.selectMapsBySQL(sql);
        if (list.size() > 0) {
          for (int i = 1; i < list.size(); i++) {
            Map m = (Map) list.get(i);
            //初始化基本信息表
            String insert_sql1 = "insert into ZCXMJXZPB (year,eid,iid,batch,stepcode) values " +
                    "(" + year + ",'" + m.get("EID") + "','" + m.get("IID") + "', '" + batch + "',1)";
            //环节记录表
            String insert_sql5 = "insert into pro_baseperformance_step_log (year,eid,iid,stepcode,batch,reportguid) values " +
                    "(" + year + ",'" + m.get("EID") + "','" + m.get("IID") + "',1, '" + batch + "'," + reportguid + ")";

            baseDao.executeBySql(insert_sql1);
            baseDao.executeBySql(insert_sql5);

            if (i % 100 == 0) {
              baseDao.flush();
            }
          }
          //保存发布记录表
          String insert_sql = "insert into pro_release_log (guid,isopen,deadlinedate,year,reportmodelid,reportmodelname,batch,createuserid,createusername,createdate) values " +
                  "(pro_release_log_sequence.nextval,'" + 0 + "'," + "sysdate + 30" + "," + year + "," + reportguid + ",'" + reportname + "','" + batch + "','" + operator.getGuId() + "','" + operator.getName() + "',sysdate)";
          baseDao.executeBySql(insert_sql);
          result.setSuccess(true);
          result.setContent("发布成功");

        } else {
          result.setSuccess(false);
          result.setContent("无发布相关数据");
        }
      } else if (ConfigureParser.getPropert("JXKHID").equals(reportid)) {
        //初始化相关项目绩效表
        /*String sql = "select distinct p.eid "+
                "  from ysbz.payoutdata p, ysbz.workflow w " +
                " where p.year = w.year " +
                "   and p.stepid = w.stepid " +
                "   and w.flowstep = 4 " +
                "   and w.deptype = 4 " +
//                "   and w.flowstep = 1 " +
//                "   and w.deptype = 1 " +
                "   and w.year = " + year;*/
        String sql = "select distinct pd.eid\n" +
                "  from (select eid, year\n" +
                "          from ysbz.payoutdata\n" +
                "         where year = \n" + year +
                "           and stepid = 10\n" +
                "           and iid like '2.%') pd,\n" +
                "       (select eid, year\n" +
                "          from ysbz.payoutitem\n" +
                "         where year = \n" + year +
                "           and isaccept = 1) pi\n" +
                " where pd.year = pi.year\n" +
                "   and pd.eid = pi.eid\n" +
                "   and pi.year = " + year;
        List list = baseDao.selectMapsBySQL(sql);
        if (list.size() > 0) {
          for (int i = 1; i < list.size(); i++) {
            Map m = (Map) list.get(i);
            //初始化基本信息表
            String insert_sql1 = "insert into jxgl_gzkhpfb (year,eid,batch,stepcode) values " +
                    "(" + year + ",'" + m.get("EID") + "','" + batch + "',1)";
            //环节记录表
            String insert_sql5 = "insert into pro_basemanage_step_log (year,eid,stepcode,batch,reportguid) values " +
                    "(" + year + ",'" + m.get("EID") + "',1,'" + batch + "'," + reportguid + ")";

            baseDao.executeBySql(insert_sql1);
            baseDao.executeBySql(insert_sql5);

            if (i % 100 == 0) {
              baseDao.flush();
            }
          }
          //保存发布记录表
          String insert_sql = "insert into pro_release_log (guid,isopen,deadlinedate,year,reportmodelid,reportmodelname,batch,createuserid,createusername,createdate) values " +
                  "(pro_release_log_sequence.nextval,'" + 0 + "'," + "sysdate + 30" + "," + year + "," + reportguid + ",'" + reportname + "','" + batch + "','" + operator.getGuId() + "','" + operator.getName() + "',sysdate)";
          baseDao.executeBySql(insert_sql);
          result.setSuccess(true);
          result.setContent("发布成功");
        }
      } else {
        result.setSuccess(false);
        result.setContent("未配置报表id");
      }
    }
  }
  /**
   * 查询发布的跟踪表基本信息
   * @param operator
   * @param page
   * @param search_year
   * @param nodenum
   * @param batch
     * @return
     */
  public Pages getProBase(OperatorDTO operator, Pages page, String search_year, String nodenum, String enterguid, String batch) {
    boolean flag = false;
    List list = null;
    Long count = 0l;
    //查询用户是否对应全部单位
    String all_entesql = "select t1.enterpriseguid from jczl.operenterprise t1 " +
            "where t1.appguid = 3 and t1.year = " + search_year + " and t1.operatorguid = " + operator.getGuId();
    List all_entelist = baseDao.selectBySql(all_entesql);
    if(all_entelist.size()==1){
      String enterpriseguid = ""+all_entelist.get(0);
      if ("0".equals(enterpriseguid)){
        flag = true;
      }
    }


    String sql = "select *\n" +
            "  from (select e.name          as ename,\n" +
            "               p.name          as iname,\n" +
            "               n1.nodename     as nodename,\n" +
            "               e.guid,\n" +
            "               r1.operatorguid,\n" +
            "               g.isopen,\n" +
            "               g.deadlinedate,\n" +
            "               t.*\n" +
            "          from pro_base           t,\n" +
            "               jczl.enterprise    e,\n" +
            "               ysbz.payoutitem    p,\n" +
            "               pro_base_step_log  s,\n" +
            "               report_model       r,\n" +
            "               base_process_nodes n1,\n" +
            "               jczl.operpost      r1,\n" +
            "               pro_release_log      g\n" +
            "         where t.eid = e.in_code\n" +
            "           and t.year = e.year\n" +
            "           and t.year = g.year\n" +
            "           and t.batch = g.batch\n" +
            "           and t.iid = p.iid\n" +
            "           and t.year = p.year\n" +
            "           and t.year = s.year\n" +
            "           and t.eid = s.eid\n" +
            "           and t.iid = s.iid\n" +
            "           and t.batch = s.batch\n" +
            "           and s.reportguid = r.guid\n" +
            "           and s.reportguid = g.reportmodelid\n" +
            "           and n1.roleid = r1.postguid\n" +
            "           and n1.processid = r.processid\n" +
            "           and t.stepcode = n1.nodenum\n" +
            "           and s.stepcode = t.stepcode\n" +
            "           and s.stepcode = 1\n" +
            "        \n" +
            "        union\n" +
            "        \n" +
            "        select e.name          as ename,\n" +
            "               p.name          as iname,\n" +
            "               n1.nodename     as nodename,\n" +
            "               e.guid,\n" +
            "               r1.operatorguid,\n" +
            "               g.isopen,\n" +
            "               g.deadlinedate,\n" +
            "               t.*\n" +
            "        \n" +
            "          from pro_base           t,\n" +
            "               jczl.enterprise    e,\n" +
            "               ysbz.payoutitem    p,\n" +
            "               pro_base_step_log  s,\n" +
            "               report_model       r,\n" +
            "               base_process_nodes n1,\n" +
            "               jczl.operpost      r1,\n" +
            "               pro_release_log      g\n" +
            "         where t.eid = e.in_code\n" +
            "           and t.year = e.year\n" +
            "           and t.year = g.year\n" +
            "           and t.batch = g.batch\n" +
            "           and t.iid = p.iid\n" +
            "           and t.year = p.year\n" +
            "           and t.year = s.year\n" +
            "           and t.eid = s.eid\n" +
            "           and t.iid = s.iid\n" +
            "           and t.batch = s.batch\n" +
            "           and s.reportguid = r.guid\n" +
            "           and n1.roleid = r1.postguid\n" +
            "           and s.reportguid = g.reportmodelid\n" +
            "           and n1.processid = r.processid\n" +
            "           and t.stepcode = n1.nodenum\n" +
            "           and s.stepcode = t.stepcode\n" +
            "           and s.stepcode = 2\n" +
            "           and e.guid in (select t1.enterpriseguid\n" +
            "                            from jczl.Enterdept t1\n" +
            "                           where t1.superenterguid = e.guid\n" +
            "                             and t1.year = t.year\n" +
            "                             and t1.appguid = 3)\n" +
            "        \n" +
            "        union\n" +
            "        \n" +
            "        select e.name          as ename,\n" +
            "               p.name          as iname,\n" +
            "               n1.nodename     as nodename,\n" +
            "               e.guid,\n" +
            "               r1.operatorguid,\n" +
            "               g.isopen,\n" +
            "               g.deadlinedate,\n" +
            "               t.*\n" +
            "        \n" +
            "          from pro_base           t,\n" +
            "               jczl.enterprise    e,\n" +
            "               ysbz.payoutitem    p,\n" +
            "               pro_base_step_log  s,\n" +
            "               report_model       r,\n" +
            "               base_process_nodes n1,\n" +
            "               jczl.operpost      r1,\n" +
            "               pro_release_log      g\n" +
            "         where t.eid = e.in_code\n" +
            "           and t.year = e.year\n" +
            "           and t.year = g.year\n" +
            "           and t.batch = g.batch\n" +
            "           and t.iid = p.iid\n" +
            "           and t.year = p.year\n" +
            "           and t.year = s.year\n" +
            "           and t.eid = s.eid\n" +
            "           and t.iid = s.iid\n" +
            "           and t.batch = s.batch\n" +
            "           and s.reportguid = r.guid\n" +
            "           and n1.roleid = r1.postguid\n" +
            "           and s.reportguid = g.reportmodelid\n" +
            "           and n1.processid = r.processid\n" +
            "           and t.stepcode = n1.nodenum\n" +
            "           and s.stepcode = t.stepcode\n" +
            "           and s.stepcode = 3\n" +
            "           and e.guid in (select ed.enterpriseguid\n" +
            "                            from ysbz.UserDivision ud, jczl.enterdivision ed\n" +
            "                           where ud.year = ed.year\n" +
            "                             and ud.divisionguid = ed.divisionguid\n" +
            "                             and ed.appguid = 3\n" +
            "                             and t.year = ed.year\n" +
            "                             and ud.userguid = "+operator.getGuId()+")\n" +
            "        ) a\n" +
            "\n" +
            " where a.operatorguid = "+operator.getGuId()+" ";

    if (!flag){//不是对应全部单位
      sql += "   and a.guid in (select t1.enterpriseguid\n" +
              "                    from jczl.operenterprise t1\n" +
              "                   where t1.appguid = 3\n" +
              "                     and t1.year = a.year\n" +
              "                     and t1.operatorguid = "+operator.getGuId()+") ";
    }

    if (search_year != null && !"".equals(search_year)){
      sql += " and a.year = " + search_year;
    }
    if (nodenum != null && !"".equals(nodenum)){
      sql += " and a.stepcode = " + nodenum;
    }
    if (enterguid != null && !"".equals(enterguid)){
      sql += " and a.guid = " + enterguid;
    }
    if (batch != null && !"".equals(batch)){
      sql += " and a.batch = '" + batch + "'";
    }


    if(page.getOffset()!=null&&page.getLimit()!=null){
      list = baseDao.selectMapsBySQL(sql + " order by a.eid,a.iid,a.stepcode", null, page.getOffset(), page.getLimit());
    }else{
      list = baseDao.selectMapsBySQL(sql + " order by a.eid,a.iid,a.stepcode", null);
    }

    count = baseDao.getCountBySQL("select count(1) from ("+sql+")", null);

    page.setRows(list);
    page.setTotal(Integer.valueOf(""+count));

    return page;
  }

  /**
   * 查询自评表发布的基本信息
   * @param operator
   * @param page
   * @param search_year
   * @param nodenum
   * @param batch
   * @return
   */
  @Override
  public Pages getBasePerformance(OperatorDTO operator, Pages page, String search_year, String nodenum, String enterguid, String batch) {
    boolean flag = false;
    List list = null;
    Long count = 0l;
    //查询用户是否对应全部单位
    String all_entesql = "select t1.enterpriseguid from jczl.operenterprise t1 " +
            "where t1.appguid = 3 and t1.year = " + search_year + " and t1.operatorguid = " + operator.getGuId();
    List all_entelist = baseDao.selectBySql(all_entesql);
    if(all_entelist.size()==1){
      String enterpriseguid = ""+all_entelist.get(0);
      if ("0".equals(enterpriseguid)){
        flag = true;
      }
    }


    String sql = "select *\n" +
            "  from (select e.name          as ename,\n" +
            "               p.name          as iname,\n" +
            "               n1.nodename     as nodename,\n" +
            "               e.guid,\n" +
            "               r1.operatorguid,\n" +
            "               g.isopen,\n" +
            "               g.deadlinedate,\n" +
            "               t.*\n" +
            "          from zcxmjxzpb          t,\n" +
            "               jczl.enterprise    e,\n" +
            "               ysbz.payoutitem    p,\n" +
            "               pro_baseperformance_step_log  s,\n" +
            "               report_model       r,\n" +
            "               base_process_nodes n1,\n" +
            "               jczl.operpost      r1,\n" +
            "               pro_release_log      g\n" +
            "         where t.eid = e.in_code\n" +
            "           and t.year = e.year\n" +
            "           and t.year = g.year\n" +
            "           and t.batch = g.batch\n" +
            "           and t.iid = p.iid\n" +
            "           and t.year = p.year\n" +
            "           and t.year = s.year\n" +
            "           and t.eid = s.eid\n" +
            "           and t.iid = s.iid\n" +
            "           and t.batch = s.batch\n" +
            "           and s.reportguid = r.guid\n" +
            "           and s.reportguid = g.reportmodelid\n" +
            "           and n1.roleid = r1.postguid\n" +
            "           and n1.processid = r.processid\n" +
            "           and t.stepcode = n1.nodenum\n" +
            "           and s.stepcode = t.stepcode\n" +
            "           and s.stepcode = 1\n" +
            "        \n" +
            "        union\n" +
            "        \n" +
            "        select e.name          as ename,\n" +
            "               p.name          as iname,\n" +
            "               n1.nodename     as nodename,\n" +
            "               e.guid,\n" +
            "               r1.operatorguid,\n" +
            "               g.isopen,\n" +
            "               g.deadlinedate,\n" +
            "               t.*\n" +
            "        \n" +
            "          from zcxmjxzpb           t,\n" +
            "               jczl.enterprise    e,\n" +
            "               ysbz.payoutitem    p,\n" +
            "               pro_baseperformance_step_log  s,\n" +
            "               report_model       r,\n" +
            "               base_process_nodes n1,\n" +
            "               jczl.operpost      r1,\n" +
            "               pro_release_log      g\n" +
            "         where t.eid = e.in_code\n" +
            "           and t.year = e.year\n" +
            "           and t.year = g.year\n" +
            "           and t.batch = g.batch\n" +
            "           and t.iid = p.iid\n" +
            "           and t.year = p.year\n" +
            "           and t.year = s.year\n" +
            "           and t.eid = s.eid\n" +
            "           and t.iid = s.iid\n" +
            "           and t.batch = s.batch\n" +
            "           and s.reportguid = r.guid\n" +
            "           and s.reportguid = g.reportmodelid\n" +
            "           and n1.roleid = r1.postguid\n" +
            "           and n1.processid = r.processid\n" +
            "           and t.stepcode = n1.nodenum\n" +
            "           and s.stepcode = t.stepcode\n" +
            "           and s.stepcode = 2\n" +
            /*"           and e.guid in (select t1.enterpriseguid\n" +
            "                            from jczl.Enterdept t1\n" +
            "                           where t1.superenterguid = e.guid\n" +
            "                             and t1.year = t.year\n" +
            "                             and t1.appguid = 3)\n" +*/
            "        \n" +
            "        union\n" +
            "        \n" +
            "        select e.name          as ename,\n" +
            "               p.name          as iname,\n" +
            "               n1.nodename     as nodename,\n" +
            "               e.guid,\n" +
            "               r1.operatorguid,\n" +
            "               g.isopen,\n" +
            "               g.deadlinedate,\n" +
            "               t.*\n" +
            "        \n" +
            "          from zcxmjxzpb           t,\n" +
            "               jczl.enterprise    e,\n" +
            "               ysbz.payoutitem    p,\n" +
            "               pro_baseperformance_step_log  s,\n" +
            "               report_model       r,\n" +
            "               base_process_nodes n1,\n" +
            "               jczl.operpost      r1,\n" +
            "               pro_release_log     g\n" +
            "         where t.eid = e.in_code\n" +
            "           and t.year = e.year\n" +
            "           and t.year = g.year\n" +
            "           and t.batch = g.batch\n" +
            "           and t.iid = p.iid\n" +
            "           and t.year = p.year\n" +
            "           and t.year = s.year\n" +
            "           and t.eid = s.eid\n" +
            "           and t.iid = s.iid\n" +
            "           and t.batch = s.batch\n" +
            "           and s.reportguid = r.guid\n" +
            "           and s.reportguid = g.reportmodelid\n" +
            "           and n1.roleid = r1.postguid\n" +
            "           and n1.processid = r.processid\n" +
            "           and t.stepcode = n1.nodenum\n" +
            "           and s.stepcode = t.stepcode\n" +
            "           and s.stepcode = 3\n" +
            "           and e.guid in (select ed.enterpriseguid\n" +
            "                            from ysbz.UserDivision ud, jczl.enterdivision ed\n" +
            "                           where ud.year = ed.year\n" +
            "                             and ud.divisionguid = ed.divisionguid\n" +
            "                             and ed.appguid = 3\n" +
            "                             and t.year = ed.year\n" +
            "                             and ud.userguid = "+operator.getGuId()+")\n" +
            "        ) a\n" +
            "\n" +
            " where a.operatorguid = "+operator.getGuId()+" ";

    if (!flag){//不是对应全部单位
      sql += "   and a.guid in (select t1.enterpriseguid\n" +
              "                    from jczl.operenterprise t1\n" +
              "                   where t1.appguid = 3\n" +
              "                     and t1.year = a.year\n" +
              "                     and t1.operatorguid = "+operator.getGuId()+") ";
    }

    if (search_year != null && !"".equals(search_year)){
      sql += " and a.year = " + search_year;
    }
    if (nodenum != null && !"".equals(nodenum)){
      sql += " and a.stepcode = " + nodenum;
    }
    if (enterguid != null && !"".equals(enterguid)){
      sql += " and a.guid = " + enterguid;
    }
    if (batch != null && !"".equals(batch)){
      sql += " and a.batch = '" + batch + "'";
    }


    if(page.getOffset()!=null&&page.getLimit()!=null){
      list = baseDao.selectMapsBySQL(sql + " order by a.eid,a.iid,a.stepcode", null, page.getOffset(), page.getLimit());
    }else{
      list = baseDao.selectMapsBySQL(sql + " order by a.eid,a.iid,a.stepcode", null);
    }

    count = baseDao.getCountBySQL("select count(1) from ("+sql+")", null);

    page.setRows(list);
    page.setTotal(Integer.valueOf(""+count));
    return page;
  }
/**
 * 查询查核表发布的基本信息
 * @param operator
 * @param page
 * @param search_year
 * @param nodenum
 * @param batch
 * @return
 */
  @Override
  public Pages getBaseManagePerformance(OperatorDTO operator, Pages page, String search_year, String nodenum, String enterguid, String batch) {
    boolean flag = false;
    List list = null;
    Long count = 0l;
    //查询用户是否对应全部单位
    String all_entesql = "select t1.enterpriseguid from jczl.operenterprise t1 " +
            "where t1.appguid = 3 and t1.year = " + search_year + " and t1.operatorguid = " + operator.getGuId();
    List all_entelist = baseDao.selectBySql(all_entesql);
    if(all_entelist.size()==1){
      String enterpriseguid = ""+all_entelist.get(0);
      if ("0".equals(enterpriseguid)){
        flag = true;
      }
    }


    String sql = "select *\n" +
            "  from (select e.name          as ename,\n" +
            "               n1.nodename     as nodename,\n" +
            "               e.guid,\n" +
            "               r1.operatorguid,\n" +
            "               p.isopen,\n" +
            "               p.deadlinedate,\n" +
            "               t.*\n" +
            "          from jxgl_gzkhpfb          t,\n" +
            "               jczl.enterprise    e,\n" +
            "               pro_basemanage_step_log  s,\n" +
            "               report_model       r,\n" +
            "               base_process_nodes n1,\n" +
            "               jczl.operpost      r1,\n" +
            "               pro_release_log     p\n" +
            "         where t.eid = e.in_code\n" +
            "           and t.year = e.year\n" +
            "           and t.year = s.year\n" +
            "           and t.year = p.year\n" +
            "           and t.batch = p.batch\n" +
            "           and t.eid = s.eid\n" +
            "           and t.batch = s.batch\n" +
            "           and s.reportguid = r.guid\n" +
            "           and s.reportguid = p.reportmodelid\n" +
            "           and n1.roleid = r1.postguid\n" +
            "           and n1.processid = r.processid\n" +
            "           and t.stepcode = n1.nodenum\n" +
            "           and s.stepcode = t.stepcode\n" +
            "           and s.stepcode = 1\n" +
            "        \n" +
            "        union\n" +
            "        \n" +
            "        select e.name          as ename,\n" +
            "               n1.nodename     as nodename,\n" +
            "               e.guid,\n" +
            "               r1.operatorguid,\n" +
            "               p.isopen,\n" +
            "               p.deadlinedate,\n" +
            "               t.*\n" +
            "        \n" +
            "          from jxgl_gzkhpfb           t,\n" +
            "               jczl.enterprise    e,\n" +
            "               pro_basemanage_step_log  s,\n" +
            "               report_model       r,\n" +
            "               base_process_nodes n1,\n" +
            "               jczl.operpost      r1,\n" +
            "               pro_release_log     p\n" +
            "         where t.eid = e.in_code\n" +
            "           and t.year = e.year\n" +
            "           and t.year = s.year\n" +
            "           and t.year = p.year\n" +
            "           and t.batch = p.batch\n" +
            "           and t.eid = s.eid\n" +
            "           and t.batch = s.batch\n" +
            "           and s.reportguid = r.guid\n" +
            "           and s.reportguid = p.reportmodelid\n" +
            "           and n1.roleid = r1.postguid\n" +
            "           and n1.processid = r.processid\n" +
            "           and t.stepcode = n1.nodenum\n" +
            "           and s.stepcode = t.stepcode\n" +
            "           and s.stepcode = 2\n" +
            "           and e.guid in (select t1.enterpriseguid\n" +
            "                            from jczl.Enterdept t1\n" +
            "                           where t1.superenterguid = e.guid\n" +
            "                             and t1.year = t.year\n" +
            "                             and t1.appguid = 3)\n" +
            "        \n" +
            "        union\n" +
            "        \n" +
            "        select e.name          as ename,\n" +
            "               n1.nodename     as nodename,\n" +
            "               e.guid,\n" +
            "               r1.operatorguid,\n" +
            "               p.isopen,\n" +
            "               p.deadlinedate,\n" +
            "               t.*\n" +
            "        \n" +
            "          from jxgl_gzkhpfb           t,\n" +
            "               jczl.enterprise    e,\n" +
            "               pro_basemanage_step_log  s,\n" +
            "               report_model       r,\n" +
            "               base_process_nodes n1,\n" +
            "               jczl.operpost      r1,\n" +
            "               pro_release_log     p\n" +
            "         where t.eid = e.in_code\n" +
            "           and t.year = e.year\n" +
            "           and t.year = s.year\n" +
            "           and t.year = p.year \n" +
            "           and t.batch = p.batch\n" +
            "           and t.eid = s.eid\n" +
            "           and t.batch = s.batch\n" +
            "           and s.reportguid = r.guid\n" +
            "           and s.reportguid = p.reportmodelid\n" +
            "           and n1.roleid = r1.postguid\n" +
            "           and n1.processid = r.processid\n" +
            "           and t.stepcode = n1.nodenum\n" +
            "           and s.stepcode = t.stepcode\n" +
            "           and s.stepcode = 3\n" +
            "           and e.guid in (select ed.enterpriseguid\n" +
            "                            from ysbz.UserDivision ud, jczl.enterdivision ed\n" +
            "                           where ud.year = ed.year\n" +
            "                             and ud.divisionguid = ed.divisionguid\n" +
            "                             and ed.appguid = 3\n" +
            "                             and t.year = ed.year\n" +
            "                             and ud.userguid = "+operator.getGuId()+")\n" +
            "        ) a\n" +
            "\n" +
            " where a.operatorguid = "+operator.getGuId()+" ";

    if (!flag){//不是对应全部单位
      sql += "   and a.guid in (select t1.enterpriseguid\n" +
              "                    from jczl.operenterprise t1\n" +
              "                   where t1.appguid = 3\n" +
              "                     and t1.year = a.year\n" +
              "                     and t1.operatorguid = "+operator.getGuId()+") ";
    }

    if (search_year != null && !"".equals(search_year)){
      sql += " and a.year = " + search_year;
    }
    if (nodenum != null && !"".equals(nodenum)){
      sql += " and a.stepcode = " + nodenum;
    }
    if (enterguid != null && !"".equals(enterguid)){
      sql += " and a.guid = " + enterguid;
    }
    if (batch != null && !"".equals(batch)){
      sql += " and a.batch = '" + batch +"'";
    }


    if(page.getOffset()!=null&&page.getLimit()!=null){
      list = baseDao.selectMapsBySQL(sql + " order by a.eid,a.stepcode", null, page.getOffset(), page.getLimit());
    }else{
      list = baseDao.selectMapsBySQL(sql + " order by a.eid,a.stepcode", null);
    }

    count = baseDao.getCountBySQL("select count(1) from ("+sql+")", null);

    page.setRows(list);
    page.setTotal(Integer.valueOf(""+count));
    return page;
  }

  /**
   * 跟踪表查询环节信息
   * @param operator
   * @param page
   * @param search_year
   * @param nodenum
   * @param batch
   * @return
   */
  public Pages getProStepInfo(OperatorDTO operator, Pages page, String search_year, String nodenum, String enterguid, String batch) {
    boolean flag = false;
    List list = null;
    Long count = 0l;
    //查询用户是否对应全部单位
    String all_entesql = "select t1.enterpriseguid from jczl.operenterprise t1 " +
            "where t1.appguid = 3 and t1.year = " + search_year + " and t1.operatorguid = " + operator.getGuId();
    List all_entelist = baseDao.selectBySql(all_entesql);
    if(all_entelist.size()==1){
      String enterpriseguid = ""+all_entelist.get(0);
      if ("0".equals(enterpriseguid)){
        flag = true;
      }
    }

    String sql = "select a.year,\n" +
            "       a.stepcode,\n" +
            "       nvl2(n1.nodename, n1.nodename, '结束') as nodename,\n" +
            "       a.ename,\n" +
            "       a.eid,\n" +
            "       a.guid,\n" +
            "       a.batch,\n" +
            "       a.reportguid,\n" +
            "       a.phoneno\n" +
            "  from (select min(s.stepcode) as stepcode,\n" +
            "               '[' || e.isbn_code || ']' || e.name as ename,\n" +
            "               e.isbn_code,\n" +
            "               s.year,\n" +
            "               s.eid,\n" +
            "               e.guid,\n" +
            "               s.batch,\n" +
            "               s.reportguid,\n" +
            "               p.phoneno \n" +
            "          from pro_base_step_log s, jczl.enterprise e left join base_enterphone p on p.enterguid = e.guid \n" +
            "         where s.eid = e.in_code\n" +
            "           and s.year = e.year\n" +
            "           and s.stepcode <> 0\n" +
            "         group by e.name,\n" +
            "                  e.isbn_code,\n" +
            "                  s.year,\n" +
            "                  s.eid,\n" +
            "                  e.guid,\n" +
            "                  s.batch,\n" +
            "                  s.reportguid,\n" +
            "                  p.phoneno " +
            "        \n" +
            "        union\n" +
            "        \n" +
            "        select max(s.stepcode) as stepcode,\n" +
            "               '[' || e.isbn_code || ']' || e.name as ename,\n" +
            "               e.isbn_code,\n" +
            "               s.year,\n" +
            "               s.eid,\n" +
            "               e.guid,\n" +
            "               s.batch,\n" +
            "               s.reportguid,\n" +
            "               p.phoneno \n" +
            "          from pro_base_step_log s, jczl.enterprise e left join base_enterphone p on p.enterguid = e.guid \n" +
            "         where s.eid = e.in_code\n" +
            "           and s.year = e.year\n" +
            "         group by e.name,\n" +
            "                  e.isbn_code,\n" +
            "                  s.year,\n" +
            "                  s.eid,\n" +
            "                  e.guid,\n" +
            "                  s.batch,\n" +
            "                  s.reportguid,\n" +
            "                  p.phoneno " +
            "        having max(s.stepcode) = 0) a\n" +
            "  left join base_process_nodes n1\n" +
            "    on a.stepcode = n1.nodenum\n" +
            "  left join report_model r\n" +
            "    on n1.processid = r.processid\n" +
            " where 1 =1  and a.reportguid = r.guid ";



    if (!flag){//不是对应全部单位
      sql += "   and a.guid in (select t1.enterpriseguid\n" +
              "                    from jczl.operenterprise t1\n" +
              "                   where t1.appguid = 3\n" +
              "                     and t1.year = a.year\n" +
              "                     and t1.operatorguid = "+operator.getGuId() +")";
    }

    if (search_year != null && !"".equals(search_year)){
      sql += " and a.year = " + search_year;
    }
    if (nodenum != null && !"".equals(nodenum)){
      sql += " and a.stepcode = " + nodenum;
    }
    if (enterguid != null && !"".equals(enterguid)){
      sql += " and a.guid = " + enterguid;
    }
    if (batch != null && !"".equals(batch)){
      sql += " and a.batch = '" + batch + "'";
    }


    if(page.getOffset()!=null&&page.getLimit()!=null){
      list = baseDao.selectMapsBySQL(sql + " order by a.isbn_code, a.stepcode ", null, page.getOffset(), page.getLimit());
    }else{
      list = baseDao.selectMapsBySQL(sql + " order by a.isbn_code, a.stepcode ", null);
    }

    count = baseDao.getCountBySQL("select count(1) from ("+sql+")", null);

    page.setRows(list);
    page.setTotal(Integer.valueOf(""+count));

    return page;
  }
  /**
   * 自评表查询环节信息
   * @param operator
   * @param page
   * @param search_year
   * @param nodenum
   * @param batch
   * @return
   */
  @Override
  public Pages getPerformanceStepInfo(OperatorDTO operator, Pages page, String search_year, String nodenum, String enterguid, String batch) {
    boolean flag = false;
    List list = null;
    Long count = 0l;
    //查询用户是否对应全部单位
    String all_entesql = "select t1.enterpriseguid from jczl.operenterprise t1 " +
            "where t1.appguid = 3 and t1.year = " + search_year + " and t1.operatorguid = " + operator.getGuId();
    List all_entelist = baseDao.selectBySql(all_entesql);
    if(all_entelist.size()==1){
      String enterpriseguid = ""+all_entelist.get(0);
      if ("0".equals(enterpriseguid)){
        flag = true;
      }
    }

    String sql = "select a.year,\n" +
            "       a.stepcode,\n" +
            "       nvl2(n1.nodename, n1.nodename, '结束') as nodename,\n" +
            "       a.ename,\n" +
            "       a.eid,\n" +
            "       a.guid,\n" +
            "       a.batch,\n" +
            "       a.reportguid,\n" +
            "       a.phoneno\n" +
            "  from (select min(s.stepcode) as stepcode,\n" +
            "               '[' || e.isbn_code || ']' || e.name as ename,\n" +
            "               e.isbn_code,\n" +
            "               s.year,\n" +
            "               s.eid,\n" +
            "               e.guid,\n" +
            "               s.batch,\n" +
            "               s.reportguid,\n" +
            "               p.phoneno \n" +
            "          from pro_baseperformance_step_log s, jczl.enterprise e left join base_enterphone p on p.enterguid = e.guid \n" +
            "         where s.eid = e.in_code\n" +
            "           and s.year = e.year\n" +
            "           and s.stepcode <> 0\n" +
            "         group by e.name,\n" +
            "                  e.isbn_code,\n" +
            "                  s.year,\n" +
            "                  s.eid,\n" +
            "                  e.guid,\n" +
            "                  s.batch,\n" +
            "                  s.reportguid,\n" +
            "                  p.phoneno " +
            "        \n" +
            "        union\n" +
            "        \n" +
            "        select max(s.stepcode) as stepcode,\n" +
            "               '[' || e.isbn_code || ']' || e.name as ename,\n" +
            "               e.isbn_code,\n" +
            "               s.year,\n" +
            "               s.eid,\n" +
            "               e.guid,\n" +
            "               s.batch,\n" +
            "               s.reportguid,\n" +
            "               p.phoneno \n" +
            "          from pro_baseperformance_step_log s, jczl.enterprise e left join base_enterphone p on p.enterguid = e.guid \n" +
            "         where s.eid = e.in_code\n" +
            "           and s.year = e.year\n" +
            "         group by e.name,\n" +
            "                  e.isbn_code,\n" +
            "                  s.year,\n" +
            "                  s.eid,\n" +
            "                  e.guid,\n" +
            "                  s.batch,\n" +
            "                  s.reportguid,\n" +
            "                  p.phoneno " +
            "        having max(s.stepcode) = 0) a\n" +
            "  left join base_process_nodes n1\n" +
            "    on a.stepcode = n1.nodenum\n" +
            "  left join report_model r\n" +
            "    on n1.processid = r.processid\n" +
            " where 1 =1  and a.reportguid = r.guid ";



    if (!flag){//不是对应全部单位
      sql += "   and a.guid in (select t1.enterpriseguid\n" +
              "                    from jczl.operenterprise t1\n" +
              "                   where t1.appguid = 3\n" +
              "                     and t1.year = a.year\n" +
              "                     and t1.operatorguid = "+operator.getGuId() +")";
    }

    if (search_year != null && !"".equals(search_year)){
      sql += " and a.year = " + search_year;
    }
    if (nodenum != null && !"".equals(nodenum)){
      sql += " and a.stepcode = " + nodenum;
    }
    if (enterguid != null && !"".equals(enterguid)){
      sql += " and a.guid = " + enterguid;
    }
    if (batch != null && !"".equals(batch)){
      sql += " and a.batch = '" + batch + "'";
    }


    if(page.getOffset()!=null&&page.getLimit()!=null){
      list = baseDao.selectMapsBySQL(sql + " order by a.isbn_code, a.stepcode ", null, page.getOffset(), page.getLimit());
    }else{
      list = baseDao.selectMapsBySQL(sql + " order by a.isbn_code, a.stepcode ", null);
    }

    count = baseDao.getCountBySQL("select count(1) from ("+sql+")", null);

    page.setRows(list);
    page.setTotal(Integer.valueOf(""+count));
    return page;
  }
  /**
   * 管理考核表查询环节信息
   * @param operator
   * @param page
   * @param search_year
   * @param nodenum
   * @param batch
   * @return
   */
  @Override
  public Pages getPerformanceManageStepInfo(OperatorDTO operator, Pages page, String search_year, String nodenum, String enterguid, String batch) {
    boolean flag = false;
    List list = null;
    Long count = 0l;
    //查询用户是否对应全部单位
    String all_entesql = "select t1.enterpriseguid from jczl.operenterprise t1 " +
            "where t1.appguid = 3 and t1.year = " + search_year + " and t1.operatorguid = " + operator.getGuId();
    List all_entelist = baseDao.selectBySql(all_entesql);
    if(all_entelist.size()==1){
      String enterpriseguid = ""+all_entelist.get(0);
      if ("0".equals(enterpriseguid)){
        flag = true;
      }
    }

    String sql = "select a.year,\n" +
            "       a.stepcode,\n" +
            "       nvl2(n1.nodename, n1.nodename, '结束') as nodename,\n" +
            "       a.ename,\n" +
            "       a.eid,\n" +
            "       a.guid,\n" +
            "       a.batch,\n" +
            "       a.reportguid,\n" +
            "       a.phoneno\n" +
            "  from (select min(s.stepcode) as stepcode,\n" +
            "               '[' || e.isbn_code || ']' || e.name as ename,\n" +
            "               e.isbn_code,\n" +
            "               s.year,\n" +
            "               s.eid,\n" +
            "               e.guid,\n" +
            "               s.batch,\n" +
            "               s.reportguid,\n" +
            "               p.phoneno \n" +
            "          from pro_basemanage_step_log s, jczl.enterprise e left join base_enterphone p on p.enterguid = e.guid \n" +
            "         where s.eid = e.in_code\n" +
            "           and s.year = e.year\n" +
            "           and s.stepcode <> 0\n" +
            "         group by e.name,\n" +
            "                  e.isbn_code,\n" +
            "                  s.year,\n" +
            "                  s.eid,\n" +
            "                  e.guid,\n" +
            "                  s.batch,\n" +
            "                  s.reportguid,\n" +
            "                  p.phoneno " +
            "        \n" +
            "        union\n" +
            "        \n" +
            "        select max(s.stepcode) as stepcode,\n" +
            "               '[' || e.isbn_code || ']' || e.name as ename,\n" +
            "               e.isbn_code,\n" +
            "               s.year,\n" +
            "               s.eid,\n" +
            "               e.guid,\n" +
            "               s.batch,\n" +
            "               s.reportguid,\n" +
            "               p.phoneno \n" +
            "          from pro_basemanage_step_log s, jczl.enterprise e left join base_enterphone p on p.enterguid = e.guid \n" +
            "         where s.eid = e.in_code\n" +
            "           and s.year = e.year\n" +
            "         group by e.name,\n" +
            "                  e.isbn_code,\n" +
            "                  s.year,\n" +
            "                  s.eid,\n" +
            "                  e.guid,\n" +
            "                  s.batch,\n" +
            "                  s.reportguid,\n" +
            "                  p.phoneno " +
            "        having max(s.stepcode) = 0) a\n" +
            "  left join base_process_nodes n1\n" +
            "    on a.stepcode = n1.nodenum\n" +
            "  left join report_model r\n" +
            "    on n1.processid = r.processid\n" +
            " where 1 =1  and a.reportguid = r.guid ";



    if (!flag){//不是对应全部单位
      sql += "   and a.guid in (select t1.enterpriseguid\n" +
              "                    from jczl.operenterprise t1\n" +
              "                   where t1.appguid = 3\n" +
              "                     and t1.year = a.year\n" +
              "                     and t1.operatorguid = "+operator.getGuId() +")";
    }

    if (search_year != null && !"".equals(search_year)){
      sql += " and a.year = " + search_year;
    }
    if (nodenum != null && !"".equals(nodenum)){
      sql += " and a.stepcode = " + nodenum;
    }
    if (enterguid != null && !"".equals(enterguid)){
      sql += " and a.guid = " + enterguid;
    }
    if (batch != null && !"".equals(batch)){
      sql += " and a.batch = '" + batch + "'";
    }


    if(page.getOffset()!=null&&page.getLimit()!=null){
      list = baseDao.selectMapsBySQL(sql + " order by a.isbn_code, a.stepcode ", null, page.getOffset(), page.getLimit());
    }else{
      list = baseDao.selectMapsBySQL(sql + " order by a.isbn_code, a.stepcode ", null);
    }

    count = baseDao.getCountBySQL("select count(1) from ("+sql+")", null);

    page.setRows(list);
    page.setTotal(Integer.valueOf(""+count));
    return page;
  }

  /**
   * 跟踪表查询环节详情
   * @param operator
   * @param page
   * @param year
   * @param batch
   * @return
   */
  public Pages getProStepDetail(OperatorDTO operator, Pages page, String year, String enterguid, String batch) {
    boolean flag = false;
    List list = null;
    Long count = 0l;
    //查询用户是否对应全部单位
    String all_entesql = "select t1.enterpriseguid from jczl.operenterprise t1 " +
            "where t1.appguid = 3 and t1.year = " + year + " and t1.operatorguid = " + operator.getGuId();
    List all_entelist = baseDao.selectBySql(all_entesql);
    if(all_entelist.size()==1){
      String enterpriseguid = ""+all_entelist.get(0);
      if ("0".equals(enterpriseguid)){
        flag = true;
      }
    }

    String reportid = null;
    String id_sql = "select t.processid from REPORT_MODEL t where t.reportid = '"+ConfigureParser.getPropert("JXGZID")+"'";
    List idlist = baseDao.selectBySql(id_sql);
    if(idlist.size() > 0){
      reportid = ""+idlist.get(0);
    }

    String sql = "select '[' || e.isbn_code || ']' || e.name as ename,\n" +
            "       s.year,\n" +
            "       s.eid,\n" +
            "       e.guid,\n" +
            "       s.iid,\n" +
            "       p.name as iname,\n" +
            "       s.batch,\n" +
            "       s.stepcode,\n" +
            "       nvl2(n1.nodename, n1.nodename, '结束') as nodename,\n" +
            "       s.reportguid\n" +
            "  from jczl.enterprise e,ysbz.payoutitem p, pro_base_step_log s\n" +
            "  left join base_process_nodes n1\n" +
            "    on s.stepcode = n1.nodenum\n" +
            "   and n1.processid = "+reportid+"\n" +
            /*"  left join report_model r\n" +
            "    on n1.processid = r.processid\n" +*/
            " where s.eid = e.in_code\n" +
            "   and s.year = e.year\n" +
            "   and s.iid = p.iid\n" +
            "   and s.year = p.year\n";
    //"   and s.reportguid = r.guid\n" ;


    if (!flag){//不是对应全部单位
      sql += "   and e.guid in (select t1.enterpriseguid\n" +
              "                    from jczl.operenterprise t1\n" +
              "                   where t1.appguid = 3\n" +
              "                     and t1.year = s.year\n" +
              "                     and t1.operatorguid = "+operator.getGuId() +")";
    }

    if (year != null && !"".equals(year)){
      sql += " and s.year = " + year;
    }
    if (enterguid != null && !"".equals(enterguid)){
      sql += " and e.guid = " + enterguid;
    }
    if (batch != null && !"".equals(batch)){
      sql += " and s.batch = '" + batch + "'";
    }

    list = baseDao.selectMapsBySQL(sql + " order by e.isbn_code, s.stepcode ", null, page.getOffset(), page.getLimit());

    count = baseDao.getCountBySQL("select count(1) from ("+sql+")", null);

    page.setRows(list);
    page.setTotal(Integer.valueOf(""+count));

    return page;
  }

/**
 * 自评表查询环节详情
 * @param operator
 * @param page
 * @param year
 * @param batch
 * @return
 */
  @Override
  public Pages getPerformanceStepDetail(OperatorDTO operator, Pages page, String year, String enterguid, String batch) {
    boolean flag = false;
    List list = null;
    Long count = 0l;
    //查询用户是否对应全部单位
    String all_entesql = "select t1.enterpriseguid from jczl.operenterprise t1 " +
            "where t1.appguid = 3 and t1.year = " + year + " and t1.operatorguid = " + operator.getGuId();
    List all_entelist = baseDao.selectBySql(all_entesql);
    if(all_entelist.size()==1){
      String enterpriseguid = ""+all_entelist.get(0);
      if ("0".equals(enterpriseguid)){
        flag = true;
      }
    }

    String reportid = null;
    String id_sql = "select t.processid from REPORT_MODEL t where t.reportid = '"+ConfigureParser.getPropert("JXZPID")+"'";
    List idlist = baseDao.selectBySql(id_sql);
    if(idlist.size() > 0){
      reportid = ""+idlist.get(0);
    }



    String sql = "select '[' || e.isbn_code || ']' || e.name as ename,\n" +
            "       s.year,\n" +
            "       s.eid,\n" +
            "       e.guid,\n" +
            "       s.iid,\n" +
            "       p.name as iname,\n" +
            "       s.batch,\n" +
            "       s.stepcode,\n" +
            "       nvl2(n1.nodename, n1.nodename, '结束') as nodename,\n" +
            "       s.reportguid\n" +
            "  from jczl.enterprise e,ysbz.payoutitem p, pro_baseperformance_step_log s\n" +
            "  left join base_process_nodes n1\n" +
            "    on s.stepcode = n1.nodenum" +
            "   and n1.processid = "+reportid+"\n" +
           /* "  left join report_model r\n" +
            "    on n1.processid = r.processid\n" +*/
            " where s.eid = e.in_code\n" +
            "   and s.year = e.year\n" +
            "   and s.iid = p.iid\n" +
            "   and s.year = p.year\n";
            //"   and s.reportguid = r.guid\n" ;


    if (!flag){//不是对应全部单位
      sql += "   and e.guid in (select t1.enterpriseguid\n" +
              "                    from jczl.operenterprise t1\n" +
              "                   where t1.appguid = 3\n" +
              "                     and t1.year = s.year\n" +
              "                     and t1.operatorguid = "+operator.getGuId() +")";
    }

    if (year != null && !"".equals(year)){
      sql += " and s.year = " + year;
    }
    if (enterguid != null && !"".equals(enterguid)){
      sql += " and e.guid = " + enterguid;
    }
    if (batch != null && !"".equals(batch)){
      sql += " and s.batch = '" + batch + "'";
    }

    list = baseDao.selectMapsBySQL(sql + " order by e.isbn_code, s.stepcode ", null, page.getOffset(), page.getLimit());

    count = baseDao.getCountBySQL("select count(1) from ("+sql+")", null);

    page.setRows(list);
    page.setTotal(Integer.valueOf(""+count));

    return page;
  }

  /**
   * 考核表查询环节详情
   * @param operator
   * @param page
   * @param year
   * @param batch
   * @return
   */
  @Override
  public Pages getManagePerformanceStepDetail(OperatorDTO operator, Pages page, String year, String enterguid, String batch) {
    boolean flag = false;
    List list = null;
    Long count = 0l;
    //查询用户是否对应全部单位
    String all_entesql = "select t1.enterpriseguid from jczl.operenterprise t1 " +
            "where t1.appguid = 3 and t1.year = " + year + " and t1.operatorguid = " + operator.getGuId();
    List all_entelist = baseDao.selectBySql(all_entesql);
    if(all_entelist.size()==1){
      String enterpriseguid = ""+all_entelist.get(0);
      if ("0".equals(enterpriseguid)){
        flag = true;
      }
    }

    String reportid = null;
    String id_sql = "select t.processid from REPORT_MODEL t where t.reportid = '"+ConfigureParser.getPropert("JXGZID")+"'";
    List idlist = baseDao.selectBySql(id_sql);
    if(idlist.size() > 0){
      reportid = ""+idlist.get(0);
    }

    String sql = "select '[' || e.isbn_code || ']' || e.name as ename,\n" +
            "       s.year,\n" +
            "       s.eid,\n" +
            "       e.guid,\n" +
//            "       p.name as iname,\n" +
            "       s.batch,\n" +
            "       s.stepcode,\n" +
            "       nvl2(n1.nodename, n1.nodename, '结束') as nodename,\n" +
            "       s.reportguid\n" +
            "  from jczl.enterprise e, pro_basemanage_step_log s\n" +
            "  left join base_process_nodes n1\n" +
            "    on s.stepcode = n1.nodenum\n" +
            "   and n1.processid = "+reportid+"\n" +
            /*"  left join report_model r\n" +
            "    on n1.processid = r.processid\n" +*/
            " where s.eid = e.in_code\n" +
            "   and s.year = e.year\n" ;
//            "   and s.year = p.year\n";,ysbz.payoutitem p
            //"   and s.reportguid = r.guid\n" ;


    if (!flag){//不是对应全部单位
      sql += "   and e.guid in (select t1.enterpriseguid\n" +
              "                    from jczl.operenterprise t1\n" +
              "                   where t1.appguid = 3\n" +
              "                     and t1.year = s.year\n" +
              "                     and t1.operatorguid = "+operator.getGuId() +")";
    }

    if (year != null && !"".equals(year)){
      sql += " and s.year = " + year;
    }
    if (enterguid != null && !"".equals(enterguid)){
      sql += " and e.guid = " + enterguid;
    }
    if (batch != null && !"".equals(batch)){
      sql += " and s.batch = '" + batch + "'";
    }

    list = baseDao.selectMapsBySQL(sql + " order by e.isbn_code, s.stepcode ", null, page.getOffset(), page.getLimit());

    count = baseDao.getCountBySQL("select count(1) from ("+sql+")", null);

    page.setRows(list);
    page.setTotal(Integer.valueOf(""+count));

    return page;
  }

  /**
   * 绩效科报表查看
   * @param operator
   * @param page
   * @param search_year
   * @param nodenum
   * @return
   */
  public Pages getProBaseAllStep(OperatorDTO operator, Pages page, String search_year, String nodenum, String enterguid, String batch) {
    boolean flag = false;
    List list = null;
    Long count = 0l;
    //查询用户是否对应全部单位
    String all_entesql = "select t1.enterpriseguid from jczl.operenterprise t1 " +
            "where t1.appguid = 3 and t1.year = " + search_year + " and t1.operatorguid = " + operator.getGuId();
    List all_entelist = baseDao.selectBySql(all_entesql);
    if(all_entelist.size()==1){
      String enterpriseguid = ""+all_entelist.get(0);
      if ("0".equals(enterpriseguid)){
        flag = true;
      }
    }

    String sql = "select e.name as ename, p.name as iname, nvl2(n1.nodename,n1.nodename,'已结束') as nodename, t.*\n" +
            "  from jczl.enterprise e, ysbz.payoutitem p, pro_base t\n" +
            "  left join pro_base_step_log s\n" +
            "    on t.year = s.year\n" +
            "   and t.eid = s.eid\n" +
            "   and t.iid = s.iid\n" +
            "   and t.batch = s.batch\n" +
            "  left join report_model r\n" +
            "    on s.reportguid = r.guid\n" +
            "  left join base_process_nodes n1\n" +
            "    on t.stepcode = n1.nodenum\n" +
            "   and n1.processid = r.processid\n" +
            "\n" +
            " where t.eid = e.in_code\n" +
            "   and t.year = e.year\n" +
            "   and t.iid = p.iid\n" +
            "   and t.year = p.year " ;


    if (!flag){//不是对应全部单位
      sql += "   and e.guid in (select t1.enterpriseguid\n" +
              "                    from jczl.operenterprise t1\n" +
              "                   where t1.appguid = 3\n" +
              "                     and t1.year = s.year\n" +
              "                     and t1.operatorguid = "+operator.getGuId() +")";
    }

    if (search_year != null && !"".equals(search_year)){
      sql += " and t.year = " + search_year;
    }
    if (nodenum != null && !"".equals(nodenum)){
      sql += " and t.stepcode = " + nodenum;
    }
    if (enterguid != null && !"".equals(enterguid)){
      sql += " and e.guid = " + enterguid;
    }
    if (batch != null && !"".equals(batch)){
      sql += " and t.batch = '" + batch + "'";
    }


    if(page.getOffset()!=null&&page.getLimit()!=null){
      list = baseDao.selectMapsBySQL(sql + " order by t.eid,t.iid,t.stepcode", null, page.getOffset(), page.getLimit());
    }

    count = baseDao.getCountBySQL("select count(1) from ("+sql+")", null);

    page.setRows(list);
    page.setTotal(Integer.valueOf(""+count));

    return page;
  }
  /**
   * 绩效科报表查看
   * @param operator
   * @param page
   * @param search_year
   * @param nodenum
   * @return
   */
  @Override
  public Pages getBasePerformanceAllStep(OperatorDTO operator, Pages page, String search_year, String nodenum, String enterguid, String batch) {
    boolean flag = false;
    List list = null;
    Long count = 0l;
    //查询用户是否对应全部单位
    String all_entesql = "select t1.enterpriseguid from jczl.operenterprise t1 " +
            "where t1.appguid = 3 and t1.year = " + search_year + " and t1.operatorguid = " + operator.getGuId();
    List all_entelist = baseDao.selectBySql(all_entesql);
    if(all_entelist.size()==1){
      String enterpriseguid = ""+all_entelist.get(0);
      if ("0".equals(enterpriseguid)){
        flag = true;
      }
    }

    String sql = "select e.name as ename, p.name as iname, nvl2(n1.nodename,n1.nodename,'已结束') as nodename, t.*\n" +
            "  from jczl.enterprise e, ysbz.payoutitem p, zcxmjxzpb t\n" +
            "  left join pro_baseperformance_step_log s\n" +
            "    on t.year = s.year\n" +
            "   and t.eid = s.eid\n" +
            "   and t.iid = s.iid\n" +
            "   and t.batch = s.batch\n" +
            "  left join report_model r\n" +
            "    on s.reportguid = r.guid\n" +
            "  left join base_process_nodes n1\n" +
            "    on t.stepcode = n1.nodenum\n" +
            "   and n1.processid = r.processid\n" +
            "\n" +
            " where t.eid = e.in_code\n" +
            "   and t.year = e.year\n" +
            "   and t.iid = p.iid\n" +
            "   and t.year = p.year " ;


    if (!flag){//不是对应全部单位
      sql += "   and e.guid in (select t1.enterpriseguid\n" +
              "                    from jczl.operenterprise t1\n" +
              "                   where t1.appguid = 3\n" +
              "                     and t1.year = s.year\n" +
              "                     and t1.operatorguid = "+operator.getGuId() +")";
    }

    if (search_year != null && !"".equals(search_year)){
      sql += " and t.year = " + search_year;
    }
    if (nodenum != null && !"".equals(nodenum)){
      sql += " and t.stepcode = " + nodenum;
    }
    if (enterguid != null && !"".equals(enterguid)){
      sql += " and e.guid = " + enterguid;
    }
    if (batch != null && !"".equals(batch)){
      sql += " and t.batch = '" + batch + "'";
    }


    if(page.getOffset()!=null&&page.getLimit()!=null){
      list = baseDao.selectMapsBySQL(sql + " order by t.eid,t.iid,t.stepcode", null, page.getOffset(), page.getLimit());
    }

    count = baseDao.getCountBySQL("select count(1) from ("+sql+")", null);

    page.setRows(list);
    page.setTotal(Integer.valueOf(""+count));
    return page;
  }
/**
 * 绩效科报表查看
 * @param operator
 * @param page
 * @param search_year
 * @param nodenum
 * @return
 */
  @Override
  public Pages getBasePerformanceManageAllStep(OperatorDTO operator, Pages page, String search_year, String nodenum, String enterguid, String batch) {
    boolean flag = false;
    List list = null;
    Long count = 0l;
    //查询用户是否对应全部单位
    String all_entesql = "select t1.enterpriseguid from jczl.operenterprise t1 " +
            "where t1.appguid = 3 and t1.year = " + search_year + " and t1.operatorguid = " + operator.getGuId();
    List all_entelist = baseDao.selectBySql(all_entesql);
    if(all_entelist.size()==1){
      String enterpriseguid = ""+all_entelist.get(0);
      if ("0".equals(enterpriseguid)){
        flag = true;
      }
    }

    String sql = "select e.name as ename, nvl2(n1.nodename,n1.nodename,'已结束') as nodename, t.*\n" +
            "  from jczl.enterprise e,  jxgl_gzkhpfb t\n" +
            "  left join pro_basemanage_step_log s\n" +
            "    on t.year = s.year\n" +
            "   and t.eid = s.eid\n" +
            "   and t.batch = s.batch\n" +
            "  left join report_model r\n" +
            "    on s.reportguid = r.guid\n" +
            "  left join base_process_nodes n1\n" +
            "    on t.stepcode = n1.nodenum\n" +
            "   and n1.processid = r.processid\n" +
            "\n" +
            " where t.eid = e.in_code\n" +
            "   and t.year = e.year\n" ;
//            "   and t.year = p.year " ;ysbz.payoutitem p,p.name as iname,


    if (!flag){//不是对应全部单位
      sql += "   and e.guid in (select t1.enterpriseguid\n" +
              "                    from jczl.operenterprise t1\n" +
              "                   where t1.appguid = 3\n" +
              "                     and t1.year = s.year\n" +
              "                     and t1.operatorguid = "+operator.getGuId() +")";
    }

    if (search_year != null && !"".equals(search_year)){
      sql += " and t.year = " + search_year;
    }
    if (nodenum != null && !"".equals(nodenum)){
      sql += " and t.stepcode = " + nodenum;
    }
    if (enterguid != null && !"".equals(enterguid)){
      sql += " and e.guid = " + enterguid;
    }
    if (batch != null && !"".equals(batch)){
      sql += " and t.batch = '" + batch + "'";
    }


    if(page.getOffset()!=null&&page.getLimit()!=null){
      list = baseDao.selectMapsBySQL(sql + " order by t.eid,t.stepcode", null, page.getOffset(), page.getLimit());
    }

    count = baseDao.getCountBySQL("select count(1) from ("+sql+")", null);

    page.setRows(list);
    page.setTotal(Integer.valueOf(""+count));
    return page;
  }

  /**
   * 提交
   * @param basedata
     * @param result
     */
  public void submitBaseInfo(String basedata, Result result) {
    JSONArray jsonArr = JSONArray.parseArray(basedata);
    Iterator<Object> it = jsonArr.iterator();
    while(it.hasNext()){
      JSONObject jobj = (JSONObject)it.next();
      //System.out.println("guid:"+obj.get("guid")+" inputcode:"+obj.get("inputcode"));
      String year ="" + jobj.get("YEAR");
      String eid  ="" + jobj.get("EID");
      String iid = "" + jobj.get("IID");
      String batch ="" + jobj.get("BATCH");
      //查询下一步环节
      String nextnum_sql = "select n.after_node_num, n.nodenum from base_process_nodes n,pro_base_step_log p,report_model m " +
              " where n.processid = m.processid " +
              " and p.reportguid = m.guid" +
              " and p.stepcode = n.nodenum" +
              " and p.year = " + year +
              " and p.eid = '" + eid + "' " +
              " and p.iid = '" + iid +"' " +
              " and p.batch = '" + batch +"' ";

      List nextnum_list = baseDao.selectBySql(nextnum_sql);
      if (nextnum_list.size()>0){
        Object[] obj = (Object[]) nextnum_list.get(0);
        int num = Integer.valueOf(""+obj[1]);
        int nextnum = Integer.valueOf(""+obj[0]);

        //插入下一步数据
        String insert_sql1 = "insert into pro_base " +
                "(select p.year,p.eid,p.iid,p.batch,p.cdate,p.edate,p.idate,p.firstmoney,p.adjustmoney,p.realmoney,p.expendingmoney,p.inputer,p.contact,p.status,p.detail,"+ nextnum + ",p.xmgk" + ",p.xmzxqk" + ",p.czwtyj" +
                " from pro_base p " +
                " where p.year = " + year + " and p.eid = '" + eid + "' and p.iid = '" + iid + "' and p.batch = '" + batch + "' and p.stepcode = "+ num +")";

        String insert_sql2 = "insert into pro_performance " +
                "(select p.year,p.eid,p.iid,p.batch,p.guid,p.line,p.performancetarget,p.targetname,p.targetqk,p.description,p.status,"+ nextnum +" from pro_performance p " +
                " where p.year = " + year + " and p.eid = '" + eid + "' and p.iid = '" + iid + "' and p.batch = '" + batch + "' and p.stepcode = "+ num +")";

        String insert_sql3 = "insert into pro_plement_plan " +
                "(select p.year,p.eid,p.iid,p.batch,p.guid,p.line,p.sonprogramname,p.planstep,p.progress,p.description,p.status,"+ nextnum +" from pro_plement_plan p " +
                " where p.year = " + year + " and p.eid = '" + eid + "' and p.iid = '" + iid + "' and p.batch = '" + batch + "' and p.stepcode = "+ num +")";

        String insert_sql4 = "insert into pro_expend_plan " +
                "(select p.year,p.eid,p.iid,p.batch,p.guid,p.line,p.sonprogramname,p.paymoney,p.paytime,p.non_paymoney,p.payplan,p.description,p.status ,"+ nextnum +" from pro_expend_plan p " +
                " where p.year = " + year + " and p.eid = '" + eid + "' and p.iid = '" + iid + "' and p.batch = '" + batch + "' and p.stepcode = "+ num +")";


        //修改环节记录表
        String update_sql = "update pro_base_step_log p set p.stepcode = " + nextnum +
                " where p.year = " + year + " and p.eid = '" + eid + "' and p.iid = '" + iid + "' and p.batch = '" + batch + "'";

        baseDao.executeBySql(insert_sql1);
        baseDao.executeBySql(insert_sql2);
        baseDao.executeBySql(insert_sql3);
        baseDao.executeBySql(insert_sql4);
        baseDao.executeBySql(update_sql);
      }
    }
    result.setSuccess(true);
    result.setContent("提交成功");

  }
/**
 * 提交
 * @param basedata
 * @param result
 */
  @Override
  public void submitBasePerformance(String basedata, Result result) {
    JSONArray jsonArr = JSONArray.parseArray(basedata);
    Iterator<Object> it = jsonArr.iterator();
    while(it.hasNext()){
      JSONObject jobj = (JSONObject)it.next();
      //System.out.println("guid:"+obj.get("guid")+" inputcode:"+obj.get("inputcode"));
      String year ="" + jobj.get("YEAR");
      String eid  ="" + jobj.get("EID");
      String iid = "" + jobj.get("IID");
      String batch ="" + jobj.get("BATCH");
      //查询下一步环节
      String nextnum_sql = "select n.after_node_num, n.nodenum from base_process_nodes n,pro_baseperformance_step_log p,report_model m " +
              " where n.processid = m.processid " +
              " and p.reportguid = m.guid" +
              " and p.stepcode = n.nodenum" +
              " and p.year = " + year +
              " and p.eid = '" + eid + "' " +
              " and p.iid = '" + iid +"' " +
              " and p.batch = '" + batch+"' ";

      List nextnum_list = baseDao.selectBySql(nextnum_sql);
      if (nextnum_list.size()>0){
        Object[] obj = (Object[]) nextnum_list.get(0);
        int num = Integer.valueOf(""+obj[1]);
        int nextnum = Integer.valueOf(""+obj[0]);

        //插入下一步数据
        String insert_sql1 = "insert into zcxmjxzpb " +
                "(select t.year,\n" +
                "       t.eid,\n" +
                "       t.iid,\n" +
                "       t.batch,\n" +
                "       t.zrbm,\n" +
                "       t.fzr,\n" +
                "       t.lxdh,\n" +
                "       t.snjz,\n" +
                "       t.ysap,\n" +
                "       t.ystz,\n" +
                "       t.czzf,\n" +
                "       t.ysjy,\n" +
                "       t.mbbzsp_js,\n" +
                "       t.mbbzsp_df,\n" +
                "       t.yszz_js,\n" +
                "       t.yszz_df,\n" +
                "       t.xmzzgl_js,\n" +
                "       t.xmzzgl_df,\n" +
                "       t.zjzchg_js,\n" +
                "       t.zjzchg_df,\n" +
                "       t.zjzcxf_js,\n" +
                "       t.zjzcxf_df,\n" +
                "       t.xmcc_js,\n" +
                "       t.xmcc_df,\n" +
                "       t.xmxy_js,\n" +
                "       t.xmxy_df,\n" +
                "       t.hj,\n" +
                "       t.pjjg,\n" +
                "       t.tbr,\n" +
                "       t.tbr_lxfs,\n" +
                "       t.tbrq,\n"+ nextnum + "," +
                "       t.xmgk,\n"+
                "       t.jcjzjsy,\n" +
                "       t.xmssqk,\n"+
                "       t.xmjxqk,\n"+
                "       t.qtsmwt"+
                " from ZCXMJXZPB t " +
                " where t.year = " + year + " and t.eid = '" + eid + "' and t.iid = '" + iid + "' and t.batch = '" + batch +"' and t.stepcode = "+ num +")";
/*
        String insert_sql2 = "insert into pro_performance " +
                "(select p.year,p.eid,p.iid,p.batch,p.line,p.childiid,p.firstgoal,p.target,p.detailmoney,p.totalmoney,p.remark,p.status,"+ nextnum +" from pro_performance p " +
                " where p.year = " + year + " and p.eid = '" + eid + "' and p.iid = '" + iid + "' and p.batch = " + batch +" and p.stepcode = "+ num +")";

        String insert_sql3 = "insert into pro_plement_plan " +
                "(select p.year,p.eid,p.iid,p.batch,p.line,p.cdate,p.edate,p.step,p.description,p.status,"+ nextnum +" from pro_plement_plan p " +
                " where p.year = " + year + " and p.eid = '" + eid + "' and p.iid = '" + iid + "' and p.batch = " + batch +" and p.stepcode = "+ num +")";

        String insert_sql4 = "insert into pro_expend_plan " +
                "(select p.year,p.eid,p.iid,p.batch,p.line,p.cdate,p.edate,p.paymoney,p.progress,p.description,p.opinion,p.status ,"+ nextnum +" from pro_expend_plan p " +
                " where p.year = " + year + " and p.eid = '" + eid + "' and p.iid = '" + iid + "' and p.batch = " + batch +" and p.stepcode = "+ num +")";*/


        //修改环节记录表
        String update_sql = "update pro_baseperformance_step_log p set p.stepcode = " + nextnum +
                " where p.year = " + year + " and p.eid = '" + eid + "' and p.iid = '" + iid + "' and p.batch = '" + batch + "'";

        baseDao.executeBySql(insert_sql1);
       /* baseDao.executeBySql(insert_sql2);
        baseDao.executeBySql(insert_sql3);
        baseDao.executeBySql(insert_sql4);*/
        baseDao.executeBySql(update_sql);
      }
    }
    result.setSuccess(true);
    result.setContent("提交成功");
  }

/**
 * 提交
 * @param basedata
 * @param result
 */
    @Override
    public void submitBaseManage(String basedata, Result result) {
        JSONArray jsonArr = JSONArray.parseArray(basedata);
        Iterator<Object> it = jsonArr.iterator();
        while(it.hasNext()){
            JSONObject jobj = (JSONObject)it.next();
            //System.out.println("guid:"+obj.get("guid")+" inputcode:"+obj.get("inputcode"));
            String year ="" + jobj.get("YEAR");
            String eid  ="" + jobj.get("EID");
            String batch ="" + jobj.get("BATCH");
            //查询下一步环节
            String nextnum_sql = "select n.after_node_num, n.nodenum from base_process_nodes n,pro_basemanage_step_log p,report_model m " +
                    " where n.processid = m.processid " +
                    " and p.reportguid = m.guid" +
                    " and p.stepcode = n.nodenum" +
                    " and p.year = " + year +
                    " and p.eid = '" + eid + "' " +
                    " and p.batch = '" + batch+ "' ";

            List nextnum_list = baseDao.selectBySql(nextnum_sql);
            if (nextnum_list.size()>0){
                Object[] obj = (Object[]) nextnum_list.get(0);
                int num = Integer.valueOf(""+obj[1]);
                int nextnum = Integer.valueOf(""+obj[0]);

                //插入下一步数据
                String insert_sql1 = "insert into jxgl_gzkhpfb " +
                        "(select j.year,\n" +
                        "       j.eid,\n" +
                        "       j.batch,\n" +
                        "       j.zsld_z,\n" +
                        "       j.zsld_p,\n" +
                        "       j.xtjz_z,\n" +
                        "       j.xtjz_p,\n" +
                        "       j.lslly_z,\n" +
                        "       j.lslly_p,\n" +
                        "       j.sbsj_z,\n" +
                        "       j.sbsj_p,\n" +
                        "       j.sbzl_z_1,\n" +
                        "       j.sbzl_p_1,\n" +
                        "       j.jxjkzdjs_z,\n" +
                        "       j.jxjkzdjs_p,\n" +
                        "       j.jxjksb_z,\n" +
                        "       j.jxjksb_p,\n" +
                        "       j.jxjksbzl_z,\n" +
                        "       j.jxjksbzl_p, \n" +
                        "       j.aszp_z_1,\n" +
                        "       j.aszp_p_1,\n" +
                        "       j.zpbgzl_z_1,\n" +
                        "       j.zpbgzl_p_1,\n" +
                        "       j.zddc_z,\n" +
                        "       j.zddc_p,\n" +
                        "       j.phczbmpj_z,\n" +
                        "       j.phczbmpj_p,\n" +
                        "       j.sjjx_z,\n" +
                        "       j.sjjx_p,\n" +
                        "       j.zgls_z,\n" +
                        "       j.zgls_p,\n" +
                        "       j.jgyy_z,\n" +
                        "       j.jgyy_p,\n" +
                        "       j.zlbs_z,\n" +
                        "       j.zlbs_p,\n" +
                        "       j.hlhjy_z,\n" +
                        "       j.hlhjy_p,\n" +
                        "       j.qt_z,\n" +
                        "       j.qt_p,\n" +
                        "       j.zf_z,\n" +
                        "       j.zf_p,\n" +
                        "       j.sbzl_z_2,\n" +
                        "       j.sbzl_p_2,\n" +
                        "       j.sbzl_z_3,\n" +
                        "       j.sbzl_p_3,\n" +
                        "       j.aszp_z_2,\n" +
                        "       j.aszp_p_2,\n" +
                        "       j.zpbgzl_z_2,\n" +
                        "       j.zpbgzl_p_2,\n" +
                        "       j.zpbgzl_z_3,\n" +
                        "       j.zpbgzl_p_3,\n" +
                        "       j.zpbgzl_z_4,\n" +
                        "       j.zpbgzl_p_4,\n" +
                        "       j.tbr,\n" +
                        "       j.tbr_lxfs,\n" +
                        "       j.datetime,"+ nextnum +
                        " from jxgl_gzkhpfb j " +
                        " where j.year = " + year + " and j.eid = '" + eid + "' and j.batch = '" + batch +"' and j.stepcode = "+ num +")";
/*
        String insert_sql2 = "insert into pro_performance " +
                "(select p.year,p.eid,p.iid,p.batch,p.line,p.childiid,p.firstgoal,p.target,p.detailmoney,p.totalmoney,p.remark,p.status,"+ nextnum +" from pro_performance p " +
                " where p.year = " + year + " and p.eid = '" + eid + "' and p.iid = '" + iid + "' and p.batch = " + batch +" and p.stepcode = "+ num +")";

        String insert_sql3 = "insert into pro_plement_plan " +
                "(select p.year,p.eid,p.iid,p.batch,p.line,p.cdate,p.edate,p.step,p.description,p.status,"+ nextnum +" from pro_plement_plan p " +
                " where p.year = " + year + " and p.eid = '" + eid + "' and p.iid = '" + iid + "' and p.batch = " + batch +" and p.stepcode = "+ num +")";

        String insert_sql4 = "insert into pro_expend_plan " +
                "(select p.year,p.eid,p.iid,p.batch,p.line,p.cdate,p.edate,p.paymoney,p.progress,p.description,p.opinion,p.status ,"+ nextnum +" from pro_expend_plan p " +
                " where p.year = " + year + " and p.eid = '" + eid + "' and p.iid = '" + iid + "' and p.batch = " + batch +" and p.stepcode = "+ num +")";*/


                //修改环节记录表
                String update_sql = "update pro_basemanage_step_log p set p.stepcode = " + nextnum +
                        " where p.year = " + year + " and p.eid = '" + eid + "' and p.batch = '" + batch + "'";

                baseDao.executeBySql(insert_sql1);
       /* baseDao.executeBySql(insert_sql2);
        baseDao.executeBySql(insert_sql3);
        baseDao.executeBySql(insert_sql4);*/
                baseDao.executeBySql(update_sql);
            }
        }
        result.setSuccess(true);
        result.setContent("提交成功");
    }

    /**
   * 退回
   * @param baseinfodata
     * @param result
     */
  public void backBaseInfo(String baseinfodata, Result result) {
    JSONArray jsonArr = JSONArray.parseArray(baseinfodata);
    Iterator<Object> it = jsonArr.iterator();
    while(it.hasNext()){
      JSONObject obj = (JSONObject)it.next();
      String year = ""+obj.get("YEAR");
      String eid = ""+obj.get("EID");
      String iid = ""+obj.get("IID");
      String batch = ""+obj.get("BATCH");
      String stepcode = ""+obj.get("STEPCODE");
      //System.out.println("guid:"+obj.get("guid")+" inputcode:"+obj.get("inputcode"));
      //查询上一环节号
      String beforenum_sql = "select n.nodenum from base_process_nodes n,pro_base_step_log p,report_model m " +
              " where n.processid = m.processid " +
              " and p.reportguid = m.guid" +
              " and p.year = " + year +
              " and p.eid = '" + eid + "' " +
              " and p.iid = '" + iid +"' " +
              " and p.batch = '" + batch + "' " +
              " and n.after_node_num = " + stepcode;

      List beforenum_list = baseDao.selectBySql(beforenum_sql);
      if (beforenum_list.size()>0){
        int beforenum = Integer.valueOf(""+beforenum_list.get(0));

        //删除当前环节数据
        String delete_sql1 = "delete from pro_base p where p.year = " + year + " and p.eid = '" + eid +
                "' and p.iid = '" + iid + "' and p.batch = '" + batch + "' and p.stepcode = " + stepcode;
        String delete_sql2 = "delete from pro_performance p where p.year = " + year + " and p.eid = '" + eid +
                "' and p.iid = '" + iid + "' and p.batch = '" + batch + "' and p.stepcode = " + stepcode;
        String delete_sql3 = "delete from pro_plement_plan p where p.year = " + year + " and p.eid = '" + eid +
                "' and p.iid = '" + iid + "' and p.batch = '" + batch + "' and p.stepcode = " + stepcode;
        String delete_sql4 = "delete from pro_expend_plan p where p.year = " + year + " and p.eid = '" + eid +
                "' and p.iid = '" + iid + "' and p.batch = '" + batch + "' and p.stepcode = " + stepcode;


        //修改环节记录表
        String update_sql = "update pro_base_step_log p set p.stepcode = " + beforenum +
                " where p.year = " + year + " and p.eid = '" + eid + "' and p.iid = '" + iid + "' and p.batch = '" + batch +"'";

        baseDao.executeBySql(delete_sql1);
        baseDao.executeBySql(delete_sql2);
        baseDao.executeBySql(delete_sql3);
        baseDao.executeBySql(delete_sql4);
        baseDao.executeBySql(update_sql);
      }
      result.setSuccess(true);
      result.setContent("退回成功");
    }
  }

/**
 * 退回
 * @param baseinfodata
 * @param result
 */
  @Override
  public void backBasePerformance(String baseinfodata, Result result) {
    JSONArray jsonArr = JSONArray.parseArray(baseinfodata);
    Iterator<Object> it = jsonArr.iterator();
    while(it.hasNext()){
      JSONObject obj = (JSONObject)it.next();
      String year = ""+obj.get("YEAR");
      String eid = ""+obj.get("EID");
      String iid = ""+obj.get("IID");
      String batch = ""+obj.get("BATCH");
      String stepcode = ""+obj.get("STEPCODE");
      //System.out.println("guid:"+obj.get("guid")+" inputcode:"+obj.get("inputcode"));
      //查询上一环节号
      String beforenum_sql = "select n.nodenum from base_process_nodes n,pro_baseperformance_step_log p,report_model m " +
              " where n.processid = m.processid " +
              " and p.reportguid = m.guid" +
              " and p.year = " + year +
              " and p.eid = '" + eid + "' " +
              " and p.iid = '" + iid +"' " +
              " and p.batch = '" + batch +"' " +
              " and n.after_node_num = " + stepcode;

      List beforenum_list = baseDao.selectBySql(beforenum_sql);
      if (beforenum_list.size()>0){
        int beforenum = Integer.valueOf(""+beforenum_list.get(0));

        //删除当前环节数据
        String delete_sql1 = "delete from ZCXMJXZPB p where p.year = " + year + " and p.eid = '" + eid +
                "' and p.iid = '" + iid + "' and p.batch = '" + batch + "' and p.stepcode = " + stepcode;
       /* String delete_sql2 = "delete from pro_performance p where p.year = " + year + " and p.eid = '" + eid +
                "' and p.iid = '" + iid + "' and p.batch = " + batch + " and p.stepcode = " + stepcode;
        String delete_sql3 = "delete from pro_plement_plan p where p.year = " + year + " and p.eid = '" + eid +
                "' and p.iid = '" + iid + "' and p.batch = " + batch + " and p.stepcode = " + stepcode;
        String delete_sql4 = "delete from pro_expend_plan p where p.year = " + year + " and p.eid = '" + eid +
                "' and p.iid = '" + iid + "' and p.batch = " + batch + " and p.stepcode = " + stepcode;*/


        //修改环节记录表
        String update_sql = "update pro_baseperformance_step_log p set p.stepcode = " + beforenum +
                " where p.year = " + year + " and p.eid = '" + eid + "' and p.iid = '" + iid + "' and p.batch = '" + batch + "'";

        baseDao.executeBySql(delete_sql1);
       /* baseDao.executeBySql(delete_sql2);
        baseDao.executeBySql(delete_sql3);
        baseDao.executeBySql(delete_sql4);*/
        baseDao.executeBySql(update_sql);
      }
      result.setSuccess(true);
      result.setContent("退回成功");
    }
  }

/**
 * 退回
 * @param baseinfodata
 * @param result
 */
    @Override
    public void backBaseManage(String baseinfodata, Result result) {
        JSONArray jsonArr = JSONArray.parseArray(baseinfodata);
        Iterator<Object> it = jsonArr.iterator();
        while(it.hasNext()){
            JSONObject obj = (JSONObject)it.next();
            String year = ""+obj.get("YEAR");
            String eid = ""+obj.get("EID");
            String batch = ""+obj.get("BATCH");
            String stepcode = ""+obj.get("STEPCODE");
            //System.out.println("guid:"+obj.get("guid")+" inputcode:"+obj.get("inputcode"));
            //查询上一环节号
            String beforenum_sql = "select n.nodenum from base_process_nodes n,pro_basemanage_step_log p,report_model m " +
                    " where n.processid = m.processid " +
                    " and p.reportguid = m.guid" +
                    " and p.year = " + year +
                    " and p.eid = '" + eid + "' " +
                    " and p.batch = '" + batch + "' "+
                    " and n.after_node_num = " + stepcode;

            List beforenum_list = baseDao.selectBySql(beforenum_sql);
            if (beforenum_list.size()>0){
                int beforenum = Integer.valueOf(""+beforenum_list.get(0));

                //删除当前环节数据
                String delete_sql1 = "delete from jxgl_gzkhpfb j where j.year = " + year + " and j.eid = '" + eid +
                        "' and j.batch = '" + batch + "' and j.stepcode = " + stepcode;
       /* String delete_sql2 = "delete from pro_performance p where p.year = " + year + " and p.eid = '" + eid +
                "' and p.iid = '" + iid + "' and p.batch = " + batch + " and p.stepcode = " + stepcode;
        String delete_sql3 = "delete from pro_plement_plan p where p.year = " + year + " and p.eid = '" + eid +
                "' and p.iid = '" + iid + "' and p.batch = " + batch + " and p.stepcode = " + stepcode;
        String delete_sql4 = "delete from pro_expend_plan p where p.year = " + year + " and p.eid = '" + eid +
                "' and p.iid = '" + iid + "' and p.batch = " + batch + " and p.stepcode = " + stepcode;*/


                //修改环节记录表
                String update_sql = "update pro_basemanage_step_log p set p.stepcode = " + beforenum +
                        " where p.year = " + year + " and p.eid = '" + eid + "' and p.batch = '" + batch + "'";

                baseDao.executeBySql(delete_sql1);
       /* baseDao.executeBySql(delete_sql2);
        baseDao.executeBySql(delete_sql3);
        baseDao.executeBySql(delete_sql4);*/
                baseDao.executeBySql(update_sql);
            }
            result.setSuccess(true);
            result.setContent("退回成功");
        }
    }

    /**
   * 查询报表模型
   * @param page
   * @return
     */
  public Pages getReportModel(Pages page) {
    String sql = "select p.processname,m.* from report_model m,base_process_info p where m.processid = p.processid ";
    List list = baseDao.selectMapsBySQL(sql, null, page.getOffset().intValue(), page.getLimit().intValue());
    Long count = baseDao.getCountBySQL("select count(1) from ("+sql+")", null);
    page.setRows(list);
    page.setTotal(Integer.valueOf(""+count));
    return page;
  }

  /**
   * 保存报表模型
   * @param GUID
   * @param REPORTCODE
   * @param REPORTNAME
   * @param REPORTID
   * @param ISEDIT
   * @param PROCESSID
     * @param result
     */
  public void saveReportModel(String GUID, String REPORTCODE, String REPORTNAME, String REPORTID, String ISEDIT, String PROCESSID, Result result) {
    if(!StringUtil.isblank(GUID)){
      ReportModel model = new ReportModel();
      model.setGuid(Long.valueOf(GUID));
      model.setReportcode(REPORTCODE);
      model.setReportname(REPORTNAME);
      model.setReportid(REPORTID);
      model.setIsedit(ISEDIT);
      model.setProcessid(PROCESSID);
      baseDao.update(model);
    }else{
      ReportModel model = new ReportModel();
      model.setReportcode(REPORTCODE);
      model.setReportname(REPORTNAME);
      model.setReportid(REPORTID);
      model.setIsedit(ISEDIT);
      model.setProcessid(PROCESSID);
      baseDao.save(model);
    }
    result.setSuccess(true);
    result.setContent("保存成功");

  }
  /**
   * 保存报表模型
   * @param ISOPEN
   * @param DEADLINEDATE
   * @param reportid
   * @param result
   */
  @Override
  public void saveReleaseLog(String ISOPEN, String DEADLINEDATE,String reportid, Result result) {
      //Long mill = DEADLINEDATE.getTime();
      String sql = "update PRO_RELEASE_LOG p set p.deadlinedate = to_date('" + DEADLINEDATE+"','yyyy-mm-dd hh24:mi:ss')," + "p.isopen = '" + ISOPEN + "'where p.reportmodelid = " + reportid;
      baseDao.executeBySql(sql);
  }

  /**
   * 删除报表模型
   * @param guid
   * @param result
     */
  public void deleteReportModel(String guid, Result result) {
    //查询模型是否使用
    String sql = "select * from pro_release_log t where t.reportmodelid = " + guid;
    List list = baseDao.selectBySql(sql);
    if(list.size()>0){
      result.setSuccess(false);
      result.setContent("该报表模型正在使用，不允许删除");
    }else{
      String delete_sql = "delete from report_model where guid = " + guid;
      baseDao.executeBySql(delete_sql);
      result.setSuccess(true);
      result.setContent("删除成功");
    }

  }

    /**
     * 删除绩效发布记录以及其他记录
     * @param year
     * @param batch
     * @param reportid
     * @param result
     */
    @Override
    public void deleteReleaseLog(String year, String batch, String reportid, Result result) {
        String reportId = "";
        String reportid_sql = "select t.reportid from report_model t where t.guid = " + reportid;
        List reportid_list = baseDao.selectBySql(reportid_sql);
        if (reportid_list.size() > 0) {
            reportId = (String) reportid_list.get(0);
        }
        if (ConfigureParser.getPropert("JXGZID").equals(reportId)){
            String insert_sql = "insert into hispro_base\n" +
                    "  select *\n" +
                    "    from pro_base p\n" +
                    "   where p.year =\n" + year +
                    "     and p.batch ='" + batch + "'";
            String insert_sql1 = "insert into hispro_expend_plan\n" +
                    "  select *\n" +
                    "    from pro_expend_plan p\n" +
                    "   where p.year =\n" + year +
                    "     and p.batch ='" + batch + "'";
            String insert_sql2 = "insert into hispro_plement_plan\n" +
                    "  select *\n" +
                    "    from pro_plement_plan p\n" +
                    "   where p.year =\n" + year +
                    "     and p.batch ='" + batch + "'";
            String insert_sql3 ="insert into hispro_performance\n" +
                    "  select *\n" +
                    "    from pro_performance p\n" +
                    "   where p.year =\n" + year +
                    "     and p.batch ='" + batch + "'";
            baseDao.executeBySql(insert_sql);
            baseDao.executeBySql(insert_sql1);
            baseDao.executeBySql(insert_sql2);
            baseDao.executeBySql(insert_sql3);
            String delete_sql ="delete from pro_base p\n" +
                    " where p.year =\n" + year +
                    "   and p.batch ='" + batch + "'";
            String delete_sql1 ="delete from pro_expend_plan p\n" +
                    " where p.year =\n" + year +
                    "   and p.batch ='" + batch + "'";
            String delete_sql2 ="delete from pro_plement_plan p\n" +
                    " where p.year =\n" + year +
                    "   and p.batch ='" + batch + "'";
            String delete_sql3 ="delete from pro_performance p\n" +
                    " where p.year =\n" + year +
                    "   and p.batch ='" + batch + "'";
            String delete_sql4="delete from pro_base_step_log p\n" +
                    " where p.year =\n" + year +
                    "   and p.batch ='" + batch + "'";
            baseDao.executeBySql(delete_sql);
            baseDao.executeBySql(delete_sql1);
            baseDao.executeBySql(delete_sql2);
            baseDao.executeBySql(delete_sql3);
            baseDao.executeBySql(delete_sql4);
        }else if(ConfigureParser.getPropert("JXZPID").equals(reportId)){
            String insert_sql1 = "insert into hiszcxmjxzpb\n" +
                    "  select *\n" +
                    "    from zcxmjxzpb p\n" +
                    "   where p.year =\n" + year +
                    "     and p.batch ='" + batch + "'";
            baseDao.executeBySql(insert_sql1);
            String delete_sql ="delete from ZCXMJXZPB p\n" +
                    " where p.year =\n" + year +
                    "   and p.batch ='" + batch + "'";
            String delete_sql1="delete from pro_baseperformance_step_log p\n" +
                    " where p.year =\n" + year +
                    "   and p.batch ='" + batch + "'";
            baseDao.executeBySql(delete_sql);
            baseDao.executeBySql(delete_sql1);
        }else if(ConfigureParser.getPropert("JXKHID").equals(reportId)){
            String insert_sql ="insert into hisjxgl_gzkhpfb\n" +
                    "  select *\n" +
                    "    from jxgl_gzkhpfb p\n" +
                    "   where p.year =\n" + year +
                    "     and p.batch ='" + batch + "'";
            baseDao.executeBySql(insert_sql);
            String delete_sql ="delete from jxgl_gzkhpfb p\n" +
                    " where p.year =\n" + year +
                    "   and p.batch ='" + batch + "'";
            String delete_sql1="delete from pro_basemanage_step_log p\n" +
                    " where p.year =\n" + year +
                    "   and p.batch ='" + batch + "'";
            baseDao.executeBySql(delete_sql);
            baseDao.executeBySql(delete_sql1);
        }else {
            result.setSuccess(false);
            result.setContent("未配置报表id");
        }
        String insert_sql ="insert into hispro_release_log\n" +
                "  select *\n" +
                "    from pro_release_log p\n" +
                "   where p.reportmodelid =\n" + reportid +
                "   and p.batch = '" + batch +"'";
        baseDao.executeBySql(insert_sql);
        String delete_sql1 ="delete from pro_release_log p\n" +
                "   where p.reportmodelid =\n" + reportid +
                "   and p.batch = '" + batch +"'";
        baseDao.executeBySql(delete_sql1);
        result.setSuccess(true);
        result.setContent("删除成功");
    }

    /**
   * 获取流程树
   * @return
     */
  public List getProcessInfos(){
    String sql = "select t.processid ,t.processname from base_process_info t";
    List list = baseDao.selectBySql(sql);
    List addlList = new ArrayList();

    for (int i = 0; i < list.size(); i++) {
      Object[] obj = (Object[])list.get(i);
      Map map = new HashMap();
      map.put("id", obj[0]);
      map.put("name", obj[1]);
      map.put("pId", 0);
      addlList.add(map);
    }
    return addlList;
  }

  /**                 
   * 获取报表模型树
   * @return
     */
  public List getReportModelTree(){
    String sql = "select t.guid,t.reportname from report_model t";
    List list = baseDao.selectBySql(sql);
    List addlList = new ArrayList();

    for (int i = 0; i < list.size(); i++) {
      Object[] obj = (Object[])list.get(i);
      Map map = new HashMap();
      map.put("id", obj[0]);
      map.put("name", obj[1]);
      map.put("pId", 0);
      addlList.add(map);
    }
    return addlList;
  }

  /**
   * 获取跟踪表环节树
   * @return
   */
  public List getStepcodeTree(OperatorDTO operator){
    String sql = null;
    List addlList = new ArrayList();
    if(operator!=null){
      sql ="select n.nodenum,n.nodename \n" +
              "            from pro_base_step_log s, report_model r, base_process_nodes n ,jczl.operpost o\n" +
              "             where s.reportguid = r.guid and r.processid = n.processid and n.roleid = o.postguid\n" +
              "             and o.operatorguid = "+operator.getGuId()+"\n" +
              "             group by n.nodenum,n.nodename order by n.nodenum";
    }else{
      sql = "select n.nodenum,n.nodename " +
              "from pro_base_step_log s, report_model r, base_process_nodes n " +
              " where s.reportguid = r.guid and r.processid = n.processid " +
              " group by n.nodenum,n.nodename order by n.nodenum";
      Map endmap = new HashMap();
      endmap.put("id", 0);
      endmap.put("name", "已结束");
      endmap.put("pId", null);
      addlList.add(endmap);
    }
    List list = baseDao.selectBySql(sql);
    if (list.size()>0){
      for (int i = 0; i < list.size(); i++) {
        Object[] obj = (Object[])list.get(i);
        Map map = new HashMap();
        map.put("id", obj[0]);
        map.put("name", obj[1]);
        map.put("pId", null);
        addlList.add(map);
      }
    }


    return addlList;
  }
  /**
   * 获取考核表环节树
   * @return
   */
  @Override
  public List getManagePerformanceStepcodeTree(OperatorDTO operator) {
    String sql = null;
    List addlList = new ArrayList();
    if(operator!=null){
      sql ="select n.nodenum,n.nodename \n" +
              "            from pro_basemanage_step_log s, report_model r, base_process_nodes n ,jczl.operpost o\n" +
              "             where s.reportguid = r.guid and r.processid = n.processid and n.roleid = o.postguid\n" +
              "             and o.operatorguid = "+operator.getGuId()+"\n" +
              "             group by n.nodenum,n.nodename order by n.nodenum";
    }else{
      sql = "select n.nodenum,n.nodename " +
              "from pro_basemanage_step_log s, report_model r, base_process_nodes n " +
              " where s.reportguid = r.guid and r.processid = n.processid " +
              " group by n.nodenum,n.nodename order by n.nodenum";
      Map endmap = new HashMap();
      endmap.put("id", 0);
      endmap.put("name", "已结束");
      endmap.put("pId", null);
      addlList.add(endmap);
    }
    List list = baseDao.selectBySql(sql);
    if (list.size()>0){
      for (int i = 0; i < list.size(); i++) {
        Object[] obj = (Object[])list.get(i);
        Map map = new HashMap();
        map.put("id", obj[0]);
        map.put("name", obj[1]);
        map.put("pId", null);
        addlList.add(map);
      }
    }


    return addlList;
  }
  /**
   * 获取自评表环节树
   * @return
   */
  @Override
  public List getPerformanceStepcodeTree(OperatorDTO operator) {
    String sql = null;
    List addlList = new ArrayList();
    if(operator!=null){
      sql ="select n.nodenum,n.nodename \n" +
              "            from pro_baseperformance_step_log s, report_model r, base_process_nodes n ,jczl.operpost o\n" +
              "             where s.reportguid = r.guid and r.processid = n.processid and n.roleid = o.postguid\n" +
              "             and o.operatorguid = "+operator.getGuId()+"\n" +
              "             group by n.nodenum,n.nodename order by n.nodenum";
    }else{
      sql = "select n.nodenum,n.nodename " +
              "from pro_baseperformance_step_log s, report_model r, base_process_nodes n " +
              " where s.reportguid = r.guid and r.processid = n.processid " +
              " group by n.nodenum,n.nodename order by n.nodenum";
      Map endmap = new HashMap();
      endmap.put("id", 0);
      endmap.put("name", "已结束");
      endmap.put("pId", null);
      addlList.add(endmap);
    }
    List list = baseDao.selectBySql(sql);
    if (list.size()>0){
      for (int i = 0; i < list.size(); i++) {
        Object[] obj = (Object[])list.get(i);
        Map map = new HashMap();
        map.put("id", obj[0]);
        map.put("name", obj[1]);
        map.put("pId", null);
        addlList.add(map);
      }
    }


    return addlList;
  }

  public List getJczlEnteTree(OperatorDTO operator, String year) {
    boolean flag = false;
    String sql = "";
    //查询用户是否对应全部单位
    String all_entesql = "select t1.enterpriseguid from jczl.operenterprise t1 " +
            "where t1.appguid = 3 and t1.year = " + year + " and t1.operatorguid = " + operator.getGuId();
    List all_entelist = baseDao.selectBySql(all_entesql);
    if(all_entelist.size()==1){
      String enterpriseguid = ""+all_entelist.get(0);
      if ("0".equals(enterpriseguid)){
        flag = true;
      }
    }

    if(flag){
      sql = "select '[' || e.isbn_code || ']' || e.name as name, e.guid, e.parent_guid\n" +
              "  from jczl.enterprise e\n" +
              " where e.year = " + year +" order by e.isbn_code " ;
    }else{
      sql = "select '[' || e.isbn_code || ']' || e.name as name, e.guid, e.parent_guid\n" +
              "  from jczl.operenterprise t, jczl.enterprise e\n" +
              " where t.year = e.year\n" +
              " and t.enterpriseguid = e.guid\n" +
              "   and t.appguid = 3 " +
              "   and e.year = " + year +
              "   and t.operatorguid = "+operator.getGuId()+" order by e.isbn_code ";
    }

    List list = this.baseDao.selectBySql(sql);
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

  public List getAllJczlEnteTree(String year) {
    String sql = "select '[' || e.isbn_code || ']' || e.name as name, e.guid, e.parent_guid\n" +
              "  from jczl.enterprise e\n" +
              " where e.year = " + year +" order by e.isbn_code " ;
    List list = this.baseDao.selectBySql(sql);
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

  public void saveEnterPhone(String guid, String enterguid, String entername, String phoneno, Result result) {
    String sql = "";
    if(StringUtil.isblank(guid)){
      sql = "insert into base_enterphone (guid,enterguid,entername,phoneno) " +
              "values " +
              "(base_enterphone_sequence.nextval,"+enterguid+",'"+entername+"','"+phoneno+"')";

    }else{
      sql = "update base_enterphone t set t.phoneno = "+ phoneno +" where t.guid = "+ guid +" and t.enterguid = " +enterguid;

    }
    baseDao.executeBySql(sql);
    result.setSuccess(true);
    result.setContent("保存成功");

  }

  public Map findEnterPhone(String enterguid) {
    Map map = new HashMap();
    String sql = "select t.guid, t.enterguid, t.entername, t.phoneno from base_enterphone t where t.enterguid = " +enterguid;
    List list = baseDao.selectBySql(sql);
    if(list.size()>0){
      Object[] obj = (Object[]) list.get(0);
      map.put("guid", ""+obj[0]);
      map.put("enterguid", ""+obj[1]);
      map.put("entername", ""+obj[2]);
      map.put("phoneno", ""+obj[3]);
    }
    return map;

  }

    @Override
  public List getDateTree(String reportid) {
  List dataList = new ArrayList();
  String sql = "select p.batch from PRO_RELEASE_LOG p ,REPORT_MODEL t where p.Reportmodelid = t.guid and t.reportid ='" + reportid+"'";
  List list =  baseDao.selectBySql(sql);//得到List集合
    if(list.size()>0){
      for(int i = 0; i < list.size() ; i++ ){
        Object obj = list.get(i);
        Map map = new HashMap();
        map.put("id",obj);
        map.put("name",obj);
        map.put("pId",null);
        dataList.add(map);
      }
    }
  return dataList;
  }

  @Override
  public void sendMassage(Result result,String basedata) {
    String returnmsg = "";
      int j = 0;
    //1.短信模版 select * from BASE_MODULE t
      //这个短信到时候会有一个模板，
    String sql = "select t.CONTENT from BASE_MODULE t";
    List list = this.baseDao.selectBySql(sql);
    String mas = (String) list.get(0);

      //批量获取单位以及号码
      JSONArray jsonArr = JSONArray.parseArray(basedata);
      Iterator<Object> it = jsonArr.iterator();
      while(it.hasNext()) {
        j++;
        JSONObject object = (JSONObject) it.next();
        //System.out.println("guid:"+obj.get("guid")+" inputcode:"+obj.get("inputcode"));
//          String year = "" + object.get("YEAR");//年
        String ename = "" + object.get("ENAME");//单位名称
//          String batch = "" + object.get("BATCH");//批次
//          String nodename = "" + object.get("NODENAME");//当前环节
        String phoneno = "" + object.get("PHONENO");//电话

        String message = mas;
        message = message.replace("${单位名称}", "" + ename);
        returnmsg += "<p>" + "单位:" + ename + "号码:" + phoneno + "短信:" + message + "</p>";
        SmsService.sendMessage("绩效管理系统", phoneno, message, "0", "", "037", "123456");

      }
    result.setContent("发送成功"+j +"条短信"+returnmsg);
    result.setSuccess(true);
          /*//2.查询单位以及手机号码
          //单位以及号码多个
          String sql2 = "select '['||e.isbn_code||']'||e.name as name, p.phoneno\n" +  //e.in_code（EID）
                  "  from PRO_BASE_STEP_LOG t, jczl.enterprise e , BASE_ENTERPHONE p\n" +
                  " where 1=1 \n" +
                  "   and t.stepcode = 1\n" +
                  "   and e.in_code = t.eid\n" +
                  "   and p.enterguid = e.guid\n" +
                  //新加的
                //  "and t.year = " + year +
                  //"and t.batch =" + batch +
                  //"and t.nodename =" + nodename +
                 // "and p.ename =" + ename ;
                  "and p.phoneno =" + phoneno ;
                 // " group by '['||e.isbn_code||']'||e.name, p.phoneno";
          List list1 = baseDao.selectBySql(sql2);
          //if (list1.size()>0) {
              Object[] obj = (Object[]) list1.get(0);
              //int pho = Integer.valueOf("" + obj[1]);
              String pho = (String) obj[1];
              //int name = Integer.valueOf("" + obj[0]);
              String message = mas;
              message = message.replace("${单位名称}",""+obj[0]);
              returnmsg += "<p>"+"单位:"+ obj[0]+"号码:"+pho+"短信:"+message+"</p>";
              SmsService.sendMessage("绩效管理系统", pho, message, "0", "", "037", "123456");
         // }
      }*/

    /*//2.查询单位以及手机号码
      //单位以及号码多个
    String sql2 = "select '['||e.isbn_code||']'||e.name as name, p.phoneno\n" +  //e.in_code（EID）
            "  from PRO_BASE_STEP_LOG t, jczl.enterprise e , BASE_ENTERPHONE p\n" +
            " where 1=1 \n" +
            "   and t.stepcode = 1\n" +
            "   and e.in_code = t.eid\n" +
            "   and p.enterguid = e.guid\n" +
            //新加的
            "and t.year = " + year +
            "and t.batch =" + batch +
            "and t.nodename =" + nodename +
            "and p.ename =" + ename +
            "and p.phoneno =" + phoneno +
            " group by '['||e.isbn_code||']'||e.name, p.phoneno";
    List list1 = baseDao.selectBySql(sql2);
    if(list1.size()>0){
      for(int i = 0;i < list1.size();i++){
          j+=i;
        Object[] obj = (Object[]) list1.get(i);
        String pho = (String) obj[1];
        //String phones = pho;
          //System.out.println("替换前："+message);
          String message = mas;
          message = message.replace("${单位名称}",""+obj[0]);
          //System.out.println("替换后："+message);

        //3.送短信
        //System.out.println("单位"+ obj[0]+"号码:"+pho+"短信" + message);
        returnmsg += "<p>"+"单位:"+ obj[0]+"号码:"+pho+"短信:"+message+"</p>";
          SmsService.sendMessage("绩效管理系统", pho, message, "0", "", "037", "123456");
        *//*  try {
              SmsService.sendMessage("绩效管理系统", pho, message, "0", "", ConfigureParser.getPropert("APPGUID"), "pwd");
          } catch (Exception e) {
            //发送错误 加提示
              e.printStackTrace();

          }*//*
      }*/

    }
  /*public static void main(String[] args) {
    try {
      SmsService.sendMessage("绩效管理系统", "18259973869", "测试", "0", "", "037", "123456");
    } catch (Exception e) {
      e.printStackTrace();

    }
  }*/
}