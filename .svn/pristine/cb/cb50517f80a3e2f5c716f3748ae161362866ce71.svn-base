package com.datanew.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="PRO_RELEASE_LOG",schema="JXGL")
public  class  ProReleaseLog {

    //创建用户名称
    private String createusername;

    //年
    private String year;

    //创建时间
    private Date createdate;

    //报表模板id
    private Long reportmodelid;

    //主键
    private Long guid;

    //创建用户id
    private String createuserid;

    //报表模板名称
    private String reportmodelname;

    //批次号
    private Integer batch;

    public void setCreateusername(String createusername){

        this.createusername=createusername;
    }

    @Basic
    @Column(name="CREATEUSERNAME",length=200)
    public String getCreateusername(){

        return this.createusername;
    }

    public void setYear(String year){

        this.year=year;
    }

    @Basic
    @Column(name="YEAR",length=4)
    public String getYear(){

        return this.year;
    }

    public void setCreatedate(Date createdate){

        this.createdate=createdate;
    }

    @Basic
    @Column(name="CREATEDATE")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",locale="zh",timezone="GMT+8")
    public Date getCreatedate(){

        return this.createdate;
    }

    public void setReportmodelid(Long reportmodelid){

        this.reportmodelid=reportmodelid;
    }

    @Basic
    @Column(name="REPORTMODELID")
    public Long getReportmodelid(){

        return this.reportmodelid;
    }

    public void setGuid(Long guid){

        this.guid=guid;
    }

    @Id
    @GeneratedValue(generator="sequence")
    @SequenceGenerator(name="sequence",sequenceName="base_release_log_sequence")
    @Basic
    @Column(name="GUID")
    public Long getGuid(){

        return this.guid;
    }

    public void setCreateuserid(String createuserid){

        this.createuserid=createuserid;
    }

    @Basic
    @Column(name="CREATEUSERID",length=20)
    public String getCreateuserid(){

        return this.createuserid;
    }

    public void setReportmodelname(String reportmodelname){

        this.reportmodelname=reportmodelname;
    }

    @Basic
    @Column(name="REPORTMODELNAME",length=200)
    public String getReportmodelname(){

        return this.reportmodelname;
    }

    public void setBatch(Integer batch){

        this.batch=batch;
    }

    @Basic
    @Column(name="BATCH")
    public Integer getBatch(){

        return this.batch;
    }

    public ProReleaseLog(String createusername,String year,Date createdate,Long reportmodelid,Long guid,String createuserid,String reportmodelname,Integer batch){


        this.createusername=createusername;
        this.year=year;
        this.createdate=createdate;
        this.reportmodelid=reportmodelid;
        this.guid=guid;
        this.createuserid=createuserid;
        this.reportmodelname=reportmodelname;
        this.batch=batch;
    }

    public ProReleaseLog(){

    }

}
