package com.datanew.model;

import javax.persistence.Column;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "activity_instance")
public class ActiveInstance {
	
	private	Long ai_Id;                        
	private	Long processId;                       
	private	Long node_Id; 
	private String node_Name;
	private	Long ai_User_Id; 
	private String ai_User_Name;
	private String business_No;	 
	private	Date ai_Start_DateTime;                        
	private	String operation;
	private String opinion;
	private Integer isRoleSelect;
	private Long roleId;
	private String roleName;
	private String filename;
	private byte[] filecontent;
	@Id
    @GeneratedValue( generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "activity_instance_sequence")
	public Long getAi_Id() {
		return ai_Id;
	}
	public void setAi_Id(Long ai_Id) {
		this.ai_Id = ai_Id;
	}
	@Column(name = "PROCESSID")
	public Long getProcessId() {
		return processId;
	}
	public void setProcessId(Long processId) {
		this.processId = processId;
	}
	@Column(name = "NODE_ID")
	public Long getNode_Id() {
		return node_Id;
	}
	public void setNode_Id(Long node_Id) {
		this.node_Id = node_Id;
	}
	@Column(name = "NODE_NAME")
	public String getNode_Name() {
		return node_Name;
	}
	public void setNode_Name(String node_Name) {
		this.node_Name = node_Name;
	}
	@Column(name = "AI_USER_ID")
	public Long getAi_User_Id() {
		return ai_User_Id;
	}
	public void setAi_User_Id(Long ai_User_Id) {
		this.ai_User_Id = ai_User_Id;
	}
	@Column(name = "AI_USER_NAME")
	public String getAi_User_Name() {
		return ai_User_Name;
	}
	public void setAi_User_Name(String ai_User_Name) {
		this.ai_User_Name = ai_User_Name;
	}
	@Column(name = "BUSINESS_NO")
	public String getBusiness_No() {
		return business_No;
	}
	public void setBusiness_No(String business_No) {
		this.business_No = business_No;
	}
	@Column(name = "AI_START_DATETIME")
	public Date getAi_Start_DateTime() {
		return ai_Start_DateTime;
	}
	public void setAi_Start_DateTime(Date ai_Start_DateTime) {
		this.ai_Start_DateTime = ai_Start_DateTime;
	}

	@Column(name = "OPERATION")
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	@Column(name = "OPINION")
	public String getOpinion() {
		return opinion;
	}
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	@Column(name = "ISROLESELECT")
	public Integer getIsRoleSelect() {
		return isRoleSelect;
	}
	public void setIsRoleSelect(Integer isRoleSelect) {
		this.isRoleSelect = isRoleSelect;
	}
	@Column(name = "ROLEID")
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	@Column(name = "ROLENAME")
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	@Column(name = "FILENAME",length=200)
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	@Column(name = "FILECONTENT")
	public byte[] getFilecontent() {
		return filecontent;
	}
	public void setFilecontent(byte[] filecontent) {
		this.filecontent = filecontent;
	}


}
