package com.datanew.model;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "BB_REPORTTOUSER")
public class BbReportToUser implements java.io.Serializable {

	// Fields

	private Integer guid;
	private String userid;
	private String reportid;
	private String fillstatus;
	private String auditstatus;
	private String issend;
	private Date sj;
	private String remark;

	@Id
    @GeneratedValue( generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "BB_REPORTTOUSER_SEQUENCE")
	public Integer getGuid() {
		return this.guid;
	}

	public void setGuid(Integer guid) {
		this.guid = guid;
	}

	@Column(name = "USERID", length = 20)
	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Column(name = "REPORTID", length = 20)
	public String getReportid() {
		return this.reportid;
	}

	public void setReportid(String reportid) {
		this.reportid = reportid;
	}

	@Column(name = "FILLSTATUS")
	public String getFillstatus() {
		return fillstatus;
	}

	public void setFillstatus(String fillstatus) {
		this.fillstatus = fillstatus;
	}
	@Column(name = "AUDITSTATUS")
	public String getAuditstatus() {
		return auditstatus;
	}

	public void setAuditstatus(String auditstatus) {
		this.auditstatus = auditstatus;
	}
	@Column(name = "ISSEND")
	public String getIssend() {
		return issend;
	}

	public void setIssend(String issend) {
		this.issend = issend;
	}
	@Column(name = "sj")
	public Date getSj() {
		return sj;
	}

	public void setSj(Date sj) {
		this.sj = sj;
	}
	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	

}