package com.datanew.model;

import com.hzzk.common.dto.OperatorDTO;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity// 对实体注释。任何Hibernate映射对象都要有这个注释
@Table(name="base_operator")//声明此对象映射到数据库的数据表，通过它可以为实体指定表(talbe),目录(Catalog)和schema的名字。该注释不是必须的，如果没有则系统使用默认值(实体的短类名)。
public class BaseOperator
  implements Serializable
{
  private Integer userid;
  private String loginname;
  private String username;
  private String password;
  private String entecode;
  private String remark;
  private String moblephonenum;
  private String telephone;
  private Date createdate;
  private Date logintime;
  private String idcard;
  private String regicode;
  private Date lastLoginTime;
  private String jczl_userid;
  private String enable;
  private Long parent_guid;
  /*
  ca登录新增方法
   */
  public BaseOperator convert(OperatorDTO o) {
    this.userid = o.getGuId().intValue();
    this.loginname = o.getCode();
    this.username = o.getName();
    this.password = o.getPassword();
    this.remark = o.getRemark();
    this.moblephonenum = null;
    this.entecode = o.getEnterpriseGuId().intValue()+"";
    this.telephone = null;
    /*this.position = null;
    this.email = null;
    this.address = null;
    this.usertype = null;
    this.createname = null;*/
    this.createdate = null;
    this.logintime = null;
    /*this.postname=null;*/
    this.parent_guid = (long)o.getParentGuId().intValue();
    /*this.post=null;
    this.lastlogintime = null;*/
    return this;
  }

  /*
*   @Version:该注释可用于在实体Bean中添加乐观锁支持。
*
* */
  @Id// 声明此属性为主键。该属性值可以通过应该自身创建，但是Hibernate推荐通过Hibernate生成
  @GeneratedValue(generator="sequence")//GeneratedValue用来定义主键生成策略
  @SequenceGenerator(name="sequence", sequenceName="base_operator_sequence")//SequenceGenerator用来定义一个生成主键的序列；它们要联合使用才有效。
  public Integer getUserid() { return this.userid; }

  public void setUserid(Integer userid)
  {
    this.userid = userid;
  }

  @Column(name="LOGINNAME", length=30)//  声明该属性与数据库字段的映射关系。
  public String getLoginname() {
    return this.loginname;
  }

  public void setLoginname(String loginname) {
    this.loginname = loginname;
  }

  @Column(name="USERNAME", length=90)
  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
  @Column(name="REGICODE", length=6)
  public String getRegicode() {
    return this.regicode;
  }

  public void setRegicode(String regicode) {
    this.regicode = regicode;
  }
  
  @Column(name = "ENTECODE", length = 60)
  public String getEntecode() {
		return this.entecode;
  }

  public void setEntecode(String entecode) {
		this.entecode = entecode;
  }
	
  @Column(name="PASSWORD", length=120)
  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Column(name="REMARK", length=200)
  public String getRemark() {
    return this.remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  @Column(name="MOBLEPHONENUM", length=60)
  public String getMoblephonenum() {
    return this.moblephonenum;
  }

  public void setMoblephonenum(String moblephonenum) {
    this.moblephonenum = moblephonenum;
  }

  @Column(name="TELEPHONE", length=90)
  public String getTelephone() {
    return this.telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  @Column(name="CREATEDATE")
  public Date getCreatedate() {
    return this.createdate;
  }

  public void setCreatedate(Date createdate) {
    this.createdate = createdate;
  }

  @Column(name="LOGINTIME")
  public Date getLogintime() {
    return this.logintime;
  }

  public void setLogintime(Date logintime) {
    this.logintime = logintime;
  }

  @Column(name="IDCARD")
  public String getIdcard() {
    return this.idcard;
  }

  public void setIdcard(String idcard) {
    this.idcard = idcard;
  }


  @Column(name="JCZL_USERID")
  public String getJczl_userid() {
    return jczl_userid;
  }

  public void setJczl_userid(String jczl_userid) {
    this.jczl_userid = jczl_userid;
  }

  @Column(name="ENABLE")
  public String getEnable() {
    return enable;
  }

  public void setEnable(String enable) {
    this.enable = enable;
  }

  @Column(name="PARENT_GUID")
  public Long getParent_guid() {
    return parent_guid;
  }

  public void setParent_guid(Long parent_guid) {
    this.parent_guid = parent_guid;
  }


  @Transient//。当POJO有属性不需要映射的时候一定要用@Transitent修饰，该注释表示此属性与表没有映射关系，只是一个暂时的属性
  public Date getLastLoginTime() {
    return this.lastLoginTime;
  }

  public void setLastLoginTime(Date lastLoginTime) {
    this.lastLoginTime = lastLoginTime;
  }
}