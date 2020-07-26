package com.datanew.model;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "BB_TBUSERTYPE")
public class BbTbUserType {
	
	private Long typeId;//主键ID
	private String typeCode;//
	private String typeName;//
    private Long createUserId;//创建用户Id
    private String createUserName;//创建用户
    private Date createDate;//创建时间
    private Date updateDate;//最后一次修改时间
    private String remark;
    //临时变量
    private String userId;
    @Id
    @GeneratedValue( generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "bb_tbusertype_sequence")
	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	@Column(name = "typeCode", length = 200)
	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	@Column(name = "typeName", length = 200)
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	@Column(name = "createUserId", length = 20)
	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	@Column(name = "createUserName", length = 200)
	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	@Column(name = "createDate")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Column(name = "updateDate")
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	@Column(name = "remark", length = 200)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Transient
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

   
}
