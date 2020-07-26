package com.datanew.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "fszg.xt_REGION")
public class Region {

    private String regiCode;    //	Varchar2(6)	PK	√		行政区划编码
    private String regiNo;        //	Varchar2(2)	UN	√		行政区划编号
    private String regiName;    //	Varchar2(30)			行政区划名称
    private Long f_EnteFsGUID;    //	Number(20)	FK	√		行政区划对应财政管理部门GUID（引用DW_Enterprise表）
    private String financeLevel;//	Char(1)					行政区划对应财政级次（1中央，2省，3市，4县（市、区））
    private String p_regiCode;    //	Varchar2(6)				父节点（0为根节点）
    private String synMark;    //同步标志  0未同步1已同步2同步失败
    private Integer synNum;    //同步次数
    private Date synDate;    //同步时间
    private String synInfo;    //同步说明
    private String syn_flag = "0";

    @Column
    public String getSyn_flag() {
        return syn_flag;
    }

    public void setSyn_flag(String syn_flag) {
        this.syn_flag = syn_flag;
    }


    @Column(length = 6)
    public String getP_regiCode() {
        return p_regiCode;
    }

    @Id
    @Column(length = 6)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public String getRegiCode() {
        return regiCode;
    }

    @Column(length = 30)
    public String getRegiName() {
        return regiName;
    }

    @Column(length = 6)
    public String getRegiNo() {
        return regiNo;
    }

    @Column(length = 20)
    public Long getF_EnteFsGUID() {
        return f_EnteFsGUID;
    }

    @Column(length = 1)
    public String getFinanceLevel() {
        return financeLevel;
    }

    public void setF_EnteFsGUID(Long enteFsGUID) {
        f_EnteFsGUID = enteFsGUID;
    }


    public void setFinanceLevel(String financeLevel) {
        this.financeLevel = financeLevel;
    }

    public void setP_regiCode(String p_regiCode) {
        this.p_regiCode = p_regiCode;
    }


    public void setRegiCode(String regiCode) {
        this.regiCode = regiCode;
    }

    public void setRegiName(String regiName) {
        this.regiName = regiName;
    }

    public void setRegiNo(String regiNo) {
        this.regiNo = regiNo;
    }

    @Column
    public String getSynMark() {
        return synMark;
    }
    
    public void setSynMark(String synMark) {
        this.synMark = synMark;
    }

    @Column
    public Integer getSynNum() {
        return synNum;
    }

    public void setSynNum(Integer synNum) {
        this.synNum = synNum;
    }

    @Column
    public Date getSynDate() {
        return synDate;
    }

    public void setSynDate(Date synDate) {
        this.synDate = synDate;
    }

    @Column
    public String getSynInfo() {
        return synInfo;
    }

    public void setSynInfo(String synInfo) {
        this.synInfo = synInfo;
    }


}
