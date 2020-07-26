package com.datanew.service.impl;


import com.datanew.dao.BaseDao;
import com.datanew.dto.Pages;
import com.datanew.dto.Result;
import com.datanew.model.BaseModule;
import com.datanew.model.BaseOperator;
import com.datanew.model.BbReport;
import com.datanew.model.BbReportToUser;
import com.datanew.service.ReportService;
import com.datanew.util.*;
import com.datanew.webservice.WebServiceClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.MessageDigest;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.*;


@Service("reportService")
public class ReportServiceImpl implements ReportService {

    @Resource
    BaseDao baseDao;

	public Map findReportInfo(String reportId) {
		List bplist = baseDao.selectByHql("from BbReport t where t.guid = '"+reportId+"'");
		Map map = new HashMap();
		map.put("info", bplist.get(0));
		return map;
	}

	public void saveReportInfo(BaseOperator operator, BbReport b) {
		if("".equals(b.getGuid())|| b.getGuid()==null){
			b.setCreateDate(new Date());
			b.setUserGuId(operator.getUserid());
			b.setTableName("ZUD_"+PYinUtil.getPYIndexStr(b.getName(), true));
			b.setUserName(operator.getUsername());
			b.setSequenceName("SEQ_COMMON");
			b.setEnable("0");
			b.setTbenable("0");
			b.setIsupload("1");
			b.setIsSelect("1");
			baseDao.saveOrUpdate(b);
		}else{
			BbReport bp = (BbReport) baseDao.load(BbReport.class, b.getGuid());
			bp.setName(b.getName());
			bp.setCollectName(b.getCollectName());
			bp.setFillName(b.getFillName());
			bp.setDesignName(b.getDesignName());
			baseDao.saveOrUpdate(bp);
		}
	}

