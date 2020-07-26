package com.datanew.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name = "bb_report")
public class BbReport {
	
	private Long guid; // 编码,对应序列seq_report
	
	private String name; //表名称
	
	private String tableName; // 表编号

	private String sequenceName; // 序列名称
	
	private Integer userGuId; // 创建者编码

	private String userName; // 创建者姓名

	private Date createDate; // 创建日期

	private String designName; // 设计模版名称

	private String fillName; // 填报模版名称
	
	private String enable; // 启用标记
	
	private String isupload;//是否需要上传附件
	
	private String collectName;//汇总报表名称
	
	private String isSelect;//是否选择填报人填报
	
	private String tbenable;//是否启用填报功能
	
	@Id
    @GeneratedValue( generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "bb_report_sequence")
	public Long getGuid() {
		return guid;
	}
	
	public void setGuid(Long guid) {
		this.guid = guid;
	}
	
	@Column(name = "NAME", length = 60)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "TABLENAME", length = 30)
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@Column(name = "SEQUENCENAME", length = 30)
	public String getSequenceName() {
		return sequenceName;
	}

	public void setSequenceName(String sequenceName) {
		this.sequenceName = sequenceName;
	}

	@Column(name = "USERGUID")
	public Integer getUserGuId() {
		return userGuId;
	}

	public void setUserGuId(Integer userGuId) {
		this.userGuId = userGuId;
	}

	@Column(name = "USERNAME", length = 30)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "CREATEDATE")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "DESIGNNAME", length = 100)
	public String getDesignName() {
		return designName;
	}

	public void setDesignName(String designName) {
		this.designName = designName;
	}

	@Column(name = "FILLNAME", length = 100)
	public String getFillName() {
		return fillName;
	}

	public void setFillName(String fillName) {
		this.fillName = fillName;
	}

	@Column(name = "ENABLE")
	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}
	@Column(name = "TBENABLE")
	public String getTbenable() {
		return tbenable;
	}

	public void setTbenable(String tbenable) {
		this.tbenable = tbenable;
	}

	@Column(name = "ISUPLOAD")
	public String getIsupload() {
		return isupload;
	}

	public void setIsupload(String isupload) {
		this.isupload = isupload;
	}
	@Column(name = "COLLECTNAME", length = 100)
	public String getCollectName() {
		return collectName;
	}

	public void setCollectName(String collectName) {
		this.collectName = collectName;
	}
	@Column(name = "ISSELECT")
	public String getIsSelect() {
		return isSelect;
	}

	public void setIsSelect(String isSelect) {
		this.isSelect = isSelect;
	}
	
	
}
