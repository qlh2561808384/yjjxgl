package com.datanew.model;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="REPORT_MODEL",schema="JXGL")
public  class  ReportModel {

    //是否允许审核环节修改（0否1是）
    private String isedit;

    //序号
    private Long guid;

    //报表名称
    private String reportname;

    //报表编码
    private String reportcode;

    //报表id（HAPPYSERVER）
    private String reportid;

    //流程id
    private String processid;

    public void setIsedit(String isedit){

        this.isedit=isedit;
    }

    @Basic
    @Column(name="ISEDIT",length=1)
    public String getIsedit(){

        return this.isedit;
    }

    public void setGuid(Long guid){

        this.guid=guid;
    }

    @Id
    @GeneratedValue(generator="sequence")
    @SequenceGenerator(name="sequence",sequenceName="report_model_sequence")
    @Basic
    @Column(name="GUID")
    public Long getGuid(){

        return this.guid;
    }

    public void setReportname(String reportname){

        this.reportname=reportname;
    }

    @Basic
    @Column(name="REPORTNAME",length=200)
    public String getReportname(){

        return this.reportname;
    }

    public void setReportcode(String reportcode){

        this.reportcode=reportcode;
    }

    @Basic
    @Column(name="REPORTCODE",length=50)
    public String getReportcode(){

        return this.reportcode;
    }

    public void setReportid(String reportid){

        this.reportid=reportid;
    }

    @Basic
    @Column(name="REPORTID",length=200)
    public String getReportid(){

        return this.reportid;
    }

    public void setProcessid(String processid){

        this.processid=processid;
    }

    @Basic
    @Column(name="PROCESSID",length=50)
    public String getProcessid(){

        return this.processid;
    }

    public ReportModel(String isedit,Long guid,String reportname,String reportcode,String reportid){

        
        this.isedit=isedit;
        this.guid=guid;
        this.reportname=reportname;
        this.reportcode=reportcode;
        this.reportid=reportid;
    }

    public ReportModel(){

            }

}