	public boolean deleteRopert(String reportid) throws BusinessException {
		BbReport report= (BbReport) baseDao.load(BbReport.class, Long.valueOf(reportid));
		try {
		/*	String error = "";
			Result resultInfo = null;
			if (report!=null) {
				//删除设计模版
				if(!StringUtil.isblank(report.getDesignName())){
					resultInfo = StaticData.getFileProperty(ConfigureParser.getPropert("authId", ""), 
							ConfigureParser.getPropert("targetVolume", ""), 
							report.getDesignName());

					if (resultInfo != null) {
						if (resultInfo.getFileInfo() != null) {
							error = StaticData.deleteFile(ConfigureParser.getPropert("authId", ""), 
									ConfigureParser.getPropert("targetVolume", ""), 
									report.getDesignName());
						}else if (resultInfo.getError() != null && !resultInfo.getError().equals("")) {
							error = "删除设计模板出现异常:"+resultInfo.getError();
						}else if (resultInfo.getInfo() != null && !resultInfo.getInfo().equals("")) {
							
						}
					} else {
						throw new BusinessException("删除设计模板出现异常");
					}
				}
				
				if (StringUtil.isblank(error)&&!StringUtil.isblank(report.getFillName())) {
					//删除填报模版
					resultInfo = StaticData.getFileProperty(ConfigureParser.getPropert("authId", ""), 
							ConfigureParser.getPropert("targetVolume", ""), 
							report.getFillName());

					if (resultInfo != null) {
						if (resultInfo.getFileInfo() != null) {
							error = StaticData.deleteFile(ConfigureParser.getPropert("authId", ""), 
									ConfigureParser.getPropert("targetVolume", ""), 
									report.getFillName());
						}else if (resultInfo.getError() != null && !resultInfo.getError().equals("")) {
							error = "删除填报模板出现异常:"+resultInfo.getError();
						}else if (resultInfo.getInfo() != null && !resultInfo.getInfo().equals("")) {
						}
					} else {
						throw new BusinessException("删除设计模板出现异常");
					}
				}
			}

			if (!StringUtil.isblank(error)) {
				throw new BusinessException(error);
			} else {*/
				baseDao.delete(report);
				baseDao.executeBySql("delete from BB_REPORTTOUSER t where t.reportid = "+reportid);
				return true;
			//}
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
	public void updateRopert(String guid,String designName) {
		BbReport b= (BbReport) baseDao.load(BbReport.class, Long.valueOf(guid));
		b.setDesignName(designName);
		baseDao.save(b);
	}
	
	public String updateEnable(String guid) {
		BbReport b= (BbReport) baseDao.load(BbReport.class, Long.valueOf(guid));
		String str = "";
		if("0".equals(b.getEnable())){
			b.setEnable("1");
			str = "1";
		}else{
		    b.setEnable("0");
		    str = "0";
		}
		baseDao.save(b);
		return str;
	}
	
	public String updateTbEnable(String guid) {
		BbReport b= (BbReport) baseDao.load(BbReport.class, Long.valueOf(guid));
		String str = "";
		if("0".equals(b.getTbenable())){
			b.setTbenable("1");
			str = "1";
		}else{
		    b.setTbenable("0");
		    str = "0";
		}
		baseDao.save(b);
		return str;
	}
	
	public void updateIsupload(String guid) {
		BbReport b= (BbReport) baseDao.load(BbReport.class, Long.valueOf(guid));
		if("0".equals(b.getIsupload())){
			b.setIsupload("1");
		}else{
		    b.setIsupload("0");
		}
		baseDao.save(b);
	}
	
	public void updateIsSelectReport(String guid) {
		BbReport b= (BbReport) baseDao.load(BbReport.class, Long.valueOf(guid));
		if("0".equals(b.getIsSelect())){
			b.setIsSelect("1");
		}else{
		    b.setIsSelect("0");
		}
		baseDao.save(b);
	}
	
	public boolean hasFillData(String tableName) {
		List list = baseDao.selectBySql("select 1 from ALL_ALL_TABLES where table_name = '"+tableName+"'");
		if(list!=null&&!list.isEmpty()){
			List listdata = baseDao.selectBySql("select count(*) count from "+tableName);
			if(!"0".equals(listdata.get(0).toString())){
				return false;
			}else{
				return true;
			}
		}else{
			return true;
		}
	}

	public String saveReport(String guid,String mode,Result result) throws Exception {
		BbReport report = (BbReport)baseDao.load(BbReport.class, Long.valueOf(guid));
		result = StaticData.publishReport(ConfigureParser.getPropert("targetVolume", ""), ConfigureParser.getPropert("authId",""), report.getDesignName(),report.getTableName(),mode);
		if (StringUtil.isblank(result.getStrsuccess())&&result.getStrsuccess().equals("false")&&!StringUtil.isblank(result.getError())) {
			throw new BusinessException(result.getError());
		}else {
			if(!StringUtil.isblank(result.getReportFileName())){
				report.setFillName(result.getReportFileName());
				baseDao.save(report);
				return result.getReportFileName();
			}else{
				throw new BusinessException("发布失败，未返回内部名称!");
			}
		}
	}

	public boolean saveSendMessage(String reportid, String module){
		try {
			List list = baseDao.selectByHql("select t.userid,t.moblephonenum,b from BaseOperator t,BbReportToUser b where t.userid = b.userid and b.reportid="+reportid+" and b.issend='0'", null);
	        Object[] args = new Object[6];
	        args[0] =  0;
	        args[2] = "096";
	        args[3] = "zjlxkj";
	        args[5] = -1;
	        BaseModule m = (BaseModule)baseDao.loadByHql("from BaseModule t where t.guId="+Long.valueOf(module), null);
	        args[4] = m.getContent();
	        for(int i=0;i<list.size();i++){
	        	Object[] obj = (Object[]) list.get(i);
	        	if(obj[1]==null){
	        		continue;
	        	}
	        	args[1]=obj[1].toString();
	        	String result= WebServiceClient.getClientMessage("Send",args);
	        	if("1".equals(result)){
	        		BbReportToUser btu = (BbReportToUser)obj[2];
	        		btu.setIssend("1");
	        		baseDao.save(btu);
	        	}
			}
		} catch (Exception e) {
			 return false;
		}
        return true;
	}
	public void saveSendBack(String userid, String reportid,String module,String reportsj) throws Exception {
		String[] userids = userid.split(",");
		BaseModule m = (BaseModule)baseDao.loadByHql("from BaseModule t where t.guId="+Long.valueOf(module), null);
		for(int i=0;i<userids.length;i++){
			BbReportToUser bReportToUser = (BbReportToUser)baseDao.loadByHql("from BbReportToUser t where t.userid = "+userids[i]+"and t.reportid="+reportid+" and t.sj=to_date('"+reportsj+"','yyyy-MM-dd')", null);
			bReportToUser.setFillstatus("0");
			bReportToUser.setAuditstatus("0");
			baseDao.save(bReportToUser);
			List list = baseDao.selectByHql("select t.moblephonenum from BaseOperator t where t.userid="+userids[i], null);
	        Object[] args = new Object[6];
	        args[0] =  0;
	        args[2] = "096";
	        args[3] = "zjlxkj";
	        args[5] = -1;
	        args[4] = m.getContent();
	        String moblephonenum = list.get(0).toString();
	        if(!StringUtil.isblank(moblephonenum)){
	        	args[1]=moblephonenum;
	        	String result= WebServiceClient.getClientMessage("Send",args);
	        }
	    }
	}
	public void saveComplete(String userid, String reportid,String reportsj) throws Exception {
		    BbReport bReport = (BbReport)baseDao.load(BbReport.class, Long.valueOf(reportid));
			BbReportToUser bReportToUser = (BbReportToUser)baseDao.loadByHql("from BbReportToUser t where t.userid = "+userid+"and t.reportid="+reportid+" and t.sj=to_date('"+reportsj+"','yyyy-MM-dd')", null);
			Long count = baseDao.getCountBySQL("select count(1) from Bb_Attachment t where t.reportuserid="+bReportToUser.getGuid(), null);
            if("1".equals(bReport.getIsupload())&&count == 0){
            	  throw new BusinessException("该报表需要上传附件");
		    }
			bReportToUser.setFillstatus("1");
			baseDao.save(bReportToUser);
	}
	
	public void saveUserToReport(String userid, String guid,String isMax,String remark) throws Exception {
		//是否重新发布报表(0,更新最新报表，1发布新的任务)
		if("0".equals(isMax)){
			BbReportToUser btu;
			List dlist = baseDao.selectBySql("(select to_char(max(t.sj),'yyyy-MM-dd') from BB_REPORTTOUSER t where t.reportid = "+guid+")");
			if(dlist.isEmpty()||dlist.size()==0){
				throw new BusinessException("该报表未发布过请选择发布新的任务进行发布");
			}
			List list = baseDao.selectBySql("select t.userid from BB_REPORTTOUSER t where t.reportid = "+guid+" and t.sj = to_date('"+dlist.get(0).toString()+"','yyyy-MM-dd')");
			String[] userids = userid.split(",");
			TreeSet<String> tr = new TreeSet<String>();
			 for(int i=0;i<userids.length;i++){
				 if(!"".equals(userids[i])){
					 userids[i] = userids[i].split("_")[1];
					 tr.add(userids[i]);
				 }
			 }
			 for(String i : tr){
				 if(!list.contains(i)){
						btu = new BbReportToUser();
						btu.setReportid(guid);
						btu.setUserid(i);
						btu.setFillstatus("0");
						btu.setAuditstatus("0");
						btu.setIssend("0");
						btu.setSj(DateUtil.getCurrentDate(dlist.get(0).toString(), "yyyy-MM-dd"));
						btu.setRemark(remark);
						baseDao.save(btu);
					}
			}
		}else{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List list = baseDao.selectBySql("select t.userid from BB_REPORTTOUSER t where t.reportid = "+guid+" and t.sj = to_date('"+sdf.format(new Date())+"','yyyy-MM-dd')");
			if(list.size()>0){
				throw new BusinessException("该报表今天已经发布请勿重复发布");
			}
			BbReportToUser btu;
			String[] userids = userid.split(",");
			TreeSet<String> tr = new TreeSet<String>();
			for(int i=0;i<userids.length;i++){
				 if(!"".equals(userids[i])){
					 userids[i] = userids[i].split("_")[1];
					 tr.add(userids[i]);
				 }
			 }
			 for(String i : tr){
				btu = new BbReportToUser();
				btu.setReportid(guid);
				btu.setUserid(i);
				btu.setFillstatus("0");
				btu.setAuditstatus("0");
				btu.setIssend("0");
				btu.setSj(DateUtil.getCurrentDate("yyyy-MM-dd"));
				btu.setRemark(remark);
				baseDao.save(btu);
			}
		}
	}
	public String MD5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};       
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	public Pages getFillInfo(Pages page, String reportid,String divisionguid,String auditstatus,String entename,String userids,String sj) {
		if(!StringUtil.isblank(reportid)&&!StringUtil.isblank(sj)){
			String sql = "select t.userid,o.username,t.guid,t.fillstatus,t.auditstatus,t.reportid,r.name as reportname,d.name as divisionname,e.name as entename,to_char(t.sj,'yyyy-MM-dd') as sj,t.remark from bb_reporttouser t,base_operator o,bb_report r,base_division d,jczl.dw_enterprise e where o.userid = t.userid and t.reportid = r.guid and d.code(+) = o.divisionguid and o.entecode = e.fs_guid and t.reportid="+reportid+" and t.sj=to_date('"+sj+"','yyyy-MM-dd')";
			String countsql = "select count(1) from bb_reporttouser t,base_operator o,bb_report r,base_division d,jczl.dw_enterprise e where o.userid = t.userid and t.reportid = r.guid and d.code(+) = o.divisionguid and o.entecode = e.fs_guid and t.reportid="+reportid+" and t.sj=to_date('"+sj+"','yyyy-MM-dd')";
			if(StringUtil.isblank(userids)){
				if(!StringUtil.isblank(auditstatus)){
					sql += " and t.auditstatus="+auditstatus;
					countsql += " and t.auditstatus="+auditstatus;
				}
				if(!StringUtil.isblank(divisionguid)){
					sql += " and o.divisionguid in ("+divisionguid+")";
					countsql += " and o.divisionguid in ("+divisionguid+")";
				}
				if(!StringUtil.isblank(entename)){
					sql += " and e.name like '%"+entename+"%'";
					countsql += " and e.name like '%"+entename+"%'";
				}
			}else{
				sql += " and o.userid in ("+userids+")";
				countsql += " and o.userid in ("+userids+")";
			}
			List list= baseDao.selectBySql(sql,null,page.getOffset(),page.getLimit());
			List addlList = new ArrayList();
			Map map;
			for(int i=0;i<list.size();i++){
				Object[] obj = (Object[]) list.get(i);
				map =  new HashMap();
				map.put("USERID", obj[0]);
				map.put("USERNAME", obj[1]);
				Long count = baseDao.getCountBySQL("select count(1) from bb_attachment t where t.reportuserid = "+Long.valueOf(obj[2].toString()), null);
				map.put("ISFJ", count!=0?"已上传":"未上传");
				map.put("FILLSTATUS", "1".equals(obj[3].toString())?"已完成":"填制中");
				if("1".equals(obj[3].toString())){
					map.put("AUDITSTATUS", "1".equals(obj[4].toString())?"审核通过":"审核中");
				}else{
					map.put("AUDITSTATUS", "");
				}
				map.put("REPORTID", obj[5].toString());
				map.put("REPORTNAME", obj[6].toString());
				map.put("DIVISIONNAME", obj[7]);
				map.put("ENTENAME", obj[8].toString());
				map.put("SJ", obj[9]);
				map.put("REMARK", obj[10]);
				addlList.add(map);
			}
			Long listcount=baseDao.getCountBySQL(countsql,null);
			page.setRows(addlList);
			page.setTotal(listcount.intValue());
		}else{
			page.setRows(new ArrayList());
			page.setTotal(0);
		}
		return page;
	}
	
	public void saveViewPass(String userid, String reportid,String reportsj) throws Exception {
		String[] userids = userid.split(",");
		for(int i=0;i<userids.length;i++){
			BbReportToUser bp = (BbReportToUser)baseDao.loadByHql("from BbReportToUser t where t.userid = "+userids[i]+" and t.reportid="+reportid+" and t.sj=to_date('"+reportsj+"','yyyy-MM-dd')", null);
			bp.setAuditstatus("1");
			baseDao.save(bp);
		}
   }
	public Pages getFileInfo(Pages page, String reportid,String userid,String sj) {
		if(!StringUtil.isblank(reportid)){
			String sql = "select o.guid,o.filename,to_char(o.uploaddate,'yyyy-MM-dd hh:mm:ss') from bb_reporttouser t,bb_attachment o where t.guid = o.reportuserid and t.reportid="+reportid+" and t.userid="+userid+"  and t.sj=to_date('"+sj+"','yyyy-MM-dd')";
			String countsql = "select count(1) from bb_reporttouser t,bb_attachment o where t.guid = o.reportuserid and t.reportid="+reportid+" and t.userid="+userid+"  and t.sj=to_date('"+sj+"','yyyy-MM-dd')";
			List list= baseDao.selectBySql(sql,null,page.getOffset(),page.getLimit());
			List addlList = new ArrayList();
			Map map;
			for(int i=0;i<list.size();i++){
				Object[] obj = (Object[]) list.get(i);
				map =  new HashMap();
				map.put("guid", obj[0].toString());
				map.put("filename", obj[1].toString());
				map.put("uploaddate", obj[2].toString());
				map.put("guidFileName", obj[0].toString()+","+obj[1].toString());
				addlList.add(map);
			}
			Long listcount=baseDao.getCountBySQL(countsql,null);
			page.setRows(addlList);
			page.setTotal(listcount.intValue());
		}
		return page;
	}
	public boolean deleteRopertFile(String fileguid) throws BusinessException {
		try {
				baseDao.executeBySql("delete from BB_ATTACHMENT t where t.guid in ("+fileguid+")");
				return true;
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}

	public String getProgress(String reportId, String userid,String reportsj) {
		String Hql = "select t.fillstatus from bb_reporttouser t where  t.reportid="+reportId+" and t.userid="+userid+"  and t.sj=to_date('"+reportsj+"','yyyy-MM-dd')";
		List list = baseDao.selectBySql(Hql);
		return list.get(0).toString();
	}

	public String getTbEnable(String reportId, String userid) {
		String Hql = "select t.tbenable from bb_report t where  t.guid="+reportId;
		List list = baseDao.selectBySql(Hql);
		return list.get(0).toString();
	}
	
	public List findReportUserTree() {
		String hql = "select o.username as name, o.userid as id, t.guid as pId from Base_Operator o left join jczl.dw_enterprise  t on t.fs_guid = o.entecode inner join Base_Operatortopost p on p.userid=o.userid inner join Base_Posttomenu m on m.postid = p.postid where m.menuid=23";
        List uList = baseDao.selectBySql(hql);
        List u_lList = new ArrayList();
        Map umap;
        String enteids = "";
		for(int i=0;i<uList.size();i++){
			Object[] obj = (Object[]) uList.get(i);
			umap =  new HashMap();
			umap.put("name", obj[0]);
			umap.put("id", obj[1]);
			umap.put("ISLEAF", '1');
			u_lList.add(umap);
		}
        return u_lList;
	}

	public String getTbsj(String reportId, String userid) {
		List dlist = baseDao.selectBySql("select to_char(max(t.sj),'yyyy-MM-dd') from BB_REPORTTOUSER t where t.reportid = "+reportId+" and t.userid="+userid+"");
		return dlist.get(0).toString();
	}

	public Pages getExcelInfo(Pages page, String isrecord, String startdate,
			String enddate) {
		String sql = "select t.guid,t.businessno,t.isrecord,t.remark,t.businessdate from business_record t where 1=1";
		String countsql = "select count(1) from business_record t where 1=1";
		if(!StringUtil.isblank(isrecord)){
			sql += " and t.isrecord="+isrecord;
			countsql += " and t.isrecord="+isrecord;
		}
		if(!StringUtil.isblank(startdate)){
			sql += " and t.buisnessdate >= "+startdate;
			countsql += " and t.buisnessdate >= "+startdate;
		}
		if(!StringUtil.isblank(enddate)){
			sql += " and t.buisnessdate <= "+enddate;
			countsql += " and t.buisnessdate <= "+enddate;
		}
		List list= baseDao.selectBySql(sql,null,page.getOffset(),page.getLimit());
		List addlList = new ArrayList();
		Map map;
		for(int i=0;i<list.size();i++){
			Object[] obj = (Object[]) list.get(i);
			map =  new HashMap();
			map.put("guid", obj[0]);
			map.put("businessno", obj[1]);
			map.put("isrecord", obj[2]);
			map.put("remark", obj[3]);
			map.put("businessdate", obj[4]);
			addlList.add(map);
		}
		Long listcount=baseDao.getCountBySQL(countsql,null);
		page.setRows(addlList);
		page.setTotal(listcount.intValue());
		return page;
	}
	public Pages getExcelMxInfo(Pages page, String guid) {
		if(!StringUtil.isblank(guid)){
			String sql = "select t.pc,t.year,t.dwcode,t.dwname,t.billno,t.fcode,t.fname,t.ecode,t.ename,t.tzmc,t.totalmoney from zfls t where  1=1";
			String countsql = "select count(1) from zfls t where  1=1";
			List list= baseDao.selectBySql(sql,null,page.getOffset(),page.getLimit());
			List addlList = new ArrayList();
			Map map;
			for(int i=0;i<list.size();i++){
				Object[] obj = (Object[]) list.get(i);
				map =  new HashMap();
				map.put("pc", obj[0]);
				map.put("year", obj[1]);
				map.put("dwcode", obj[2]);
				map.put("dwname", obj[3]);
				map.put("billno", obj[4]);
				map.put("fcode", obj[5]);
				map.put("ecode", obj[6]);
				map.put("fname", obj[7]);
				map.put("ename", obj[8]);
				map.put("tzmc", obj[9]);
				map.put("totalmoney", obj[10]);
				addlList.add(map);
			}
			Long listcount=baseDao.getCountBySQL(countsql,null);
			page.setRows(addlList);
			page.setTotal(listcount.intValue());
		}
		return page;
	}

	public void setInfoRecord(String businessno) throws BusinessException {
		CallableStatement call = null;
		String errmsg="";
		try {
			call = baseDao.getConnection().prepareCall("{call zfls_record(?,?)}");
			call.setString(1, businessno);
			call.registerOutParameter(2, Types.VARCHAR);
			call.execute();
			errmsg = call.getString(2);
			if(!StringUtil.isblank(errmsg)){
				throw new BusinessException("操作失败");
			}else{
				baseDao.executeBySql("update business_record t set t.isrecord = '1' where t.businessno = '"+businessno+"'");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException("操作失败");
		}
	}
	 public void deleteExcelFile(String businessno)
	  {
         baseDao.executeBySql("delete from business_record t where t.businessno='"+businessno+"'");
         baseDao.executeBySql("delete from zfls t where t.pc='"+businessno+"'");
         baseDao.executeBySql("delete from zfls1 t where t.pc='"+businessno+"'");
	  }
}
