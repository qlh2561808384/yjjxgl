package com.datanew.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name = "BASE_PROCESS_NODES")
public class BaseProcessNodes implements Serializable{
	
	private	Long nodeId;                      
	private	Long processId;                  
	private	Integer nodeNum;                     
	private	String nodename;                     
	private	Long roleId;                                     
	private	Integer after_Node_Num;	
	private	String authIds;
	private	String authNames;
	private Integer isRoleSelect;
	@Id
    @GeneratedValue( generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "base_process_nodes_sequence")
	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}
	@Column(name = "processId", length = 20)
	public Long getProcessId() {
		return processId;
	}

	public void setProcessId(Long processId) {
		this.processId = processId;
	}
	@Column(name = "nodeNum", length = 20)
	public Integer getNodeNum() {
		return nodeNum;
	}

	public void setNodeNum(Integer nodeNum) {
		this.nodeNum = nodeNum;
	}
	@Column(name = "nodename", length = 200)
	public String getNodename() {
		return nodename;
	}

	public void setNodename(String nodename) {
		this.nodename = nodename;
	}
	@Column(name = "roleId", length = 20)
	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	@Column(name = "after_Node_Num", length = 200)
	public Integer getAfter_Node_Num() {
		return after_Node_Num;
	}

	public void setAfter_Node_Num(Integer afterNodeNum) {
		after_Node_Num = afterNodeNum;
	}
	@Column(name = "authIds", length = 200)
	public String getAuthIds() {
		return authIds;
	}

	public void setAuthIds(String authIds) {
		this.authIds = authIds;
	}
	@Column(name = "authNames", length = 200)
	public String getAuthNames() {
		return authNames;
	}

	public void setAuthNames(String authNames) {
		this.authNames = authNames;
	}
	@Column(name = "isRoleSelect", length = 20)
	public Integer getIsRoleSelect() {
		return isRoleSelect;
	}

	public void setIsRoleSelect(Integer isRoleSelect) {
		this.isRoleSelect = isRoleSelect;
	}

}